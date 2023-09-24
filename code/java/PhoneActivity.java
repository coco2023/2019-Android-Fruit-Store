package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhoneActivity extends AppCompatActivity {
    Button btn_dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        btn_dial = (Button) findViewById(R.id.btn);
        btn_dial.setOnClickListener(new mClick());
    }

    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
//            通用资源标志符URI（Universal Resource Identifier,可用的资源等都可以用Uri来表示。
            Uri uri = Uri.parse("tel:18901001");
            Intent it = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(it);
        }
    }
}
