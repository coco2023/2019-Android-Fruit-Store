package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//应用SharedPreferences对象保存一个客户的联系电话。
public class SharedActivity extends AppCompatActivity {
    private EditText name, tel;
    private Button SaveBtn, GetBtn;
    private SharedPreferences sp;      //声明SharedPreferenced对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        name = (EditText) findViewById(R.id.name);
        tel = (EditText) findViewById(R.id.tel);
        SaveBtn = (Button) findViewById(R.id.btn_Save);
        GetBtn = (Button) findViewById(R.id.btn_Get);
    }

    public void Click(View view) {
        //获取SharedPreferenced对象
        sp = getSharedPreferences("phoneBook", Context.MODE_PRIVATE);
        switch (view.getId()) {
            case R.id.btn_Save:
                //获取到edit对象
                SharedPreferences.Editor edit = sp.edit();
                //通过edit对象写入数据
                edit.putString("name", name.getText().toString().trim());
                edit.putString("phone", tel.getText().toString().trim());
                //提交数据存入到XML文件中
                edit.commit();
                Toast.makeText(SharedActivity.this, "保存成功！", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_Get:
                //取出数据，getString()第二个参数为默认值，如不存在该key，则返回默认值
                name.setText(sp.getString("name", "Null"));
                tel.setText(sp.getString("phone", "Null"));
                Toast.makeText(SharedActivity.this, "提取成功！", Toast.LENGTH_LONG).show();
                break;
        }
    }
}