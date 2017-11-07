package com.example.kkkk.helloworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kkkk.helloworld.BaseViewHolder;
import com.example.kkkk.helloworld.R;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class attentionAdapter extends BaseAdapter {
    public String[] title = {"工作调度通知", "工作调度通知","工作调度通知","工作调度通知", "工作调度通知","工作调度通知"};
    public String[] name = {"小王", "小王","小王","小王", "小王","小王"};
    public String[] status = {"确认收到", "等待处理","等待处理","确认收到", "等待处理","等待处理"};
    public String[] time = {"2017 9-10", "2017 9-10","2017 9-10","2017 9-10", "2017 9-10","2017 9-10"};
    public int[] imgs_0= {R.drawable.status_bg_green, R.drawable.status_bg_orange};//,
    private Context mContext;

    final int itemLength = 4;
    private int clickTemp = -1;//标识被选择的item
    private int[] clickedList=new int[itemLength];//这个数组用来存放item的点击状态

    public attentionAdapter(Context mContext) {
        super();
        this.mContext = mContext;
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
        return title.length;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.attention_item, parent, false);
        }
        TextView wordtitle = BaseViewHolder.get(convertView, R.id.wordtitle);
        TextView wordname = BaseViewHolder.get(convertView, R.id.wordname);
        TextView wordtime = BaseViewHolder.get(convertView, R.id.wordtime);
        TextView wordstatus = BaseViewHolder.get(convertView, R.id.wordstatus);
        wordtitle.setText(title[position]);
        wordname.setText(name[position]);
        wordtime.setText(time[position]);
        wordstatus.setText(status[position]);
        if (status[position].equals("确认收到")){
            wordstatus.setBackgroundResource(imgs_0[0]);
        }else {
            wordstatus.setBackgroundResource(imgs_0[1]);
        }

        return convertView;
    }

}
