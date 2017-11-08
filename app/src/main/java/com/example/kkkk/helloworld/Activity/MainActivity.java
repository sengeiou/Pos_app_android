package com.example.kkkk.helloworld.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.kkkk.helloworld.Fragment.indexPager;
import com.example.kkkk.helloworld.Fragment.nearbyPager;
import com.example.kkkk.helloworld.Fragment.userPager;
import com.example.kkkk.helloworld.R;
import com.example.kkkk.helloworld.adapter.TablayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private TablayoutAdapter mAdapter;

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


}

