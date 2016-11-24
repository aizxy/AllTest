package com.example.administrator.alltest.camera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.administrator.alltest.R;

import java.util.ArrayList;
import java.util.List;

public class PictureActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private ListView listView;
    private ImageView picture;

    private PictureAdapter adapter;

    private List<Picture> list=new ArrayList<Picture>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picture);
        initPictures();
        layout= (RelativeLayout) findViewById(R.id.activity_picture);
        listView= (ListView) findViewById(R.id.pic_list_view);
        picture= (ImageView) findViewById(R.id.picture);
        adapter=new PictureAdapter(this,0,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                picture.setImageResource(list.get(i).getResource());
                float centerX=layout.getWidth()/2f;
                float centerY=layout.getHeight()/2f;
                final Rotate3dAnimation rotation=new Rotate3dAnimation(0,90,centerX,centerY,310.0f,true);
                rotation.setDuration(500);
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                rotation.setAnimationListener(new TurnToImageView());
                layout.startAnimation(rotation);
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float centerX = layout.getWidth() / 2f;
                float centerY = layout.getHeight() / 2f;
                final Rotate3dAnimation rotation = new Rotate3dAnimation(360, 270, centerX,
                        centerY, 310.0f, true);
                rotation.setDuration(500);
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                rotation.setAnimationListener(new TurnToListView());
                layout.startAnimation(rotation);
            }
        });
    }

    private void initPictures(){
        Picture small=new Picture("small",R.drawable.small);
        list.add(small);
        Picture sea=new Picture("sea",R.drawable.sea);
        list.add(sea);
    }


    class TurnToImageView implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ListView的动画完成后，还需要再启动ImageView的动画，让ImageView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = layout.getWidth() / 2f;
            float centerY = layout.getHeight() / 2f;
            // 将ListView隐藏
            listView.setVisibility(View.GONE);
            // 将ImageView显示
            picture.setVisibility(View.VISIBLE);
            picture.requestFocus();
            // 构建3D旋转动画对象，旋转角度为270到360度，这使得ImageView将会从不可见变为可见
            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            layout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }



    class TurnToListView implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        /**
         * 当ImageView的动画完成后，还需要再启动ListView的动画，让ListView从不可见变为可见
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            // 获取布局的中心点位置，作为旋转的中心点
            float centerX = layout.getWidth() / 2f;
            float centerY = layout.getHeight() / 2f;
            // 将ImageView隐藏
            picture.setVisibility(View.GONE);
            // 将ListView显示
            listView.setVisibility(View.VISIBLE);
            listView.requestFocus();
            // 构建3D旋转动画对象，旋转角度为90到0度，这使得ListView将会从不可见变为可见，从而回到原点
            final Rotate3dAnimation rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
                    310.0f, false);
            // 动画持续时间500毫秒
            rotation.setDuration(500);
            // 动画完成后保持完成的状态
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            layout.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }
}
