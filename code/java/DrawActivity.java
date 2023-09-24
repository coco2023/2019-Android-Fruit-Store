package com.example.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//   绘制几何图形示例。
public class DrawActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestView testView = new TestView(this);
        setContentView(testView);
    }

    private class TestView extends View {
        private int width = 550;
        private int height = 750;
        private Paint paint;

        public TestView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint = new Paint();
            paint.setColor(Color.BLUE);     //设置画笔为蓝色
            paint.setStrokeWidth(6);        //设置画笔宽度
            rotate2(canvas);                 //可测试rotate2(canvas)方法
        }

        //绘制直线构成的几何图形
        private void rotate(Canvas canvas) {
            canvas.translate(width, height);          //移动画布
            int bc = 2;                               //线条初始长度
            for (int i = 1; i < 100; i++) {
                canvas.drawLine(0, 0, bc, bc, paint);    //画线
                canvas.translate(bc, bc);                              //移动画布
                canvas.rotate(91);                            //旋转画布
                bc += 5;
            }
        }

        //给直线设置4种不同颜色
        private void rotate2(Canvas canvas) {
            canvas.drawColor(Color.GRAY);           //设置背景为黑色BLACK
            canvas.translate(width, height);        //移动画布
            int cc[] = {
                    Color.RED,
                    Color.BLUE,
                    Color.GREEN,
                    Color.MAGENTA
            };
            int bc = 2;//线条初始长度
            for (int i = 1; i < 100; i++) {
                paint.setColor(cc[i % 4]);                           //设置颜色
                canvas.drawLine(0, 0, bc, bc, paint);  //画线
                canvas.translate(bc, bc);                             //移动画布
                canvas.rotate(91);                           //旋转画布
                bc += 5;
            }
        }
    }
}
