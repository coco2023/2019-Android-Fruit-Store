package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class E61Activity extends AppCompatActivity {
    private static final String DATABASE_NAME = "mydb.db";  //数据库名称
    private static final int DATABASE_VERSION = 1;          //数据库版本号
    private static final String TABLE_NAME = "user";        //表名
    private static final String ID = "nid";                 //ID编号
    private static final String NAME = "name";              //标题
    private static final String PASSWORD = "password";      //正文
    DatebaseHelper databaseHelper;
    MySQLDatabase mydb;
    SQLiteDatabase db;
    EditText user_name;
    EditText user_password;
    Button login_button;
    Button quit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e61);

        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        login_button = (Button) findViewById(R.id.login_button);
        quit_button = (Button) findViewById(R.id.quit_button);

        login_button.setOnClickListener(new LoginListener());
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    class LoginListener implements View.OnClickListener {
        public void onClick(View v) {
            String nameString = user_name.getText().toString();
            String passString = user_password.getText().toString();
            isUserinfo(nameString,passString);
        }
    }

    public Boolean isUserinfo(String name,String pass)
    {
        String nameString=name;
        String passString=pass;
        try{
            Intent intent1 = new Intent(E61Activity.this, E61aActivity.class);
            //调用DBOpenHelpe
            mydb = new MySQLDatabase();
            databaseHelper = new DatebaseHelper(E61Activity.this, DATABASE_NAME, null, DATABASE_VERSION);
            //获取可写的数据库对象
            db = databaseHelper.getWritableDatabase();
            //根据输入的账号/密码利用游标在数据库中进行查询
            Cursor cursor = db.query(TABLE_NAME, new String[]{"name", "password"}, "name=?", new String[]{nameString}, null, null, "password");
            while (cursor.moveToNext()) {
                String password = cursor.getString(cursor.getColumnIndex("password"));
                //如果查询到对应的账号/密码
                if (passString.equals(password)) {
                    //利用Bundle携带用户名至跳转页面
                    Bundle bundle = new Bundle();
                    bundle.putString("user_name", user_name.getText().toString());
                    intent1.putExtras(bundle);
                    //intent对象实现跳转
                    startActivity(intent1);
                    break;
                }
                //如果没有查询到数据
                else {
                    Toast.makeText(this, "用户名或密码不正确",Toast.LENGTH_LONG).show();
                    break;
                }
            }

        }catch(SQLiteException e){

        }
        return false;
    }

    private class MySQLDatabase {
        SQLiteDatabase db;

        private MySQLDatabase() {
            try {
                int mode = Context.MODE_PRIVATE;
                //创建数据库
                db = openOrCreateDatabase(DATABASE_NAME, mode, null);
                //SQL语句
                String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        ID + " INTEGER primary key autoincrement," +
                        NAME + " text not null, " +
                        PASSWORD + " text not null);";
                db.execSQL(DATABASE_CREATE);             //创建数据表
                db.execSQL("insert into user (name,password) values(?,?)", new String[]{"tom", "123456"});
                db.execSQL("insert into user (name,password) values(?,?)", new String[]{"mary", "123456"}); //插入两条记录
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public class DatebaseHelper extends SQLiteOpenHelper {
        public DatebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        //onCreate抽象方法，数据库创建时自动执行
        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        //onUpgrade抽象方法，数据库的版本发生变化时，会自动执行
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
