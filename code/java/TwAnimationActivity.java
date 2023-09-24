package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

//   可以旋转、缩放、淡入/淡出、移动的补间动画程序。
public class TwAnimationActivity extends AppCompatActivity {
    private Button rotateButton = null;
    private Button scaleButton = null;
    private Button alphaButton = null;
    private Button translateButton = null;
    private ImageView image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twanimation);

        rotateButton = (Button) findViewById(R.id.rotateButton);
        scaleButton = (Button) findViewById(R.id.scaleButton);
        alphaButton = (Button) findViewById(R.id.alphaButton);
        translateButton = (Button) findViewById(R.id.translateButton);
        image = (ImageView) findViewById(R.id.image);

        rotateButton.setOnClickListener(new RotateButtonListener());
        scaleButton.setOnClickListener(new ScaleButtonListener());
        alphaButton.setOnClickListener(new AlphaButtonListener());
        translateButton.setOnClickListener(new TranslateButtonListener());
    }

    class RotateButtonListener implements OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1000);
            animationSet.addAnimation(rotateAnimation);
            image.startAnimation(animationSet);
        }
    }

    class ScaleButtonListener implements OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0, 0.1f, 0, 0.1f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(1000);
            animationSet.addAnimation(scaleAnimation);
            image.startAnimation(animationSet);
        }
    }

    class AlphaButtonListener implements OnClickListener {
        public void onClick(View v) {
//            创建一个AnimationSet对象，参数为Boolean型，
            AnimationSet animationSet = new AnimationSet(true);
//             创建一个AlphaAnimation对象，参数从完全不透明度，到完全透明
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
//              设置动画执行的时间
            alphaAnimation.setDuration(500);
//              将alphaAnimation对象添加到AnimationSet当中
            animationSet.addAnimation(alphaAnimation);
//              使用ImageView的startAnimation方法执行动画
            image.startAnimation(animationSet);
        }
    }

    class TranslateButtonListener implements OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            TranslateAnimation translateAnimation =
                    new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
            translateAnimation.setDuration(1000);
            animationSet.addAnimation(translateAnimation);
            image.startAnimation(animationSet);
        }
    }
}