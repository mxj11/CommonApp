package com.lxz.common.second.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.lxz.common.R;


/**
 * Created by lxz on 2017/8/5 0005.
 */

public class CustomImageView extends View{
    /**
     * 图片标题
     */
    private String mTitleText;
    /**
     * 标题文字颜色
     */
    private int mTitleColor;
    /**
     * 标题文字大小
     */
    private int mTitleSize;
    /**
     * 图片缩放模式
     */
    private int mImageScale;
    private static final int IMAGE_SCALE_FITXY = 0;
    private static final int IMAGE_SCALE_CENTER = 1;
    /**
     * 标题图片
     */
    private Bitmap mImage;

    /**
     * 对文本的约束
     */
    private Rect mTextBound;
    /**
     * 控制整体布局
     */
    private Rect mRect;

    private Paint mPaint;

    /**
     * 控件的宽
     */
    private int mWidth = 0;
    /**
     * 控件的高
     */
    private int mHeight = 0;
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.customImageView,defStyleAttr,0);
        for(int i = 0;i<array.getIndexCount();i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.customImageView_titleText:
                    mTitleText = array.getString(attr);
                    break;
                case R.styleable.customImageView_titleTextColor:
                    mTitleColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.customImageView_titleTextSize:
                    mTitleSize = array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.customImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(),array.getResourceId(attr,0));
                    break;
                case R.styleable.customImageView_imageScaleType:
                    mImageScale = array.getInt(attr,0);
                    break;
            }
        }

        array.recycle();

        mRect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setColor(mTitleColor);
        //计算文本需要的范围
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mTextBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取测量宽度
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY:
                mWidth = specSize;
                break;
            case MeasureSpec.AT_MOST:
                // 由图片决定的宽
                int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
                // 由字体决定的宽
                int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();
                int desire = Math.max(desireByImg, desireByTitle);//如果图片的宽大，由图片的宽度决定，反之，由字体的宽度决定
                mWidth = Math.min(desire, specSize);
                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }

        //获取测量高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode){
            case MeasureSpec.EXACTLY:
                mHeight = specSize;
                break;
            case MeasureSpec.AT_MOST:
                // 由图片决定的高
                int desireByImg = getPaddingBottom() + getPaddingTop() + mImage.getHeight();
                // 由字体决定的高
                int desireByTitle = getPaddingBottom() + getPaddingTop() + mTextBound.height();
                int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mTextBound.height();//图片和字体的高
                mHeight = Math.min(desire, specSize);
                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }


        //设置测量宽高
        setMeasuredDimension(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //边框
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        //画底板
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTitleColor);
        mPaint.setStyle(Paint.Style.FILL);

        //当前设置的宽度小雨字体需要的宽度，将字体改为xxx
        if(mTextBound.width()>mWidth){
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText,paint,(float) mWidth - getPaddingLeft() - getPaddingRight(),TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        }else{
            //正常情况，将字体居中
            canvas.drawText(mTitleText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }

        //取消使用掉的块
        mRect.bottom -= mTextBound.height();

        if (mImageScale == IMAGE_SCALE_FITXY) {
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        } else {
            //计算居中的矩形范围
            mRect.left = mWidth / 2 - mImage.getWidth() / 2;
            mRect.right = mWidth / 2 + mImage.getWidth() / 2;
            mRect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            mRect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }
    }
}
