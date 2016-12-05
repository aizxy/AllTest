package com.example.administrator.alltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.alltest.bluetooth.BluetoothActivity;
import com.example.administrator.alltest.broadcast.BroadcastTestActivity;
import com.example.administrator.alltest.camera.PictureActivity;
import com.example.administrator.alltest.fragment.FragmentActivity;
import com.example.administrator.alltest.fullscreen_set.FullScreenActivity;
import com.example.administrator.alltest.js_java.JsActivity;
import com.example.administrator.alltest.okhttp_mvp_eventbus.BookActivity;
import com.example.administrator.alltest.pullrefresh.RefreshActivity;
import com.example.administrator.alltest.saxparserfor_xml.XmlActivity;
import com.example.administrator.alltest.snackbar.SnackbarActivity;
import com.example.administrator.alltest.sqlite.SQLiteActivity;
import com.example.administrator.alltest.startactivityforresult.FirstActivity;
import com.example.administrator.alltest.turn_the_view.PageTurnActivity;
import com.example.administrator.alltest.view_skipdots.DotsViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.listview)
    ListView listView;
    private Unbinder unbinder;
    private Demo[] demos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        unbinder=ButterKnife.bind(this);
        demos=createDemos();
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,demos));
        listView.setOnItemClickListener(this);
    }

    private Demo[] createDemos(){
        return new Demo[]{
                new Demo("dots下载动画",new Intent(this, DotsViewActivity.class)),
                new Demo("StartActivityForResult的使用",new Intent(this, FirstActivity.class)),
                new Demo("SQLite简单使用",new Intent(this, SQLiteActivity.class)),
                new Demo("SnackBar",new Intent(this, SnackbarActivity.class)),
                new Demo("XML解析",new Intent(this, XmlActivity.class)),
                new Demo("MVP",new Intent(this, BookActivity.class)),
                new Demo("js&java",new Intent(this, JsActivity.class)),
                new Demo("全屏设置",new Intent(this, FullScreenActivity.class)),
                new Demo("Fragment的简单使用",new Intent(this, FragmentActivity.class)),
                new Demo("一种3d效果",new Intent(this, PictureActivity.class)),
                new Demo("广播的使用",new Intent(this, BroadcastTestActivity.class)),
                new Demo("BlueTooth",new Intent(this, BluetoothActivity.class)),
                new Demo("listview的下拉刷新",new Intent(this, RefreshActivity.class)),
                new Demo("翻页",new Intent(this, PageTurnActivity.class))
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(demos[i].intent);
    }

    static class Demo{
        String name;
        Intent intent;
        private Demo(String name,Intent intent){
            this.intent=intent;
            this.name=name;
        }
        public String toString(){
            return name;
        }
    }
}
