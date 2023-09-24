package com.example.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//   绘制几何图形示例。
public class E75Activity extends AppCompatActivity {
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
            paint.setColor(Color.BLACK); //设置画笔为黑色
            paint.setStyle(Paint.Style.STROKE); //设置填充样式
            paint.setStrokeWidth(10);         //设置画笔宽度
            paint.setTextSize(50); //设置文本大小
//            canvas.drawText("H",100,100,paint); //设置文本坐标
//            canvas.drawText("e",150,150,paint);
//            canvas.drawText("l",200,200,paint);
//            canvas.drawText("l",250,250,paint);
//            canvas.drawText("o",300,300,paint);

            Path path = new Path();
            path.lineTo(100, 100); //屏幕左上角（0,0）到（100,100）画一条直线
            path.lineTo(100, 400); //(100, 100)到(100, 400)画一条直线
            path.rLineTo(400, -300); //以(100, 400)为起始点,偏移量为(400, -300)画一条直线
            canvas.drawPath(path, paint); //绘制路径
        }
    }
}
