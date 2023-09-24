package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//  游戏中触屏事件示例
public class TouchActivity extends Activity {
    TestView testView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testView = new TestView(this);
        setContentView(testView);

        testView.setOnTouchListener(new onTouch());
    }

    class  onTouch implements View.OnTouchListener{
        int x,y;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x = (int) event.getX();
                y = (int) event.getY();
                testView.getXY(x, y);
                setContentView(testView);
                return true;
            }
            return false;
        }
    }

    private class TestView extends View {
        Bitmap mBitmap,mAlice;
        int AliceW,AliceH;
        public TestView(Context context) {
            super(context);
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            mAlice = BitmapFactory.decodeResource(getResources(), R.drawable.alice);
            AliceW=mAlice.getWidth();       //获取bitmap大小
            AliceH=mAlice.getHeight();
            Log.i("Alice", Integer.toString(AliceW));
            Log.i("Alice", Integer.toString(AliceH));
        }
        int x = 600, y = 600;         //动物的初始坐标
        void getXY(int dongx, int dongy) {
            if((dongx<AliceW)&&(dongy<AliceH)){          //动物不能移动到Alice的图像区域内
                Toast.makeText(TouchActivity.this,"不能碰到Alice", Toast.LENGTH_SHORT).show();
            }else{
                x = dongx;
                y = dongy;
            }
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(mAlice, 0, 0, null);
            canvas.drawBitmap(mBitmap, x, y, null);
        }
    }
}
