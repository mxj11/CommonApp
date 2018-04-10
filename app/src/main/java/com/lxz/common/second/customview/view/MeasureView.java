package com.lxz.common.second.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lxz on 2017/8/7 0007.
 */

public class MeasureView extends View {
    private Context mContext;
    /**
     * new 控件的时候走这个方法
     * @param context
     */
    public MeasureView(Context context) {
        this(context,null);
    }

    /**
     * 加载布局的时候调用这个方法
     * @param context
     * @param attrs
     */
    public MeasureView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 加载布局的时候调用这个方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;
        //获取测量宽度
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode){
            case MeasureSpec.EXACTLY://指定宽度(布局文件指定的宽度和match_parent)
//                Toast.makeText(mContext,"size:"+size,Toast.LENGTH_SHORT).show();
                break;
            case MeasureSpec.AT_MOST://布局指定宽度wrap_content
                Toast.makeText(mContext,"size:"+size,Toast.LENGTH_SHORT).show();
                break;
        }


        //获取测量高度
        mode = MeasureSpec.getMode(heightMeasureSpec);
        size = MeasureSpec.getSize(heightMeasureSpec);
        switch (mode){
            case MeasureSpec.EXACTLY:

                break;
            case MeasureSpec.AT_MOST:

                break;
        }

        //设置测量宽高
        setMeasuredDimension(200,200);
//        setMeasuredDimension(width,height);
    }
}
