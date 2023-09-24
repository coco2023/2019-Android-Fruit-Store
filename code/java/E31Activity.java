package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class E31Activity extends AppCompatActivity {
    EditText user_name;
    EditText user_password;
    Button login_button;
    Button quit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e31);

        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        login_button = (Button) findViewById(R.id.login_button);
        quit_button = (Button) findViewById(R.id.quit_button);

        login_button.setOnClickListener(new ButtonListener());
        quit_button.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_button:{
                    String pwd = "123456";
                    String password = user_password.getText().toString();
                    Intent intent1 = new Intent(E31Activity.this, E32Activity.class);
                    Intent intent2 = new Intent(E31Activity.this, E33Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("user_name", user_name.getText().toString());
                    intent1.putExtras(bundle);
                    if (password.equals(pwd)) {
                        startActivity(intent1);
                    }
                    else {
                        startActivity(intent2);
                    }
                }
                case R.id.quit_button:{
                    finish();
                }

            }
        }
    }
}
