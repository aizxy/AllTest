package com.example.administrator.alltest;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(intent,1);
        try {
            java.lang.Process process=Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(null!=data){
            Uri uri=data.getData();
            if(uri==null){
                return;
            }else{
                Cursor c=getContentResolver().query(uri,new String[]{MediaStore.MediaColumns.DATA},null,null,null);
                if(c!=null&&c.moveToFirst()){
                    String filePath=c.getString(0);
                }
            }

        }
    }
}
