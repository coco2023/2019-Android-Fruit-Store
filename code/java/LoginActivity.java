package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user_name,user_password;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);

        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        login_button = (Button) findViewById(R.id.login_button);

        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String  str = user_name.getText()+"，"+user_password.getText();
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
}
