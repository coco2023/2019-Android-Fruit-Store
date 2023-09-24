package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class E23Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e22);

        TextView text1 = (TextView) findViewById(R.id.textView);
        Button btn1 = (Button) findViewById(R.id.button);

        String string1 = getString(R.string.text_textview);
        String string2 = getString(R.string.text_button);
        text1.setText(string1);
        btn1.setText(string2);
    }
}