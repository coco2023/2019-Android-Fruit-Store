package com.example.helloworld;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmManagerActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    Intent intent;
    PendingIntent sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmmanager);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(new mClick());
        btn2.setOnClickListener(new mClick());
        btn3.setOnClickListener(new mClick());
    }


    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    timing();
                    break;
                case R.id.btn2:
                    cycle();
                    break;
                case R.id.btn3:
                    cancel();
                    break;
            }
        }
    }


    //         定时：15秒后发送一个广播，广播接收后Toast提示定时操作完成
    void timing() {
        intent = new Intent(AlarmManagerActivity.this, AlarmReceiver.class);
        sender = PendingIntent.getBroadcast(AlarmManagerActivity.this, 0, intent, 0);

//        设定一个15秒后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());  //从1970.1.1号以来的时间
        calendar.add(Calendar.SECOND, 15);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        //RTC_WAKEUP表示闹钟在睡眠状态下会唤醒系统并执行提示功能
        alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        Toast.makeText(AlarmManagerActivity.this, "15秒后alarm开启", Toast.LENGTH_LONG).show();
    }

    //         定义循环：每10秒发送一个广播，广播接收后Toast提示定时操作完成
    void cycle() {
        Intent intent = new Intent(AlarmManagerActivity.this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(AlarmManagerActivity.this, 0, intent, 0);
//        开始时间
        long firstime = SystemClock.elapsedRealtime();   //从设备开机到现在的时间，单位毫秒，含系统深度睡眠时间,相对时间
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        //        10秒一个周期，不停的发送广播
        //ELAPSED_REALTIME_WAKEUP表示闹钟在睡眠状态下会唤醒系统并执行提示功能
        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 10 * 1000, sender);
        Toast.makeText(AlarmManagerActivity.this, "每10秒发一次广播", Toast.LENGTH_LONG).show();
        assert am != null;
    }


    //     取消周期发送信息
    void cancel() {
        Intent intent = new Intent(AlarmManagerActivity.this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(AlarmManagerActivity.this, 0, intent, 0);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.cancel(sender);
    }

    private class AlarmReceiver {
    }
}
