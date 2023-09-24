package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text3;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn2 = (Button) findViewById(R.id.button2);
        text3 = (TextView) findViewById(R.id.textView3);


//        String str = this.getIntent().getExtras().getString("editText1");  //下面两句可以用此一句代替
        Bundle bundle2 = this.getIntent().getExtras();
        String string2 = bundle2.getString("editText1");  //注意editText1需要与前一界面中设置的Bundle中的键值对匹配
        text3.setText(string2);

        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent2 = new Intent();
        intent2.setClass(this,MainActivity.class);
        startActivity(intent2);
//        可以用一行代码实现
//        startActivity(new Intent(this, MainActivity.class));
    }
}