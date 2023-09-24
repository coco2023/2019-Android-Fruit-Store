package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteActivity extends AppCompatActivity {
    Button createdb, deletedb;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        createdb = (Button) findViewById(R.id.createdb);
        deletedb = (Button) findViewById(R.id.deletedb);
        txt = (TextView) findViewById(R.id.txt);

        createdb.setOnClickListener(new mClick());
        deletedb.setOnClickListener(new mClick());
    }

    class mClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == createdb) {
                MySQLDatabase mdb = new MySQLDatabase();  //自己构造的数据库类
                Toast.makeText(SQLiteActivity.this, "数据库和数据表创建成功", Toast.LENGTH_SHORT).show();
            } else if (view == deletedb) {
                deleteDatabase(MySQLDatabase.Database_name);
                txt.setText("删除数据库成功");
                Toast.makeText(SQLiteActivity.this, "数据库和数据表删除成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class MySQLDatabase {
        private static final String Database_name = "eBook.db";  //数据库名
        private String TABLE_NAME = "diary"; //数据表名
        private String ID = "nid";           //ID编号
        private String TITLE = "title";      //标题
        private String BODY = "body";         //正文

        private MySQLDatabase() {
            try {
                int mode = Context.MODE_PRIVATE;
                //创建数据库
                SQLiteDatabase db = openOrCreateDatabase(Database_name, mode, null);
                //SQL语句
                String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        ID + " INTEGER primary key autoincrement," +
                        TITLE + " text not null, " +
                        BODY + " text not null);";    //注意，当执行SQL命令时要以分号；结尾
                db.execSQL(DATABASE_CREATE);          //创建数据表
                txt.setText("创建数据库成功");
            } catch (SQLException e) {
                e.printStackTrace();
                txt.setText("创建数据库失败");
            }
        }
    }
}

