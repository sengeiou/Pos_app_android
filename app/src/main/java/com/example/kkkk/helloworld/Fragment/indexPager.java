package com.example.kkkk.helloworld.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kkkk.helloworld.Activity.NowTaskActivity;
import com.example.kkkk.helloworld.Activity.SignInActivity;
import com.example.kkkk.helloworld.Activity.noticeActivity;
import com.example.kkkk.helloworld.Activity.noticedetailActivity;
import com.example.kkkk.helloworld.adapter.GridViewAdapter;
import com.example.kkkk.helloworld.R;
import com.example.kkkk.helloworld.adapter.IndexGridAdapter;
import com.example.kkkk.helloworld.adapter.attentionAdapter;
import com.example.kkkk.helloworld.starItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkkk on 2017/11/5.
 */

public class indexPager extends Fragment {

    List<starItem> starList;
    GridView gridView;
    GridView grdView2;
    GridView grid_attention;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_index,container,false);
        gridView = (GridView) view.findViewById(R.id.grid);
        grdView2 = (GridView) view.findViewById(R.id.grid_);
        grid_attention = (GridView) view.findViewById(R.id.grid_attention);
        initView();
        setData();
        setGridView();
        return view;
    }


    private void initView(){
        final IndexGridAdapter gridadapter=new IndexGridAdapter(getContext());
        grdView2.setAdapter(gridadapter);
        grdView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridadapter.setSeclection(position);
                gridadapter.notifyDataSetChanged();
                switch (position) {
                    case 0:
                        intent=new Intent(getActivity(),SignInActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(getActivity(),noticeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(getActivity(),NowTaskActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
        final attentionAdapter aAdapter=new attentionAdapter(getContext());
        grid_attention.setAdapter(aAdapter);
        grid_attention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aAdapter.setSeclection(position);
                aAdapter.notifyDataSetChanged();
                Intent intent=new Intent(getActivity(),noticedetailActivity.class);
                startActivity(intent);

            }
        });
    }

    /**设置数据*/
    private void setData() {
        starList = new ArrayList<starItem>();
        starItem item = new starItem();
        item.setStaffName("小王");
        starList.add(item);
        item = new starItem();
        item.setStaffName("小王");
        starList.add(item);
        item = new starItem();
        item.setStaffName("小王");
        starList.add(item);
        item = new starItem();
        item.setStaffName("小王");
        starList.add(item);
        item = new starItem();
        item.setStaffName("小王");
        starList.add(item);
        item = new starItem();
        item.setStaffName("小王");
        starList.add(item);
        starList.addAll(starList);

    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
        int size = starList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getContext(),
                starList);
        gridView.setAdapter(adapter);
    }



}

