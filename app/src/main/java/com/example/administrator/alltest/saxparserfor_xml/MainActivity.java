package com.example.administrator.alltest.saxparserfor_xml;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.alltest.R;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private String[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datas=new String[]{
                "xml解析",
                "json解析",
                "html解析"
        };
        arrayAdapter=new ArrayAdapter<String>(getApplication(),android.R.layout.simple_list_item_2,datas);
        listView= (ListView) findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent =new Intent(getApplication(),XmlActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
