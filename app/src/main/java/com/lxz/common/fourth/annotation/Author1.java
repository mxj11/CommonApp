package com.lxz.common.fourth.annotation;

/**
 * Created by lxz on 2017/10/30 0030.
 */

public @interface Author1 {
    // 如果注解名称为value,使用时候可以省略名称，直接给值
    // (且注解只有一个属性时候才可以省略名称)
    String value();
}
