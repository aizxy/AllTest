package com.example.administrator.alltest.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.alltest.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Button btn;
    private List<HeHe> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn= (Button) findViewById(R.id.finish);
        list=new ArrayList<>();
        for(int i=0;i<10;i++){
            String name="呵呵"+i;
            String age=i+"";
            HeHe he=new HeHe(name,age);
            list.add(he);
        }
        btn.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent=new Intent();
//            intent.putExtra("name","是谁");
//            setResult(0,intent);
//            finish();
            File path=getApplicationContext().getCacheDir();
            File file=new File(path.getAbsolutePath()+"/object.adt");
            try {
                file.createNewFile();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                HeHe[] obj=new HeHe[list.size()];
                list.toArray(obj);
                oos.writeObject(obj);
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent=new Intent();
            setResult(0,intent);
            finish();
        }
    };
}
