package com.example.administrator.alltest.view_skipdots;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.alltest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 *
 * copy from "http://www.apkbus.com/forum.php?mod=viewthread&tid=270678&extra=page%3D2%26filter%3Dsortid%26orderby%3Ddateline%26sortid%3D12"
 */

public class DotsView extends View{

    private int mColor;
    private int mLayoutSize=100;
    private double time=0;
    private int flag=0;

    private Paint mPaint=new Paint();

    public DotsView(Context context){
        super(context);
    }
    public DotsView(Context context, AttributeSet attrs) {
        super(context,attrs);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.DotsView);
        mColor=array.getColor(R.styleable.DotsView_Circlecolor, Color.GRAY);
        array.recycle();
        init();
    }

    private void init() {
        mPaint.setAntiAlias(false);
        mPaint.setColor(mColor);
    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);

        mLayoutSize=Math.min(widthSpecSize,heightSpecSize);
        setMeasuredDimension(mLayoutSize,mLayoutSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆点的半径
        double radius=(mLayoutSize/6)*0.3;
        //圆点之间的间隙
        double gap=(mLayoutSize/6)*0.4;
        //计算6个圆点的圆心坐标
        List<Point> mCenterPoints=getCenterPoints(radius,gap);
        for(Point mCenterPoint : mCenterPoints){
            canvas.drawCircle(mCenterPoint.x , mCenterPoint.y , (float) radius , mPaint);
        }
        invalidate();
    }

    private List<Point> getCenterPoints(double r,double g){
        List<Point> mCenterPoints=new ArrayList<>();

        float mCenterPointGap=new Float(2*r+g);
        double a=(1-(1-time)*(1-time))*mCenterPointGap;

        if(time>1){
            time=0;
            //这里控制第一个圆点在上面还是下面走
            if(flag==0){
                flag=1;
            }else{
                flag=0;
            }
        }else{
            time=time+0.025;
        }

        //后5个点只是平移效果
        int y = mLayoutSize/2;
        Float x2 = new Float((r + g)/2 + mCenterPointGap * 1 - a);
        Float x3 = new Float((r + g)/2 + mCenterPointGap * 2 - a);
        Float x4 = new Float((r + g)/2 + mCenterPointGap * 3 - a);
        Float x5 = new Float((r + g)/2 + mCenterPointGap * 4 - a);
        Float x6 = new Float((r + g)/2 + mCenterPointGap * 5 - a);

        Double x1 = 0.5 * g + r +  (1-(1-time) * (1-time)) * mCenterPointGap * 5;

        //这里使用了x²+y²=r²
        Double y1 = Math.sqrt((mLayoutSize - g - 2 * r) * (mLayoutSize - g - 2 * r)/4 - (x1 - mLayoutSize * 0.5) * (x1 - mLayoutSize * 0.5)) + mLayoutSize * 0.5;
        if(flag == 0){
            y1 = mLayoutSize * 0.5 - Math.sqrt((mLayoutSize - g - 2 * r) * (mLayoutSize - g - 2 * r)/4 - (x1 - mLayoutSize * 0.5) * (x1 - mLayoutSize * 0.5));
        }

        mCenterPoints.add(new Point(x1.intValue(),y1.intValue()));
        mCenterPoints.add(new Point(x2.intValue(),y));
        mCenterPoints.add(new Point(x3.intValue(),y));
        mCenterPoints.add(new Point(x4.intValue(),y));
        mCenterPoints.add(new Point(x5.intValue(),y));
        mCenterPoints.add(new Point(x6.intValue(),y));
        return mCenterPoints;
    }
}
