package com.example.administrator.alltest.jsonparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.alltest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JsonActivity extends AppCompatActivity {

    private static String JSON="{\\n\" +\n" +
            "            \"    \\\"resultcode\\\": \\\"200\\\",\\n\" +\n" +
            "            \"    \\\"reason\\\": \\\"SUCCESSED!\\\",\\n\" +\n" +
            "            \"    \\\"result\\\": [\\n\" +\n" +
            "            \"        {\\n\" +\n" +
            "            \"            \\\"city\\\": \\\"苏州\\\",  /*城市*/\\n\" +\n" +
            "            \"            \\\"PM2.5\\\": \\\"73\\\",  /*PM2.5指数*/\\n\" +\n" +
            "            \"            \\\"AQI\\\": \\\"98\\\",    /*空气质量指数*/\\n\" +\n" +
            "            \"            \\\"quality\\\": \\\"良\\\", /*空气质量*/\\n\" +\n" +
            "            \"            \\\"PM10\\\": \\\"50\\\",/*PM10*/\\n\" +\n" +
            "            \"            \\\"CO\\\": \\\"0.79\\\",  /*一氧化碳*/\\n\" +\n" +
            "            \"            \\\"NO2\\\": \\\"65\\\",  /*二氧化氮*/\\n\" +\n" +
            "            \"            \\\"O3\\\": \\\"28\\\",    /*臭氧*/\\n\" +\n" +
            "            \"            \\\"SO2\\\": \\\"41\\\",  /*二氧化硫*/\\n\" +\n" +
            "            \"            \\\"time\\\": \\\"2014-12-26 11:48:40\\\"/*更新时间*/  \\n\" +\n" +
            "            \"        }\\n\" +\n" +
            "            \"    ],\\n\" +\n" +
            "            \"    \\\"error_code\\\": 0\\n\" +\n" +
            "            \"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.json_btn)
    public void onClick(){
        try {
            JSONObject jsonObject=new JSONObject(JSON);
            String resultcode=jsonObject.getString("resultcode");
            Log.e("json", "resultcode=="+resultcode );
            String reason=jsonObject.getString("reason");
            Log.e("json", "reason= "+reason );
            JSONArray result=jsonObject.getJSONArray("result");
            for(int i=0;i<result.length();i++){
                JSONObject o=result.getJSONObject(i);
                Log.e("json",o.toString());
            }
            String error_code=jsonObject.getString("erroe_code");
            Log.e("json","erroe_code="+error_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
