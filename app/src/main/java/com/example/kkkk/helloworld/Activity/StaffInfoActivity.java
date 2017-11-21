package com.example.kkkk.helloworld.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kkkk.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StaffInfoActivity extends BaseAppActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.age)
    TextView age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffinfo);
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
