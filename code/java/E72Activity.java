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
public class E72Activity extends AppCompatActivity {
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
//            canvas.drawRect(50, 50, 200, 200, paint); //绘制矩形
            RectF rect = new RectF(50, 50, 200, 200); //RectF方法构造构造圆角矩形
            canvas.drawRoundRect(rect, 20, 20, paint); //设置圆角矩形参数
        }
    }
}
