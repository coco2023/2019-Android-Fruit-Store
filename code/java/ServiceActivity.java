package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    Button startbtn, stopbtn;
    static TextView txt;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        txt = (TextView) findViewById(R.id.txt);
        startbtn = (Button) findViewById(R.id.btn1);
        stopbtn = (Button) findViewById(R.id.btn2);

        startbtn.setOnClickListener(this);
        stopbtn.setOnClickListener(this);

//        创建Intent对象
        intent = new Intent(ServiceActivity.this, MusicService.class);
    }

    @Override
    public void onClick(View v) {
        if (v == startbtn) {
            this.startService(intent);
            txt.setText("启动后台服务程序");
        } else if (v == stopbtn) {
            this.stopService(intent);
            txt.setText("销毁后台服务程序");
        }
    }
}
