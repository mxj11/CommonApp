package com.lxz.common.fourth.generic.interfaces;

/**
 * Created by lxz on 2017/10/30 0030.
 * 使用接口时明确具体类型。
 */

public class MyLog1 implements GenericInterface<String>{
    @Override
    public void print(String s) {
        System.out.println(s+"是String类型");
    }
}
