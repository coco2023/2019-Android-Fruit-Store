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
public class E74Activity extends AppCompatActivity {
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
            paint.setStyle(Paint.Style.FILL); //设置填充样式
            paint.setStrokeWidth(10);         //设置画笔宽度
            RectF rect1 = new RectF(100, 100, 300, 300);  //rectF方法构造矩形
            canvas.drawArc(rect1, 30, 120, false, paint); //根据相应矩形构造弧线，设置参数弧的开始角度、持续角度以及无边

            RectF rect2 = new RectF(400, 100, 600, 300); //rectF方法构造矩形
            canvas.drawArc(rect2, 30, 120, true, paint); ////根据相应矩形构造扇形，设置参数弧的开始角度、持续角度以及有边
        }
    }
}
