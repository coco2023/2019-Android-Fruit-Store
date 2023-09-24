package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

//    用ImageSwitcher展示相册示例。
public class ImageShowActivity extends AppCompatActivity  implements ViewSwitcher.ViewFactory {
    private Integer[] mThumbIds = {
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7};

    private Integer[] mImageIds = {
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7};

    private ImageSwitcher mSwitcher;
    private HorizontalScrollView hsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageshow);

        mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);

        mSwitcher.setFactory(this);
        mSwitcher.setImageResource(mImageIds[0]);     //初始化时显示，必须放在工厂后面，否则会报NullPointerException
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));    //设置动画
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));  //设置动画

        hsv = (HorizontalScrollView) findViewById(R.id.gallery);
        initView();
    }

    public void initView() {
        final LinearLayout layout = new LinearLayout(this);
        //添加子View，并为小图设置点击事件处理程序
        for (int i = 0; i < mThumbIds.length; i++) {
            Log.i("mThumbIds", Integer.toString(i));
            ImageView img = new ImageView(this);
            img.setId(i);
            img.setImageResource(mThumbIds[i]);
            layout.addView(img);
            img.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
//                    设置资源, 用v.getId()获取编号ID
                    mSwitcher.setImageResource(mImageIds[v.getId()]);    //为ImageSwitcher设置大图
                }
            });
        }
        hsv.addView(layout);
    }

    @Override
    public View makeView() {
        ImageView img = new ImageView(this);
        //设置背景色为黑色
        img.setBackgroundColor(0xFF000000);
        //设置图片显示的缩放方式
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //设置显示的图片在相对容器的填充方式
        img.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return img;
    }
}