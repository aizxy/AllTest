package com.example.administrator.alltest.turn_the_view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.alltest.R;

import java.util.ArrayList;
import java.util.List;

public class PageTurnActivity extends AppCompatActivity {

    private PageTurnView pageTurnView;
    private List<Bitmap> bitmaps;
    private int[] ints=new int[]{R.drawable.sea,R.drawable.meimei,R.drawable.meinv,R.drawable.small,R.drawable.taobao};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_turn);
        initBitmaps();
        pageTurnView= (PageTurnView) findViewById(R.id.page_turn_view);
        pageTurnView.setBitmaps(bitmaps);
    }

    private void initBitmaps(){
        bitmaps=new ArrayList<>();
        for(int i=0;i<ints.length;i++){
            int index=ints[i];
            Bitmap bitmap= BitmapFactory.decodeResource(this.getApplication().getResources(),index);
            bitmaps.add(bitmap);
        }
    }
}
