package com.lxz.common.fourth.generic.method;

/**
 * Created by lxz on 2017/10/30 0030.
 *   当函数中使用了一个不明确的数据类型，那么在函数上就可以进行泛型的定义。
 *
 *   public <泛型的声明> 返回值类型  函数名( 泛型 变量名  ){
 *
 *
 *   }
 *
 */

public class GenericMethod {
    //泛型方法的定义
    public <T> T getData(T t){
        return t;
    }
}
