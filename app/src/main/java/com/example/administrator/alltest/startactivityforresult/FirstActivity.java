package com.example.administrator.alltest.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.alltest.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn= (Button) findViewById(R.id.skip);
        tv= (TextView) findViewById(R.id.texth);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,SecondActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0){
//            String s=data.getStringExtra("name");
//            tv.setText(s);

            File file=new File(getApplicationContext().getCacheDir().getAbsolutePath()+"/object.adt");
            try {
//                FileInputStream in=getApplicationContext().openFileInput(getApplicationContext().getCacheDir().getAbsolutePath()+"/object.adt");
                ObjectInputStream iis = new ObjectInputStream(new FileInputStream(file));
                HeHe[] obj= (HeHe[]) iis.readObject();
                List<HeHe> listo= Arrays.asList(obj);
                String s=listo.toString();
                tv.setText(s);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
