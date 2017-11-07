package com.example.kkkk.helloworld.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kkkk.helloworld.Activity.settingActivity;
import com.example.kkkk.helloworld.Activity.userinfoActivity;
import com.example.kkkk.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kkkk on 2017/11/5.
 */

public class userPager extends Fragment {

    @BindView(R.id.userinfo)
    LinearLayout userinfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.userinfo,R.id.set})
    public void xxxx(View view) {
        switch (view.getId()){
            case R.id.userinfo:
                Intent intent1=new Intent(getActivity(),userinfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.set:
                Intent intent2=new Intent(getActivity(),settingActivity.class);
                startActivity(intent2);
                break;
        }

    }

}

