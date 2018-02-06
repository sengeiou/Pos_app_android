package com.example.kkkk.helloworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kkkk.helloworld.BaseViewHolder;
import com.example.kkkk.helloworld.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class readGridAdapter extends BaseAdapter {

        //public String[] img_text_0 = {"签到", "通知公告", "今日任务"};
        private Context mContext;
        //JSONArray list = new JSONArray();
        String data;
        JSONArray list_temp;
        final int itemLength = 4;
        JSONObject json;
        List<JSONObject> list;
        private int clickTemp = -1;//标识被选择的item
        private int[] clickedList=new int[itemLength];//这个数组用来存放item的点击状态

    public readGridAdapter(Context mContext, List<JSONObject> list) {
            super();
            this.mContext = mContext;
            this.list=list;
            //JSONObject data_ = JSON.parseObject(data);
            //String list =data_.getString("list");
            //list_temp = JSON.parseArray(list);
            for (int i =0;i<itemLength;i++){
                clickedList[i]=0;      //初始化item点击状态的数组
            }
        }

    public void setSeclection(int posiTion) {
        clickTemp = posiTion;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.read_item, parent, false);
        }
        TextView tvTitle = BaseViewHolder.get(convertView, R.id.title);
        TextView tvMessage = BaseViewHolder.get(convertView, R.id.message);
        TextView tvtime = BaseViewHolder.get(convertView, R.id.time);
        ImageView iv = BaseViewHolder.get(convertView, R.id.img);

        final JSONObject bean = list.get(position);


        iv.setBackgroundResource(R.drawable.picture);
        tvTitle.setText(bean.getString("title"));
        tvMessage.setText(bean.getString("content"));
        tvtime.setText(bean.getString("createTime"));
        return convertView;
    }

}

