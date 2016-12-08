package com.example.administrator.alltest.loadingview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.alltest.R;

/**
 * Created by Administrator on 2016/12/8 0008.
 *
 * 来源网址：http://www.itlanbao.com/code/20151008/10000/100566.html
 */

public class PieProgress extends View{

    private final RectF mRect=new RectF();
    private final RectF mRectInner=new RectF();

    private final Paint mPaintForeground=new Paint();
    private final Paint mPaintBackground=new Paint();
    private final Paint mPaintErase=new Paint();

    private static final Xfermode PORTER_DUFF_CLEAR=new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    private int mColorForeground= Color.WHITE;
    private int mColorBackground=Color.BLACK;

    private int mProgress;

    private static final float PADDING=4;
    private float mPadding;

    private Bitmap mBitmap;

    //内外圆之间的量n
    private static final float INNER_RADIUS_RATIO=0.84f;

    public PieProgress(Context context){
        this(context,null);
    }
    public PieProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context.obtainStyledAttributes(attrs,R.styleable.PieProgress));
        Resources r=context.getResources();
        //获得屏幕密度
        float scale=r.getDisplayMetrics().density;
        mPadding=scale*PADDING+r.getDimension(R.dimen.actionbar_vertival_padding);
        //设置画笔
        mPaintForeground.setColor(mColorForeground);
        mPaintForeground.setAntiAlias(true);
        mPaintBackground.setColor(mColorBackground);
        mPaintBackground.setAntiAlias(true);
        mPaintErase.setXfermode(PORTER_DUFF_CLEAR);
        mPaintErase.setAntiAlias(true);
    }

    //解析自定义的view属性,从XML资源中
    private void parseAttributes(TypedArray array){
        mColorForeground=array.getColor(R.styleable.PieProgress_foregroundColor,mColorForeground);
        mColorBackground=array.getColor(R.styleable.PieProgress_backgroundColor,mColorBackground);
        //调用方法Recycle
        array.recycle();
    }

    //画图
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,getWidth()/2-mBitmap.getWidth()/2,getHeight()/2-mBitmap.getHeight()/2,null);
    }

    //改变view的大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float bitmapWidth=w-2*mPadding;
        float bitmapHeight=h-2*mPadding;
        float radius=Math.min(bitmapWidth/2,bitmapHeight/2);
        //设置形状
        mRect.set(0,0,bitmapWidth,bitmapHeight);
        radius *= INNER_RADIUS_RATIO;
        mRectInner.set(bitmapWidth/2f-radius,bitmapHeight/2f-radius,bitmapWidth/2f+radius,bitmapHeight/2f+radius);
        //改变view
        updateBitmap();
    }

    //view动态描绘的方法
    private void updateBitmap() {
        if (mRect == null || mRect.width() == 0) {
            return;
        }
        mBitmap = Bitmap.createBitmap((int) mRect.width(), (int) mRect.height(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawArc(mRect, -90, 360, true, mPaintBackground);
        if (mProgress < 3) {
            canvas.drawLine(mRect.width() / 2, mRect.height() / 2, mRect.width() / 2, 0,
                    mPaintForeground);
        }
        canvas.drawArc(mRect, -90, mProgress, true, mPaintForeground);
        //刷新视图
        postInvalidate();
    }

    //进度置零的方法
    public void reset(){
        mProgress=0;
        updateBitmap();
    }

    //设置前景色的方法
    public void setmPaintForeground(int color){
        this.mColorForeground=color;
        mPaintForeground.setColor(color);
        //刷新视图
        invalidate();
    }

    //设置背景色的方法
    public void setmColorBackground(int color){
        this.mColorBackground=color;
        mPaintBackground.setColor(color);
        //刷新视图
        invalidate();
    }

    //设置进度值，在0到360之间
    public synchronized void setmProgress(int progress){
        mProgress=progress;
        if(progress>360){
            mProgress=360;
        }
        //重新调用绘制更新方法
        updateBitmap();
    }
}
