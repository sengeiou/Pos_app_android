package com.example.kkkk.helloworld.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kkkk.helloworld.Dialog.DialogNPV;
import com.example.kkkk.helloworld.Dialog.NameDialog;
import com.example.kkkk.helloworld.Dialog.SexDialog;
import com.example.kkkk.helloworld.R;

public class userinfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String BROADCAST_ACTION_AGE = "com.example.kkk.age";
    public static final String BROADCAST_ACTION_NAME = "com.example.kkk.name";
    public static final String BROADCAST_ACTION_SEX = "com.example.kkk.sex";
    private BroadcastReceiver mBroadcastReceiver;
    IntentFilter intentFilter;
    private DialogNPV mDialogNPV;
    private SexDialog sexDialog;
    private NameDialog nameDialog;
    LinearLayout selectAge;
    LinearLayout selectSex;
    LinearLayout selectname;
    TextView age;
    TextView sex;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        selectAge= (LinearLayout) findViewById(R.id.selectage);
        selectSex= (LinearLayout) findViewById(R.id.selectsex);
        selectname=(LinearLayout) findViewById(R.id.selectname);
        age= (TextView) findViewById(R.id.age);
        sex= (TextView) findViewById(R.id.sex);
        name= (TextView) findViewById(R.id.name);
        selectAge.setOnClickListener(this);
        selectSex.setOnClickListener(this);
        selectname.setOnClickListener(this);

        selectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFilter.addAction(BROADCAST_ACTION_AGE);
                registerReceiver(mBroadcastReceiver, intentFilter);
                showNPVDialog();
            }
        });
        selectSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentFilter.addAction(BROADCAST_ACTION_SEX);
                registerReceiver(mBroadcastReceiver, intentFilter);
                showSexDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectage:
                intentFilter.addAction(BROADCAST_ACTION_AGE);
                registerReceiver(mBroadcastReceiver, intentFilter);
                showNPVDialog();
                break;
            case R.id.selectsex:
                intentFilter.addAction(BROADCAST_ACTION_SEX);
                registerReceiver(mBroadcastReceiver, intentFilter);
                showSexDialog();
                break;
            case R.id.selectname:
                intentFilter.addAction(BROADCAST_ACTION_NAME);
                registerReceiver(mBroadcastReceiver, intentFilter);
                showNameDialog();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBroadcastReceiver = new mBroadcastReceiver();
        intentFilter = new IntentFilter();

    }
//注册广播后，要在相应位置记得销毁广播
//即在onPause() 中unregisterReceiver(mBroadcastReceiver)
//当此Activity实例化时，会动态将MyBroadcastReceiver注册到系统中
//当此Activity销毁时，动态注册的MyBroadcastReceiver将不再接收到相应的广播。

    @Override
    protected void onPause() {
        super.onPause();
        //销毁在onResume()方法中的广播
        unregisterReceiver(mBroadcastReceiver);
    }
    private void showNPVDialog(){
        if(mDialogNPV == null){
            mDialogNPV = new DialogNPV(this);
        }
        if(mDialogNPV.isShowing()){
            mDialogNPV.dismiss();
        }else {
            mDialogNPV.setCancelable(true);
            mDialogNPV.setCanceledOnTouchOutside(true);
            mDialogNPV.show();
            Window window = mDialogNPV.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_menu_animation);
            WindowManager m = getWindowManager();
            Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
            WindowManager.LayoutParams p = mDialogNPV.getWindow().getAttributes(); //获取对话框当前的参数值
            p.width = d.getWidth(); //宽度设置为屏幕
            mDialogNPV.getWindow().setAttributes(p); //设置生效
            // recommend initializing data (or setting certain data) of NumberPickView
            // every time setting up NumberPickerView,
            // and setting attr app:npv_RespondChangeOnDetached="false" to avoid NumberPickView
            // of responding onValueChanged callback if NumberPickerView detach from window
            // when it is scrolling
            mDialogNPV.initNPV();
        }
    }
    private void showSexDialog(){
        if(sexDialog == null){
            sexDialog = new SexDialog(this);
        }
        if(sexDialog.isShowing()){
            sexDialog.dismiss();
        }else {
            sexDialog.setCancelable(true);
            sexDialog.setCanceledOnTouchOutside(true);
            sexDialog.show();
            Window window = sexDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_menu_animation);
        }
    }
    private void showNameDialog(){
        if(nameDialog == null){
            nameDialog = new NameDialog(this);
        }
        if(nameDialog.isShowing()){
            nameDialog.dismiss();
        }else {
            nameDialog.setCancelable(true);
            nameDialog.setCanceledOnTouchOutside(true);
            nameDialog.show();
            Window window = nameDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_menu_animation);
            WindowManager m = getWindowManager();
            Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
            WindowManager.LayoutParams p = nameDialog.getWindow().getAttributes(); //获取对话框当前的参数值
            p.width = d.getWidth(); //宽度设置为屏幕
            nameDialog.getWindow().setAttributes(p); //设置生效
        }
    }

    public class mBroadcastReceiver extends BroadcastReceiver {

        //接收到广播后自动调用该方法
        @Override
        public void onReceive(Context context, Intent intent) {
            //写入接收广播后的操作
            switch (intent.getAction()){
                case BROADCAST_ACTION_AGE:
                    String age_ = intent.getExtras().getString("age");
                    age.setText(age_);
                    break;
                case BROADCAST_ACTION_NAME:
                    String name_ = intent.getExtras().getString("name");
                    if (!name_.equals("")){
                        name.setText(name_);
                    }else {
                        Toast.makeText(context, "昵称为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case BROADCAST_ACTION_SEX:
                    String sex_ = intent.getExtras().getString("sex");
                    sex.setText(sex_);
                    break;
            }

        }
    }


}
