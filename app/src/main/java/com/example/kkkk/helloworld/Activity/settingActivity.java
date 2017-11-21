package com.example.kkkk.helloworld.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kkkk.helloworld.App;
import com.example.kkkk.helloworld.R;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class settingActivity extends BaseAppActivity {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.btn_cancellogin)
    Button btnCancellogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back,R.id.btn_cancellogin})
    void click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btn_cancellogin:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        LemonHello.getInformationHello("您确定要退出吗？", "退出登录后您将无法接收到当前用户的所有推送消息。")
                .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                        helloView.hide();
                    }
                }))
                .addAction(new LemonHelloAction("我要退出", Color.RED, new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                        App.getInstance().setName("");
                        App.getInstance().setPwd("");
                        Intent intent = new Intent(settingActivity.this, loginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        settingActivity.this.finish();
                        helloView.hide();
                    }
                }))
                .show(settingActivity.this);
    }
}
