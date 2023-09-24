package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText, editText2;
    TextView txt3, txt4;
    Button jsonBtn, arrayBtn, fileBtn;
    private static final String fileName = "weather.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        jsonBtn = (Button) findViewById(R.id.jsonBtn);
        arrayBtn = (Button) findViewById(R.id.arrayBtn);
        fileBtn = (Button) findViewById(R.id.fileBtn);

        jsonBtn.setOnClickListener(this);
        arrayBtn.setOnClickListener(this);
        fileBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == jsonBtn) {
            setJsonData();
        } else if (view == arrayBtn) {
            setArrayData();
        } else if (view == fileBtn) {
            getJsonFile();
        }
    }

    //解析单个JSON对象
    private void setJsonData() {
        try {
            JSONObject test = new JSONObject();
            test.put("城市", "深圳");
            test.put("气温", 30);
            String cs = test.getString("城市");
            int qw = test.getInt("气温");
            editText.setText(cs);
            editText2.setText(Integer.toString(qw));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析JSON数组
    private void setArrayData() {
        JSONObject p1, p2, p3;
        JSONArray weather;
        try {
//            String str = "{\"城市\":\"成都\", \"气温\":\"21-28度，小雨\"}";
//            String str2 = "{城市:成都,气温:21-28度，小雨}";//注意，英文的冒号和逗号是语法，中文的逗号是普通字符
            p1 = new JSONObject("{\"城市\":\"成都\", \"气温\":\"2-4度，小雨\"}");
            p2 = new JSONObject("{\"城市\":\"大理\", \"气温\":\"0-14度，多云\"}");
            p3 = new JSONObject("{\"城市\":\"拉萨\", \"气温\":\"-9-5度，多云\"}");
            weather = new JSONArray();
            weather.put(p1);
            weather.put(p2);
            weather.put(p3);
            txt3.setText("");
            for (int i = 0; i < weather.length(); i++) {
                JSONObject ob = weather.getJSONObject(i);
                txt3.append(ob.getString("城市") + ": " + ob.getString("气温") + "\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析JSON文件
    private void getJsonFile() {
        AssetManager assetManager = getAssets(); //获得assets资源管理器
        String strs = "";
        try {
            InputStreamReader reader = new InputStreamReader(assetManager.open(fileName), "utf-8");
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            StringBuilder stringBuilder = new StringBuilder(); //可变的字符序列
            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
            }
            strs = stringBuilder.toString();
            buffer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(strs);
            String city, date, high, fx, low, type;
            city = jsonObject.getString("city");
            date = jsonObject.getString("date");
            high = jsonObject.getString("high");
            fx = jsonObject.getString("fx");
            low = jsonObject.getString("low");
            type = jsonObject.getString("type");
            txt4.setText(city + ":" + date + "," + high + "," + fx + "," + low + "," + type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}