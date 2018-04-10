package com.lxz.common.home.picasso;

import android.content.Context;

/**
 * dip转为px工具类
 */
public class Utils {
    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
