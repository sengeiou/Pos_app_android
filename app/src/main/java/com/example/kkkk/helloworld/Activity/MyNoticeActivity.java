package com.example.kkkk.helloworld.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kkkk.helloworld.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyNoticeActivity extends BaseAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynotice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back})
    void click(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
