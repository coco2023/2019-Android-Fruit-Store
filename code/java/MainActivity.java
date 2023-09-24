package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button1);
        edit1 = (EditText)findViewById(R.id.editText1) ;

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        //最简单的跳转方式，不传递数据
//        Intent intent1 = new Intent(this,SecondActivity.class);
//            //也可以用一句完成
////        startActivity(new Intent(this, SecondActivity.class));

        //隐式Intent
//      Uri uri =Uri.parse("http://www.baidu.com");
//      Intent intent1 = new Intent(Intent.ACTION_VIEW,uri);

//        Bundle存储数据，然后利用Intent跳转
        Intent intent1 = new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("editText1", edit1.getText().toString());    //键值对
        intent1.putExtras(bundle);       //让Intent携带Bundle中的数据

        startActivity(intent1);       //启动intent，界面跳转


    }
}
