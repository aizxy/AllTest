package com.example.administrator.alltest.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.alltest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentActivity extends AppCompatActivity{

    private QQFragment qqFragment;
    private WeiboFragment weiboFragment;
    private WeChatFragment weChatFragment;
    private TaobaoFragment taobaoFragment;

//    @BindView(R.id.qq_layout)
//    View qqLayout;
//    @BindView(R.id.weibo_layout)
//    View weiboLayout;
//    @BindView(R.id.wechat_layout)
//    View wechatLayout;
//    @BindView(R.id.taobo_layout)
//    View taobaoLayout;

    @BindView(R.id.taobo_image)
    ImageView taobo_image;
    @BindView(R.id.wechat_image)
    ImageView wechat_imageage;
    @BindView(R.id.weibo_image)
    ImageView weibo_image;
    @BindView(R.id.qq_image)
    ImageView qq_image;

    @BindView(R.id.qq_text)
    TextView qq_text;
    @BindView(R.id.wechat_text)
    TextView wechat_text;
    @BindView(R.id.weibo_text)
    TextView weibo_text;
    @BindView(R.id.taobo_text)
    TextView taobo_text;

    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        manager=getFragmentManager();
        setTabSelection(0);
    }

    @OnClick({R.id.qq_layout,R.id.weibo_layout,R.id.wechat_layout,R.id.taobo_layout})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qq_layout:
                setTabSelection(0);
                break;
            case R.id.weibo_layout:
                setTabSelection(1);
                break;
            case R.id.wechat_layout:
                setTabSelection(2);
                break;
            case R.id.taobo_layout:
                setTabSelection(3);
                break;
        }
    }

    //FragmentTransaction是android.app包中的，所以传入的fragment也要是Android.app包中的。
    private void setTabSelection(int index){
        clearSelection();
        FragmentTransaction transaction=manager.beginTransaction();
        hideFragments(transaction);
        switch (index){
            case 0:
                qq_image.setImageResource(R.drawable.addimport_qq);
                qq_text.setTextColor(Color.WHITE);
                if(qqFragment==null){
                    qqFragment=new QQFragment();
                    transaction.add(R.id.content,qqFragment);
                }else{
                    transaction.show(qqFragment);
                }
                break;
            case 1:
                weibo_image.setImageResource(R.drawable.fun_share_weibo);
                weibo_text.setTextColor(Color.WHITE);
                if(weiboFragment==null){
                    weiboFragment=new WeiboFragment();
                    transaction.add(R.id.content,weiboFragment);
                }else{
                    transaction.show(weiboFragment);
                }
                break;
            case 2:
                wechat_imageage.setImageResource(R.drawable.fun_share_weixin);
                wechat_text.setTextColor(Color.WHITE);
                if(weChatFragment==null){
                    weChatFragment=new WeChatFragment();
                    transaction.add(R.id.content,weChatFragment);
                }else{
                    transaction.show(weChatFragment);
                }
                break;
            case 3:
                taobo_image.setImageResource(R.drawable.taobao);
                taobo_text.setTextColor(Color.WHITE);
                if(taobaoFragment==null){
                    taobaoFragment=new TaobaoFragment();
                    transaction.add(R.id.content,taobaoFragment);
                }else{
                    transaction.show(taobaoFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection(){
//        qq_image.setBackgroundColor(Color.BLUE);
        qq_text.setTextColor(Color.parseColor("#82858b"));
//        weibo_image.setBackgroundColor(Color.BLUE);
        weibo_text.setTextColor(Color.parseColor("#82858b"));
//        wechat_imageage.setBackgroundColor(Color.BLUE);
        wechat_text.setTextColor(Color.parseColor("#82858b"));
//        taobo_image.setBackgroundColor(Color.BLUE);
        taobo_text.setTextColor(Color.parseColor("#82858b"));
    }

    private void hideFragments(FragmentTransaction transaction){
        if(qqFragment!=null){
            transaction.hide(qqFragment);
        }
        if(weiboFragment!=null){
            transaction.hide(weiboFragment);
        }
        if(weChatFragment!=null){
            transaction.hide(weChatFragment);
        }
        if(taobaoFragment!=null){
            transaction.hide(taobaoFragment);
        }
    }
}
