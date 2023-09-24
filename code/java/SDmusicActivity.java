package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class SDmusicActivity extends AppCompatActivity {
    ImageButton Start, Stop;
    MediaPlayer mp;
    String str;

    //    动态申请存储操作权限
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
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Start = (ImageButton) findViewById(R.id.start);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdmusic);
        verifyStoragePermissions(this);

        Start = (ImageButton) findViewById(R.id.start);
        Stop = (ImageButton) findViewById(R.id.stop);
        Start.setOnClickListener(new mStart());
        Stop.setOnClickListener(new mStop());

        mp = new MediaPlayer();
        try {
//            传统的获取mp3文件路径方法
//            str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/abc.mp3";

//
//            在Android 10中Environment.getExternalStoragePublicDirectory不再被支持
//            可用getExternalFilesDir来实现
            str = getExternalFilesDir(null)+"/abc.mp3";
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            mp.setDataSource(str);
            mp.prepare();
            mp.setLooping(true);
        } catch (IOException e) {
            Toast.makeText(this, "读取SD上的abc.mp3出错", Toast.LENGTH_LONG).show();
        }

    }

    //    实现监听接口
    class mStart implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                if (!mp.isPlaying()) { //没有播放，此时click应该执行播放
                    mp.start();
                    Toast.makeText(SDmusicActivity.this, "播放音乐", Toast.LENGTH_SHORT).show();
//                  更改按钮图标为暂停
                    Start.setImageResource(R.drawable.music_pause);
                } else {
                    mp.pause();
                    Toast.makeText(SDmusicActivity.this, "暂停播放音乐", Toast.LENGTH_SHORT).show();
//                  更改按钮图标为暂停
                    Start.setImageResource(R.drawable.music_play);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //    点击停止
    class mStop implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            停止按钮事件，停止播放音乐
            mp.reset();

//            便于用户再次选择播放
            try {
                mp = new MediaPlayer();
                mp.setDataSource(str);
                mp.prepare();
                mp.setLooping(true);   // 设置循环播放
                Toast.makeText(SDmusicActivity.this, "停止播放音乐", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Start.setImageResource(R.drawable.music_play);
        }
    }
}
