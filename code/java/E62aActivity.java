package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class E62aActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e32);

        text = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        Bundle bundle = this.getIntent().getExtras();
        String string = bundle.getString("user_name");
        text.setText("用户"+string+"， 欢迎登录");

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, E62Activity.class);
        startActivity(intent);
    }
}
