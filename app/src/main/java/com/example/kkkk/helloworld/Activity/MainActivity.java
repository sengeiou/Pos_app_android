package com.example.kkkk.helloworld.Activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.kkkk.helloworld.DemoHelper;
import com.example.kkkk.helloworld.Fragment.indexPager;
import com.example.kkkk.helloworld.Fragment.nearbyPager;
import com.example.kkkk.helloworld.Fragment.userPager;
import com.example.kkkk.helloworld.R;
import com.example.kkkk.helloworld.adapter.TablayoutAdapter;
import com.example.kkkk.helloworld.util.ExitUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.jude.utils.JActivityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseAppActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private TablayoutAdapter mAdapter;
    public boolean isexit = false;

    private ExitUtils exit = new ExitUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        initView();
        initData();

        setData();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tl_tablayout_navigation);
        mViewPager = (ViewPager) findViewById(R.id.vp_tablayout_show);

    }

    private void setData() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            Drawable drawable = null;
            switch (i) {
                case 0:
                    //图片资源我们同样要使用选择器,选择器我们不能使用state_checked属性,而应该使用state_selected属性
                    drawable = getResources().getDrawable(R.drawable.hometablayout);
                    break;
                case 1:
                    drawable = getResources().getDrawable(R.drawable.neartablayout);
                    break;
                case 2:
                    drawable = getResources().getDrawable(R.drawable.usertablayout);
                    break;
            }
            tab.setIcon(drawable);
        }

    }

    private void initData() {
        ArrayList<String> mTitles = new ArrayList<>();
        mTitles.add("首页");
        mTitles.add("附近");
        mTitles.add("我的");
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            if (i == 0) {
                mFragments.add(new indexPager());
            } else if (i == 1) {
                mFragments.add(new nearbyPager());
            } else if (i == 2) {
                mFragments.add(new userPager());
            }
        }
        mAdapter = new TablayoutAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // unregister this event listener when this activity enters the
        // background
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.pushActivity(this);

        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    protected void onStop() {
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
        //EMClient.getInstance().removeClientListener(clientListener);
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.popActivity(this);

        super.onStop();
    }
    //重写onKeyDown方法,
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            //调用exit()方法
            exit();
        }
        return false;
    }
    //被调用的exit()方法
    private void exit() {
        Timer timer;//声明一个定时器
        if (!isexit) {  //如果isExit为false,执行下面代码
            isexit = true;  //改变值为true
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();  //弹出提示
            timer = new Timer();  //得到定时器对象
            //执行定时任务,两秒内如果没有再次按下,把isExit值恢复为false,再次按下返回键时依然会进入if这段代码
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isexit = false;
                }
            }, 2000);
        } else {//如果两秒内再次按下了返回键,这时isExit的值已经在第一次按下时赋值为true了,因此不会进入if后的代码,直接执行下面的代码
            finish();
        }
    }
    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message
            for (EMMessage message : messages) {
                DemoHelper.getInstance().getNotifier().onNewMsg(message);
            }
            //refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //refreshUIWithMessage();
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {}
    };
}

