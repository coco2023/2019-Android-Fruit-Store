package com.example.helloworld;

//import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends android.app.ListActivity implements AdapterView.OnItemClickListener {

    ListView list01;  //为了和前面的例子进行区分，这里换了一个对象名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //     setContentView(R.layout.activity_list);
        list01 = getListView();

        //data
        String[] data={"企业会话","办公邮件","企业财务信息查询"};
        //Adapter(Array)
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //view
        //设置列表项的头部
        TextView header=new TextView(this);
        header.setText("智慧校园");
        header.setTextSize(24);
        list01.addHeaderView(header);
        //设置列表项的底部
        TextView foot=new TextView(this);
        foot.setText("请选择");
        foot.setTextSize(24);
        list01.addFooterView(foot);

        list01.setAdapter(adapter);
        list01.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();   //该语句无法显示选中项的内容
        Toast.makeText(this,"您选中的是："+((TextView)view).getText(),Toast.LENGTH_SHORT).show();
    }
}
