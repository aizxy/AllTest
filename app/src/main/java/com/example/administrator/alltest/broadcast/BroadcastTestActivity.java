package com.example.administrator.alltest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.alltest.R;

public class BroadcastTestActivity extends AppCompatActivity {

    private final String ACTION_NAME="发送广播";
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBoradcastReceiver();
        setContentView(R.layout.activity_broadcast_test);
        btn= (Button) findViewById(R.id.button_broadcast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ACTION_NAME);
                intent.putExtra("hehe","你好！");
                sendBroadcast(intent);
            }
        });
    }

    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(action.equals(ACTION_NAME)){
                String s=intent.getStringExtra("hehe");
                Toast.makeText(getApplication(),s,Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void registerBoradcastReceiver(){
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(ACTION_NAME);
        registerReceiver(broadcastReceiver,intentFilter);
    }
}
