package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DiaryDBActivity extends AppCompatActivity {
    Button createdb1, deletedb1, queryBtn1, deleteItemBtn1, insertBtn1;
    TextView txt;
    MySQLDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarydb);

        createdb1 = (Button) findViewById(R.id.createdb1);
        deletedb1 = (Button) findViewById(R.id.deleteDB1);
        queryBtn1 = (Button) findViewById(R.id.querydb1);
        deleteItemBtn1 = (Button) findViewById(R.id.deleteItemBtn1);
        insertBtn1 = (Button) findViewById(R.id.insertdb1);
        txt = (TextView) findViewById(R.id.txt1);

        createdb1.setOnClickListener(new mClick());
        deletedb1.setOnClickListener(new mClick());
        queryBtn1.setOnClickListener(new mClick());
        deleteItemBtn1.setOnClickListener(new mClick());
        insertBtn1.setOnClickListener(new mClick());

    }

    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == createdb1) {
                mydb = new MySQLDatabase();
                Toast.makeText(DiaryDBActivity.this, "数据库和数据表创建成功", Toast.LENGTH_SHORT).show();
            } else if (view == deletedb1) {
                deleteDatabase(Database_name);
                txt.setText("删除数据库成功");
                Toast.makeText(DiaryDBActivity.this, "数据库和数据表删除成功", Toast.LENGTH_SHORT).show();
            } else if (view == queryBtn1) {
                mydb.showItems();
            } else if (view == deleteItemBtn1) {
                mydb.deleteItem();
            } else if (view == insertBtn1) {
                mydb.insertItem();
            }
        }
    }


    private static final String Database_name = "eBook.db";    //数据库名
    private static final String TABLE_NAME = "diary";          //数据表名
    private static final String ID = "nid";                    //ID编号
    private static final String TITLE = "title";               //标题
    private static final String BODY = "body";                 //正文
    private static final int Database_Version = 1;            //数据库版本

    private class MySQLDatabase {
        SQLiteDatabase db;

        private MySQLDatabase() {
            try {
                int mode = Context.MODE_PRIVATE;
                //创建数据库
                db = openOrCreateDatabase(Database_name, mode, null);
                //SQL语句
                String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        ID + " INTEGER primary key autoincrement," +
                        TITLE + " text not null, " +
                        BODY + " text not null);";
                db.execSQL(DATABASE_CREATE);             //创建数据表
                txt.setText("创建数据库成功");
            } catch (SQLException e) {
                e.printStackTrace();
                txt.setText("创建数据库失败");
            }
        }

        //      插入两条数据
        private void insertItem() {
            try {
                ContentValues values = new ContentValues();
                values.put("TITLE", "Android");
                values.put("BODY", "真有意思");
                db.insert(TABLE_NAME, ID, values);
                values.put("TITLE", "城市");
                values.put("BODY", "发展真是迅速啊");
                db.insert(TABLE_NAME, ID, values);
                txt.setText("插入两条数据成功");
            } catch (SQLException e) {
                txt.setText("插入两条数据失败");
            }
        }

        //        删除符合条件的数据
        private void deleteItem() {
            try {
                db.delete(TABLE_NAME, " TITLE='城市'", null);
                txt.setText("删除数据成功");
            } catch (SQLException e) {
                txt.setText("删除数据失败");
            }
        }


        //        查询数据表
        private void showItems() {
            try {
                String[] col = {TITLE, BODY};
                Cursor cursor = db.query(TABLE_NAME, col, null, null, null, null, null);
                Integer num = cursor.getCount();
                String str = "";
                if (num > 0) {
                    cursor.moveToFirst();
                    str = "第一条记录：\n" + cursor.getString(0) + " " + cursor.getString(1);
                }
                String result = "共有" + Integer.toString(num) + "条记录," + str;
                txt.setText(result);
            } catch (SQLException e) {
                txt.setText("查询数据失败");
            }
        }
    }
}
