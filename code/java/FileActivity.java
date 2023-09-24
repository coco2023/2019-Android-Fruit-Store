package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {
    Button btn_Save, btn_Get;
    TextView txt;
    static final String filename = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        txt = (TextView) findViewById(R.id.txt);
        btn_Save = (Button) findViewById(R.id.btn_Save);
        btn_Get = (Button) findViewById(R.id.btn_Get);


        btn_Save.setOnClickListener(new mClick11());
        btn_Get.setOnClickListener(new mClick12());
    }

    //存入file,写文件
    class mClick11 implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String str = getString(R.string.hello);
            FileOutputStream f_out;
            try {
                f_out = openFileOutput(filename, Context.MODE_PRIVATE);
                f_out.write(str.getBytes());
                f_out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            txt.setText("写文件"+filename+"\n" + str);
//            String path= Environment.getDataDirectory().getPath();
//            String path = getDir(filename, MODE_PRIVATE).getAbsolutePath();
//            String path = getFilesDir().getAbsolutePath();
//            txt.setText("写文件" + filename + "在目录" + path + "\n" + str);
        }
    }

    //取出file，读文件
    class mClick12 implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            FileInputStream f_in;
            try {
                f_in = openFileInput(filename);
                byte[] buffer = new byte[f_in.available()];   //available()取得文件总字节数
                f_in.read(buffer);
                String str = new String(buffer);
                txt.setText("读文件" + filename + "\n" + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}