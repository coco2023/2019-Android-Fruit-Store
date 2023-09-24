package com.example.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase {
    private static final String Database_name = "eBook.db";  //数据库名
    private static final String TABLE_NAME = "diary";       //数据表名
    private static final String ID = "nid";                 //ID编号
    private static final String TITLE = "title";            //标题
    private static final String BODY = "body";              //正文
    private static final int Database_Version = 1;          //数据库版本
    private SQLiteDatabase db;
    private Context context;
    private MyDatabaseHelper myOpenHelper;


    //    使用SQLiteOpenHelper类
    private class MyDatabaseHelper extends SQLiteOpenHelper {
        public MyDatabaseHelper(Context context) {
            super(context, Database_name, null, Database_Version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }

    /*
     * 创建数据库
     */
    public void CreateDB(Context c) {
        context = c;
//        使用SQLiteOpenHelper操作数据库
        myOpenHelper = new MyDatabaseHelper(context);      //对创建数据库做了封装
        try {
            int mode = Context.MODE_PRIVATE;
            //创建数据库
            db = context.openOrCreateDatabase(Database_name, mode, null);
            //SQL语句
            String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    ID + " INTEGER primary key autoincrement," +
                    TITLE + " text not null, " +
                    BODY + " text not null);";
            db.execSQL(DATABASE_CREATE);          //创建数据表
            Log.i("SQLite", "创建数据库成功");
        } catch (SQLException e) {
            Log.i("SQLite", "创建数据库失败");
        }
    }

    /*
     * 删除数据库
     */
    public void deleteDB() {
        try {
            db.close();
            context.deleteDatabase(Database_name);
            Log.i("SQLite", "删除数据库成功");
        } catch (SQLException e) {
            Log.i("SQLite", "删除数据库失败");
        }
    }

    /*
     * 插入两条数据
     */
    public void insertItem() {
        try {
            ContentValues values = new ContentValues();
            values.put("TITLE", "Android");
            values.put("BODY", "发展真是迅速啊");
            db.insert(TABLE_NAME, ID, values);
            values.put("TITLE", "城市");
            values.put("BODY", "发展真是迅速啊");
            db.insert(TABLE_NAME, ID, values);
            Log.i("SQLite", "插入两条数据成功");
        } catch (SQLException e) {
            Log.i("SQLite", "插入两条数据失败");
        }
    }

    /*
     * 删除符合条件的数据
     */
    public void deleteItem() {
        try {
            db.delete(TABLE_NAME, " TITLE='城市'", null);
            Log.i("SQLite", "删除数据成功");
        } catch (SQLException e) {
            Log.i("SQLite", "删除数据失败");
        }
    }

    /*
     * 查询数据表
     */
    public String showItems() {
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
            return result;
        } catch (SQLException e) {
            Log.i("SQLite", "查询数据失败");
        }
        return null;
    }
}
