package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3;
    Toast toast;
    LinearLayout toastView;
    ImageView icon1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        //    关联组件
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button3);
        btn3 = (Button) findViewById(R.id.button4);

//        设置监听器
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btn1) {
//            默认方式消息
            Toast.makeText(getApplicationContext(), "默认方式Toast", Toast.LENGTH_SHORT).show();
        }
        else if (v == btn2) {
//            自定义方式显示消息
            toast = Toast.makeText(getApplicationContext(), "自定义Toast的位置", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else if (v == btn3) {
//            显示带图标的消息
            toast = Toast.makeText(getApplicationContext(),"带图标的Toast",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 80);
            toastView = (LinearLayout) toast.getView();
            icon1 = new ImageView(this);
            icon1.setImageResource(R.drawable.user);
            toastView.addView(icon1, 0);
            toast.show();
        }

    }
}
