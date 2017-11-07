package com.example.kkkk.helloworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kkkk.helloworld.R;

public class loginActivity extends AppCompatActivity {
    ImageButton img;
    TextView findBtn;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        img= (ImageButton) findViewById(R.id.back);
        findBtn= (TextView) findViewById(R.id.forget_password);
        login= (Button) findViewById(R.id.btn_login);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(loginActivity.this, "check", Toast.LENGTH_SHORT).show();
            }
        });
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginActivity.this,findpwdActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
