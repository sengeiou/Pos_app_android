package com.example.kkkk.helloworld.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.kkkk.helloworld.R;
import com.example.kkkk.helloworld.adapter.ItemAdapter;

public class DoTaskActivity extends AppCompatActivity {

    TextView btntest;
    MyGridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dotask);
        //btntest= (TextView) findViewById(R.id.textbtn);
        gridView = (MyGridView) findViewById(R.id.grid_);
        initView();
    }

    private void initView() {
        final ItemAdapter gridadapter=new ItemAdapter(this);
        gridView.setAdapter(gridadapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridadapter.setSeclection(position);
                gridadapter.notifyDataSetChanged();
            }
        });
    }
}
