package com.example.kkkk.helloworld.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kkkk.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class sendnoticeActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendnotice);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.back})
    void click(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
