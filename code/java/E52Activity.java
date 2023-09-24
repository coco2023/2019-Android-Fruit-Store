package com.example.helloworld;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class E52Activity extends Service {
    MediaPlayer play;

    public E52Activity() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //    重写 onCreate() 方法，创建后台服务
    @Override
    public void onCreate() {
        super.onCreate();

//        创建调用资源音乐文件对象
        try {
            play = MediaPlayer.create(this, R.raw.abc);
            play.setLooping(true);
            Toast.makeText(this, "创建后台服务...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "play error", Toast.LENGTH_LONG).show();
        }
    }

    //    重写 onStartCommand() 方法，启动后台服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        play.start();//开始播放音乐
        Toast.makeText(this, "启动后台服务程序，播放音乐...", Toast.LENGTH_LONG).show();
        return START_STICKY;  //如果service进程被kill掉，保留service的状态为开始状态，
    }

    //    重写 onDestroy() 方法，终止后台服务，并删除所有调用
    @Override
    public void onDestroy() {
        play.release();
        super.onDestroy();
        Toast.makeText(this, "销毁后台服务！", Toast.LENGTH_LONG).show();
    }
}
