package com.example.kkkk.helloworld.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kkkk.helloworld.Activity.noticedetailActivity;
import com.example.kkkk.helloworld.App;
import com.example.kkkk.helloworld.R;
import com.example.kkkk.helloworld.adapter.readGridAdapter;
import com.example.kkkk.helloworld.http.RetrofitHttp;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/11/6.
 */

public class noreadFragment extends Fragment {

    GridView gridView;
    private ProgressDialog mDialog;
    PullToRefreshLayout swip_noread;
    int curPage=1;
    int flag=1;
    List<JSONObject> list;
    @BindView(R.id.errorview)
    TextView errorView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_noread,container,false);
        ButterKnife.bind(this, view);
        gridView = (GridView) view.findViewById(R.id.grid_noread);
        swip_noread= (PullToRefreshLayout) view.findViewById(R.id.swipe_notice_noread);
        initView();
        getReadList(1);
        onfresh();
        return view;
    }

    private void initView() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("请稍等");
        mDialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回按键取消
        mDialog.setCancelable(true);
    }
    private void onfresh(){
        swip_noread.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                flag=1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        curPage=1;
                        getReadList(curPage);
                        swip_noread.finishRefresh();
                    }
                },1200);
            }

            @Override
            public void loadMore() {
                flag=0;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        curPage++;
                        getReadList(curPage);
                        swip_noread.finishLoadMore();
                    }
                },1200);
            }
        });
    }
    private void getReadList(int page){
        mDialog.show();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        RetrofitHttp.getRetrofit(builder.build()).ReadList(App.getInstance().getMyToken(),"0",page,10)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response) {
                        mDialog.dismiss();
                        try {
                            String result = response.body().string();
                            JSONObject jsonObject = JSON.parseObject(result);
                            String msg = jsonObject.getString("message");
                            String data = jsonObject.getString("data");
                            JSONObject data_ = JSON.parseObject(data);
                            int totalSize=data_.getInteger("totalSize");
                            String list_ =data_.getString("list");
                            JSONArray list_temp = JSON.parseArray(list_);
                            if (jsonObject.getString("code").equals("failure")) {
                                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (flag==0&&list_.equals("[]")){
                                    Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                                }else {
                                    for (int i = 0; i < list_temp.size(); i++) {
                                        JSONObject json = list_temp
                                                .getJSONObject(i);
                                        list.add(json);
                                    }
                                    if (list_.equals("[]")){
                                        errorView.setVisibility(View.VISIBLE);
                                    }else {
                                        loadList(list,totalSize);
                                    }

                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mDialog.dismiss();
                        Toast.makeText(getContext(), "未知错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void loadList(final List<JSONObject> list,int totalSize){
        final readGridAdapter gridadapter=new readGridAdapter(getContext(),list);
        gridView.setAdapter(gridadapter);
        if (curPage!=1){
            gridView.setSelection(totalSize-4);
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridadapter.setSeclection(position);
                gridadapter.notifyDataSetChanged();
                Intent intent=new Intent(getActivity(),noticedetailActivity.class);
                intent.putExtra("uuid",list.get(position).getString("uuid"));
                startActivity(intent);

            }
        });
    }
}
