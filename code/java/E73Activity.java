package com.example.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//   绘制几何图形示例。
public class E73Activity extends AppCompatActivity {
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
            paint = new Paint(); //创建画笔
            paint.setColor(Color.BLACK); //设置画笔为蓝色
            paint.setStyle(Paint.Style.STROKE); //设置填充样式
            paint.setStrokeWidth(10);         //设置画笔宽度
//            RectF rectF = new RectF(50, 50, 350, 250); //rectF方法构造矩形
//            canvas.drawOval(rectF, paint); //根据相应矩形构造椭圆
            canvas.drawCircle(150, 150, 100, paint);//构造圆形，设置圆心与半径
        }
    }
}
