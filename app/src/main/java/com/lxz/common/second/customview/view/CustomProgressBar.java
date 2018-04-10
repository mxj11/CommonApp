package com.lxz.common.second.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lxz.common.R;
import com.lxz.common.second.customview.utils.DensityUtil;


/**
 * Created by lxz on 2017/8/11 0011.
 */

public class CustomProgressBar extends View{
    /**
     * 第一圈颜色
     */
    private int mFirstColor;
    /**
     * 第二圈颜色
     */
    private int mSecondColor;
    /**
     * 圆圈的宽度
     */
    private int mCircleWidth;
    /**
     * 速度
     */
    private int mSpeed;
    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 当前进度
     */
    private int mProgress;

    /**
     * 是否应该开始下一个
     */
    private boolean isNext = false;


    public CustomProgressBar(Context context) {
        this(context,null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取自定义属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar,defStyleAttr,0);
        for(int i = 0;i<array.getIndexCount();i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth = array.getDimensionPixelOffset(attr, DensityUtil.dip2px(context,20));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = array.getInt(attr,20);//默认20
                    break;
            }
        }

        array.recycle();

        //创建画笔
        mPaint = new Paint();
        // 绘图线程
        new Thread()
        {
            public void run()
            {
                while (true)
                {
                    mProgress++;
                    if (mProgress == 360)
                    {
                        mProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try
                    {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
        }.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth()/2;//圆心坐标x
        int radius = center - mCircleWidth/2;//圆半径
        //设置圆环宽度
        mPaint.setStrokeWidth(mCircleWidth);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //设置空心
        mPaint.setStyle(Paint.Style.STROKE);
        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        if (!isNext){
        // 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else{
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }


    }
}
