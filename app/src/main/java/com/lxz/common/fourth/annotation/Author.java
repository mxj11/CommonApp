package com.lxz.common.fourth.annotation;

/**
 * Created by lxz on 2017/10/30 0030.
 * 注解：作者，时间
 */

public @interface Author {
    /**
     * 注解属性
     * 	  1. 修饰为默认或public
     *    2. 不能有主体
     */
    String name();
    String time();
}
