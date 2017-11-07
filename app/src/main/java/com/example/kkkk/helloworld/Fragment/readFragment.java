package com.example.kkkk.helloworld.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.kkkk.helloworld.Activity.noticedetailActivity;
import com.example.kkkk.helloworld.R;
import com.example.kkkk.helloworld.adapter.readGridAdapter;

/**
 * Created by Administrator on 2017/11/6.
 */

public class readFragment extends Fragment {

    GridView gridView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_read,container,false);

        gridView = (GridView) view.findViewById(R.id.grid_read);
        initView();
        return view;
    }

    private void initView() {
        final readGridAdapter gridadapter=new readGridAdapter(getContext());
        gridView.setAdapter(gridadapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridadapter.setSeclection(position);
                gridadapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "已读通告"+position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),noticedetailActivity.class);
                startActivity(intent);
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        //Intent intent=new Intent(getActivity(),noticeActivity.class);
                        //startActivity(intent);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }

            }
        });
    }


}
