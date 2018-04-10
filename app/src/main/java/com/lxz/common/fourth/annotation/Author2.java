package com.lxz.common.fourth.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by lxz on 2017/10/30 0030.
 * 注解：作者，时间
 */
// 元注解 - 1. 定义注解的可用范围
@Target({TYPE,FIELD , METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//@Target({METHOD,FIELD,TYPE})   指定只能在方法、字段、类上用；

// 元注解 - 2. 指定注解的声明周期
@Retention(RetentionPolicy.RUNTIME)   // 字节码级别有效
public @interface Author2 {
    String name() default "lxz";
    String time();

    /**
     * 注解数组
     * @return
     */
   // String[] value() default {"haha","xixi"};
    String[] value();
}
