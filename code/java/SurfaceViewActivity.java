package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class SurfaceViewActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    SurfaceView mSurfaceView;
    Button playBtn, pauseBtn, stopBtn;
    String path;
    SurfaceHolder sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfaceview);

//        动态申请授权访问 sdcard, 项目只在第一次访问sdcard时才需要
//        verifyStoragePermissions(this);

//        关联控件
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        playBtn = (Button) findViewById(R.id.play);
        pauseBtn = (Button) findViewById(R.id.pause);
        stopBtn = (Button) findViewById(R.id.stop);

//        传统读取SD卡文件方法
//        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/sample.3gp";
        path = getExternalFilesDir(null) + "/sample.mp4";

        mMediaPlayer = new MediaPlayer();
        playBtn.setOnClickListener(new mStart());
        pauseBtn.setOnClickListener(new mPause());
        stopBtn.setOnClickListener(new mStop());
    }

    //    开始
    class mStart implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                mMediaPlayer.reset();
//                为播放器对象设置用于显示视频内容、代表屏幕描绘的控制器
                mMediaPlayer.setDataSource(path);     //设置数据源
                sh = mSurfaceView.getHolder();
                mMediaPlayer.setDisplay(sh);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                playBtn.setEnabled(false);
            } catch (Exception e) {
                Log.i("MediaPlay err", "MediaPlay err");
            }
        }
    }

    //    暂停
    class mPause implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                    pauseBtn.setText("暂停");
                } else {
                    mMediaPlayer.pause();
                    pauseBtn.setText("播放");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //    停止
    class mStop implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mMediaPlayer.stop();
            playBtn.setEnabled(true);
        }
    }

}
