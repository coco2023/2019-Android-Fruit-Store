package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExFileActivity extends AppCompatActivity  {
    Button saveBtn, getBtn;
    TextView txt;
    static final String fileName = "data.txt";
    String data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exfile);
        txt = (TextView) findViewById(R.id.txt);
        saveBtn = (Button) findViewById(R.id.btn_Save);
        getBtn = (Button) findViewById(R.id.btn_Get);

        saveBtn.setOnClickListener(new mClick11());
        getBtn.setOnClickListener(new mClick12());

        data = getString(R.string.hello);
        verifyStoragePermissions(this);
    }

    // Storage Permissions

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    //按钮事件
    class mClick11 implements View.OnClickListener {
        public void onClick(View arg0) {
            //获取外部设备
            String state = Environment.getExternalStorageState();
            //判断外部设备是否可用
            FileOutputStream fos = null;
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                //获取外部存储目录
                File exFilepPath = Environment.getExternalStorageDirectory();
                File file = new File(exFilepPath, fileName);
                try {
                    fos = new FileOutputStream(file);
                    fos.write(data.getBytes());
                    fos.close();
                    txt.setText("保存成功，文件名：" + fileName +"\n文件内容：\n" + data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //按钮事件
    class mClick12 implements View.OnClickListener {
        public void onClick(View arg0) {
            String state = Environment.getExternalStorageState();
            FileInputStream fis = null;
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File exFilePath = Environment.getExternalStorageDirectory();
                File file = new File(exFilePath, fileName);
                try {
                    fis = new FileInputStream(file);
                    byte[] buffer = new byte[fis.available()];
                    int bytes = fis.read(buffer);
                    String str = new String(buffer);
                    txt.setText("\n读取成功，文件名：" + fileName +"\n文件内容：\n" + str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}