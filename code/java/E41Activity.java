package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class E41Activity extends AppCompatActivity {
    ImageButton Start, Stop, Quit;
    TextView txt;
    MediaPlayer mp;
    SeekBar seekBar;
    boolean isSeekBarChanging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e41);

//        关联控件
        Start = (ImageButton) findViewById(R.id.start);
        Stop = (ImageButton) findViewById(R.id.stop);
        Quit = (ImageButton) findViewById(R.id.quit);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        txt = (TextView) findViewById(R.id.txt);

//        为按钮设置事件监听
        Start.setOnClickListener(new mStart());
        Stop.setOnClickListener(new mStop());
        Quit.setOnClickListener(new mQuit());


//        对MediaPlayer进行初始化
//        涉及到文件操作 进行异常处理
        try {
//        对项目中的mp3音频进行处理
            mp = MediaPlayer.create(this, R.raw.abc);
            mp.setOnPreparedListener(new Mediaprepared());
            seekBar.setOnSeekBarChangeListener(new SeekBarChange());
            mp.setLooping(true);  //循环播放
        } catch (Exception e) {
            Toast.makeText(this, "play error", Toast.LENGTH_LONG).show();
        }
    }

    //    实现监听接口
    class mStart implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                if (!mp.isPlaying()) { //没有播放，此时click应该执行播放
                    mp.start();
                    Toast.makeText(E41Activity.this, "播放音乐", Toast.LENGTH_SHORT).show();
//                  更改按钮图标为暂停
                    Start.setImageResource(R.drawable.music_pause);
                } else {
                    mp.pause();
                    Toast.makeText(E41Activity.this, "暂停播放音乐", Toast.LENGTH_SHORT).show();
//                  更改按钮图标为暂停
                    Start.setImageResource(R.drawable.music_play);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class mStop implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            停止按钮事件，停止播放音乐，不是暂停
            mp.stop();
            Start.setImageResource(R.drawable.music_play);

            //            便于用户再次选择播放
            try {
                mp = MediaPlayer.create(E41Activity.this, R.raw.abc);
                mp.setLooping(true);
                Toast.makeText(E41Activity.this, "停止播放音乐", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class mQuit implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    class SeekBarChange implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            mp.seekTo(seekBar.getProgress());
        }
    }

    class Mediaprepared implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            seekBar.setMax(mp.getDuration());
            int m = mp.getDuration() / 1000 / 60;
            int s = mp.getDuration() / 1000 % 60;
            txt.setText("0" + m + ":" + s);
        }
    }


}

