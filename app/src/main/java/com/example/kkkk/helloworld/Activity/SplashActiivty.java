package com.example.kkkk.helloworld.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.kkkk.helloworld.R;

public class SplashActiivty extends AppCompatActivity {
    Splashhandler splashhandler;
    Handler x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_actiivty);
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (!Settings.System.canWrite(getBaseContext()))
            { Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getBaseContext().getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(intent);
            } else { //有了权限，具体的动作
                // Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress); data2 = intToString(progress, 255); tvSunlightValue.setText(data2 + "%");
               // Toast.makeText(SplashActiivty.this, "权限已经开启", Toast.LENGTH_SHORT).show();
            }
        }
        showSplash();
    }

    class Splashhandler implements Runnable {

        public void run() {
            Intent intent = new Intent(SplashActiivty.this, loginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void showSplash() {

        x = new Handler();
        splashhandler = new Splashhandler();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(x!=null&&splashhandler!=null){
            x.removeCallbacks(splashhandler);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(x!=null&&splashhandler!=null){
            x.postDelayed(splashhandler, 1000);
        }
    }
}
