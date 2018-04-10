package com.lxz.common.second.customview.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.lxz.common.third.commontools.L;

/**
 * Created by lxz on 2017/12/20 0020.
 */

public class MyButton extends Button{
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        L.e("dispatchTouchEvent");
         super.dispatchTouchEvent(event);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.e("onTouchEvent");
//        return super.onTouchEvent(event);
        return false;//响应父容器
    }
}
