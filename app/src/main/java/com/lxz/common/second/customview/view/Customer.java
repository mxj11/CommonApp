package com.lxz.common.second.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.lxz.common.R;

import java.util.Random;

/**
 * Created by lxz on 2017/10/24 0024.
 */

public class Customer extends View{
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;
    /**
     * 绘制时控制文本的范围
     */
    private Rect mBound;
    private Paint mPaint;
    /**
     * 控件的宽
     */
    private int mWidth = 0;
    /**
     * 控件的高
     */
    private int mHeight = 0;
    public Customer(Context context) {
        this(context,null);
    }

    public Customer(Context context, AttributeSet attrs) {//布局文件默认走两个参数的方法
        this(context, attrs,0);
    }

    public Customer(Context context, AttributeSet attrs, int defStyleAttr) {//让所有的构造方法都走这个方法
        super(context, attrs, defStyleAttr);
//        setWillNotDraw(false);
        //获取自定义样式属性
        TypedArray array =  context.getTheme().obtainStyledAttributes(attrs, R.styleable.titleStytle,defStyleAttr,0);
        for(int i = 0;i<array.getIndexCount();i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.titleStytle_titleText:
                    mTitleText = array.getString(attr);
                    break;
                case R.styleable.titleStytle_titleTextColor://文字颜色默认是黑色
                    mTitleTextColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.titleStytle_titleTextSize://字体默认是16sp
                    mTitleTextSize = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }

        array.recycle();
        //设置绘制文本的大小和颜色
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
//        mPaint.setColor(mTitleTextColor);
        //获取绘制文本的宽高
        mBound = new Rect();
        if(mTitleText != null){
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = getRandomText();
                postInvalidate();
            }
        });
        System.out.println(getWidth()+"");
        System.out.println(getMeasuredHeight()+"");
    }

    public String getRandomText(){
        Random random = new Random();
        return random.nextInt(10)+"";
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取测量宽度
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY://明确指定的，即程序员自己设置的
                mWidth = getPaddingLeft()+getPaddingRight()+specSize;
                break;
            case MeasureSpec.AT_MOST://一般为wrap_content
                mWidth = getPaddingLeft()+getPaddingRight()+mBound.width();
                break;
        }

        //获取测量高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY://明确指定的，即程序员自己设置的
                mHeight = getPaddingTop()+getPaddingBottom()+specSize;
                break;
            case MeasureSpec.AT_MOST://一般为wrap_content
                mHeight = getPaddingTop()+getPaddingBottom()+mBound.height();
                break;
        }
        //设置测量宽高
        setMeasuredDimension(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(mTitleTextColor);
//        mPaint.setTextSize(mTitleTextSize);
        if(mTitleText != null){
            canvas.drawText(mTitleText, mWidth / 2 - mBound.width() / 2, mHeight / 2 + mBound.height() / 2, mPaint);
        }
    }
}
