package com.example.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//   绘制几何图形示例。
public class E71Activity extends AppCompatActivity {
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
//            canvas.drawPoint(50, 50, paint); //画点，设置点的XY坐标
//            canvas.drawLine(50, 50, 250, 250, paint); //画线，设置起始点与结束点坐标
            float []pts={50,50,250,250,250,250,350,250,350,250,300,400}; //设置点集合
            canvas.drawLines(pts, paint); //根据点集合画多条线段
        }
    }
}
