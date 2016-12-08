package com.example.administrator.alltest.loadingview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.alltest.R;

public class ProgressActivity extends AppCompatActivity {

    private PieProgress mPieProgress1,mPieProgress2;
    boolean pieRunning;
    int pieProgress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        mPieProgress1= (PieProgress) findViewById(R.id.mProgress1);
        mPieProgress2= (PieProgress) findViewById(R.id.mProgress2);
        Button btn1= (Button) findViewById(R.id.btn1);
        new Thread(indicatorRunnable).start();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pieRunning){
                    pieProgress=0;
                    new Thread(indicatorRunnable).start();
                }
            }
        });
    }

    //绘制的线程
    final Runnable indicatorRunnable=new Runnable() {
        @Override
        public void run() {
            pieRunning=true;
            while(pieProgress<361){
                mPieProgress1.setmProgress(pieProgress);
                mPieProgress2.setmProgress(pieProgress);
                pieProgress+=2;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pieRunning=false;
            }
        }
    };
}
