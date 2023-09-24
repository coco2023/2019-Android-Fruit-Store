package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

//   ImageView图像浏览示例。
public class ImageViewActivity extends Activity
        implements OnClickListener {
    ImageView img;
    Button btn_last, btn_next;

    //    存放图片id的int数组
    private int[] imgs = {R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2};
    int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);

        img = (ImageView) findViewById(R.id.img);
        btn_last = (Button) findViewById(R.id.btn_last);
        btn_next = (Button) findViewById(R.id.btn_next);

        btn_last.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == btn_last) {
            if (index > 0)
                index--;
            else
                index = imgs.length - 1;
        }
        if (v == btn_next) {
            if (index < imgs.length - 1)
                index++;
            else
                index = 0;
        }
        img.setImageResource(imgs[index]);
    }
}
