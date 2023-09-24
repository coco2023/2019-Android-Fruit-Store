package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class E27Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e27);

        Button btn1 = (Button) findViewById(R.id.button);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String  str = "You hace clicked a button!";
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
}