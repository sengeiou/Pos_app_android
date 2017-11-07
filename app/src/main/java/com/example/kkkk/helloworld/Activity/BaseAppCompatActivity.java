package com.example.kkkk.helloworld.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.kkkk.helloworld.R;

/**
 * Created by kkkk on 2017/11/5.
 */

class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
