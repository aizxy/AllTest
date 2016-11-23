package com.example.administrator.alltest.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.alltest.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteActivity extends AppCompatActivity {

    private DairyDao dairyDao;

    //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
    //所以要确保context已初始化,我们可以把实例化Dao的步骤放在Activity的onCreate里
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        //dairyDao实例化
        dairyDao=new DairyDao(this);
        initUI();
        initDatabase();
    }
    //dairyDao的初始化
    private void initDatabase(){
        SQliteDBHelper sQliteDBHelper=new SQliteDBHelper(this);
        //数据库创建
        sQliteDBHelper.getWritableDatabase();
    }

    //view的初始化
    private EditText txtTitle,txtWeather,txtContext;
    private void initUI(){
        txtTitle= (EditText) findViewById(R.id.txtTitle);
        txtWeather= (EditText) findViewById(R.id.txtWeather);
        txtContext= (EditText) findViewById(R.id.txtContext);
    }

    //数据库的使用
    class ViewOcl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String strSQL;
            boolean flag;
            String message;
            switch (v.getId()) {
                case R.id.btnAdd:
                    String title = txtTitle.getText().toString().trim();
                    String weather = txtWeather.getText().toString().trim();;
                    String context = txtContext.getText().toString().trim();;
                    String publish = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date());
                    // 动态组件SQL语句
                    strSQL = "insert into diary values(null,'" + title + "','"
                            + weather + "','" + context + "','" + publish + "')";
                    flag = dairyDao.execWrite(strSQL);
                    //返回信息
                    message = flag?"添加成功":"添加失败";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    break;
                case R.id.btnDelete:
                    strSQL = "delete from diary where tid = 1";
                    flag = dairyDao.execWrite(strSQL);
                    //返回信息
                    message = flag?"删除成功":"删除失败";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    break;
                case R.id.btnQuery:
                    strSQL = "select * from diary order by publish desc";
                    String data = dairyDao.execQuery(strSQL);
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                    break;
                case R.id.btnUpdate:
                    strSQL = "update diary set title = '测试标题1-1' where tid = 1";
                    flag = dairyDao.execWrite(strSQL);
                    //返回信息
                    message = flag?"更新成功":"更新失败";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
