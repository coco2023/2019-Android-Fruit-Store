package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_listview1);
        listView01 = (ListView) findViewById(R.id.listView01);
        //data
        String[] data={"企业会话","办公邮件","企业财务信息查询"};
        //Adapter(Array)
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //view
        listView01.setAdapter(adapter);
        listView01.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();   //该语句无法显示选中项的内容
        Toast.makeText(this,"您选中的是："+((TextView)view).getText(),Toast.LENGTH_SHORT).show();
    }
}
