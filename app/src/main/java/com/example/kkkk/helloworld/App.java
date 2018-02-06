/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.kkkk.helloworld;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.example.kkkk.helloworld.util.SharedPreUtil;
import com.example.kkkk.helloworld.util.StringUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hyphenate.chat.EMOptions;
import com.jude.utils.JUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yan Zhenjie on 2016/7/27.
 * application
 */
public class App extends Application {

    //public OKHttpUtils okHttpUtils;
    private static App instance;
    public static Context applicationContext;
    public final String PREF_USERNAME = "username";
    public static String currentUserNick = "";
    //private ACache aCache;
    private String accessToken;
    // 用户名和密码
    private String name;
    private String pwd;

    private String sex;

    EMOptions options;
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
        applicationContext = this;
        //SpeechUtility.createUtility(App.this, SpeechConstant.APPID + "=58f6c834");
        Fresco.initialize(this);
        JUtils.initialize(this);
        //生成文件夹
        //JFileManager.getInstance().init(this, Dir.values());
        //aCache = ACache.get(instance);
        //registerActivityLifecycleCallbacks(JActivityManager.getActivityLifecycleCallbacks());

        //init demo helper
        options=new EMOptions();
        options.setAutoLogin(false);
        DemoHelper.getInstance().init(applicationContext);
        //red packet code : 初始化红包SDK，开启日志输出开关
    }
    public String getName() {
        if (StringUtil.isNullOrEmpty(name)) {
            return SharedPreUtil.getValue(instance, "name", "");
        }
        return name;
    }

    public void setName(String name) {
        if (!StringUtil.isNullOrEmpty(name)) {
            SharedPreUtil.putValue(instance, "name", name);
            this.name = name;
        } else {
            SharedPreUtil.putValue(instance, "name", "");
            this.name = "";
        }
    }

    public String getPwd() {
        if (StringUtil.isNullOrEmpty(pwd)) {
            return SharedPreUtil.getValue(instance, "pwd", "");
        }
        return pwd;
    }

    public void setPwd(String pwd) {
        if (!StringUtil.isNullOrEmpty(pwd)) {
            SharedPreUtil.putValue(instance, "pwd", pwd);
            this.pwd = pwd;
        } else {
            SharedPreUtil.putValue(instance, "pwd", "");
            this.pwd = "";
        }
    }

    public String getMyToken() {
        if (StringUtil.isNullOrEmpty(accessToken)) {
            return SharedPreUtil.getValue(instance, "accessToken", "");
        }
        return accessToken;
    }

    public void setMyToken(String accessToken) {
        if (!StringUtil.isNullOrEmpty(accessToken)) {
            SharedPreUtil.putValue(instance, "accessToken", accessToken);
            this.accessToken = accessToken;
        } else {
            SharedPreUtil.putValue(instance, "accessToken", "");
            this.accessToken = "";
        }
    }

    //文件目录列表
    public enum Dir {
        Object
    }
}
