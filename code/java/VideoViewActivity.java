package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {
    private VideoView mVideoView;
    private Button playBtn;
    //    对比 MediaPlayer ，自带进度条和进度控制，比MediaPlayer 更方便
    MediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);

        mVideoView = (VideoView) findViewById(R.id.video);
        playBtn = (Button) findViewById(R.id.playBtn);

//        mVideoView = new VideoView(this);    //调用构造器完成初始化,可省略
        mMediaController = new MediaController(this);    //调用构造器完成初始化

        playBtn.setOnClickListener(new mClick());
    }

    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //String path="/sdcard/test.3gp";
//            传统方法
//            String path =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/sample.3gp";
            String path = getExternalFilesDir(null) + "/sample.mp4";
            mVideoView.setVideoPath(path);
            mMediaController.setMediaPlayer(mVideoView);          //关联控制器与视频
            mMediaController.setPadding(0,0,0,100);                //设置mediaPlayer的位置left top right bottom
            mVideoView.setMediaController(mMediaController);      //关联视频与控制器
            mVideoView.start();
//            playBtn.setVisibility(8);//按钮不可见，//play.setEnabled(false);
        }
    }
}
