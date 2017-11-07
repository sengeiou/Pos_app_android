package com.example.kkkk.helloworld.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.kkkk.helloworld.R;
import com.example.kkkk.helloworld.adapter.nowtaskAdapter;

public class NowTaskActivity extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowtask);
        gridView = (GridView) findViewById(R.id.grid_nowtask);
        initView();
    }

    private void initView() {
        final nowtaskAdapter gridadapter=new nowtaskAdapter(this);
        gridView.setAdapter(gridadapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridadapter.setSeclection(position);
                gridadapter.notifyDataSetChanged();
                Toast.makeText(NowTaskActivity.this, "任务"+String.valueOf(position+1), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NowTaskActivity.this,TaskDetailActivity.class);
                startActivity(intent);

            }
        });
    }
}
