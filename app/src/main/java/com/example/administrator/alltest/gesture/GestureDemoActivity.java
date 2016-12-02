package com.example.administrator.alltest.gesture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.administrator.alltest.R;
import com.example.administrator.alltest.toast.ToastUtil;

public class GestureDemoActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    //定义手势检测实例
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_demo);

        //创建手势检测器
        detector=new GestureDetector(this,this);
    }

    //重写此方法，将触碰事件交给检测器处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将activity上的触碰事件交给GestureDetector处理
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //按下时触碰该方法
        ToastUtil.showToast(this,"按下了");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        //当用户在屏幕上按下，而且还未移动和松开时触发
        ToastUtil.showToast(this,"这是一个showPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        //在屏幕上的轻击事件将会触发此方法
        ToastUtil.showToast(this,"单击");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //当屏幕滚动时触发
        ToastUtil.showToast(this,"滚动");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        //当用户在屏幕上长按的时候触发
        ToastUtil.showToast(this,"还不放手吗？");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //当用户在屏幕上拖动时触发
        ToastUtil.showToast(this,"拖动");
        return false;
    }
}
