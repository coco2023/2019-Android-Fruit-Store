package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {
    static TextView txt;
    Button btn;
    int num;   //计数器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        txt = (TextView) findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.send);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        num++;
        String msg = "这是广播信息第" + Integer.toString(num) + "次发送";
        Log.i("info", msg);   //在logcat中显示

        Intent intent = new Intent();
        intent.setAction("MyBroad");     //设置action属性值

//        使用Bundle设置消息数据
        Bundle bundle = new Bundle();
        bundle.putString("count", msg);


        intent.putExtras(bundle);
        intent.setPackage("com.example.helloworld");     //新版本Android，发送静态广播，必须是定向广播，否则收不到。

//        发送广播消息
        sendBroadcast(intent);
    }
}
