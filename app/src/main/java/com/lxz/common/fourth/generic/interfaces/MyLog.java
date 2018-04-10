package com.lxz.common.fourth.generic.interfaces;

import com.lxz.common.third.commontools.T;

/**
 * Created by lxz on 2017/10/30 0030.
 * 实现不知为何类型时可以这样定义
 */

public class MyLog<T> implements GenericInterface<T>{
    @Override
    public void print(T t) {

    }
}
