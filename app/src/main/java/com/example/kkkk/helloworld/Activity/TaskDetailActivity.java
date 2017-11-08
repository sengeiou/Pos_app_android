package com.example.kkkk.helloworld.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kkkk.helloworld.adapter.rollviewAdapter;
import com.example.kkkk.helloworld.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.jude.rollviewpager.hintview.IconHintView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailActivity extends AppCompatActivity {

    private RollPagerView mRollViewPager;
    private Button btnDo;
    @BindView(R.id.back)
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetail);
        ButterKnife.bind(this);
        btnDo= (Button) findViewById(R.id.btn_do);
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new rollviewAdapter(mRollViewPager));

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        mRollViewPager.setHintView(new IconHintView(this, R.drawable.dot_1, R.drawable.dot_2,24));
        //mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
        btnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TaskDetailActivity.this,DoTaskActivity.class);
                startActivity(intent);
            }
        });

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
