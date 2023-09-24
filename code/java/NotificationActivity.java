package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    NotificationManager nManager;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String service = NOTIFICATION_SERVICE;
        nManager = (NotificationManager) getSystemService(service);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn1)
            showNotification(this);
        else if (v == btn2)
            nManager.cancelAll();
    }

    public void showNotification(Context context) {
//        高版本(Android 8)需要在系统设置里开启渠道"channel"，notification才能正常弹出
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "channel-id",
                    "channel-name",
                    NotificationManager.IMPORTANCE_HIGH);
            nManager.createNotificationChannel(notificationChannel);
        }

//        高版本在Builder()多加了一个参数channelId
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel-id");
        builder.setSmallIcon(R.drawable.music_logo)                                                        //设置小图标
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.music_logo))  //设置大图标
                .setContentTitle("这是一个通知的标题")
                .setContentText("这是一个通知的内容这是一个通知的内容")
                .setWhen(System.currentTimeMillis())                                                     //通知产生的时间
                .setAutoCancel(true);                                                                   //可取消
        Notification notification = builder.build();
        nManager.notify(0, notification);
    }

}
