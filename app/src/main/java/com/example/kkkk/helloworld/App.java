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

import android.app.Application;

import com.example.kkkk.helloworld.util.SharedPreUtil;
import com.example.kkkk.helloworld.util.StringUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.utils.JActivityManager;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

/**
 * Created by Yan Zhenjie on 2016/7/27.
 * application
 */
public class App extends Application {

    //public OKHttpUtils okHttpUtils;
    private static App instance;
    //private ACache aCache;
    private String accessToken;
    // 用户名和密码
    private String name;
    private String pwd;

    private String sex;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
        //SpeechUtility.createUtility(App.this, SpeechConstant.APPID + "=58f6c834");
        Fresco.initialize(this);
        JUtils.initialize(this);
        //生成文件夹
        JFileManager.getInstance().init(this, Dir.values());
        //aCache = ACache.get(instance);
        registerActivityLifecycleCallbacks(JActivityManager.getActivityLifecycleCallbacks());
    }

    public String getSex() {
        if (StringUtil.isNullOrEmpty(sex)) {
            return SharedPreUtil.getValue(instance, "sex", "");
        }
        return sex;
    }

    public void setSex(String sex) {
        if (!StringUtil.isNullOrEmpty(sex)) {
            SharedPreUtil.putValue(instance, "sex", sex);
            this.sex = sex;
        } else {
            SharedPreUtil.putValue(instance, "sex", "");
            this.sex = "";
        }
    }



    //文件目录列表
    public enum Dir {
        Object
    }
}
