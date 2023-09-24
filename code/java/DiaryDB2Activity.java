package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DiaryDB2Activity extends AppCompatActivity {

    Button createdb, deletedb, queryBtn, deleteItemBtn, insertBtn;
    TextView txt;
    MyDatabase myc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarydb);

        createdb = (Button) findViewById(R.id.createdb1);
        deletedb = (Button) findViewById(R.id.deleteDB1);
        queryBtn = (Button) findViewById(R.id.querydb1);
        deleteItemBtn = (Button) findViewById(R.id.deleteItemBtn1);
        insertBtn = (Button) findViewById(R.id.insertdb1);
        txt = (TextView) findViewById(R.id.txt1);

        createdb.setOnClickListener(new mClick());
        deletedb.setOnClickListener(new mClick());
        queryBtn.setOnClickListener(new mClick());
        deleteItemBtn.setOnClickListener(new mClick());
        insertBtn.setOnClickListener(new mClick());

        myc = new MyDatabase();
    }

    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == createdb) {                  // 创建数据库
                myc.CreateDB(DiaryDB2Activity.this);
                txt.setText("创建数据库成功");
            } else if (view == deletedb) {           // 删除数据库
                myc.deleteDB();
                txt.setText("删除数据库成功");
            } else if (view == queryBtn) {            // 查询记录
                txt.setText(myc.showItems());
            } else if (view == deleteItemBtn) {        // 删除Title为城市的记录
                myc.deleteItem();
                txt.setText("删除城市记录成功");
            } else if (view == insertBtn) {             //  添加两条记录
                myc.insertItem();
                txt.setText("添加数据记录成功");
            }
        }
    }

}
