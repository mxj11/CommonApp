package com.lxz.common.fourth.generic.classes;

/**
 * Created by lxz on 2017/10/30 0030.
 */

public class Father<T> {
    private T t;

    public Father() {
    }

    public Father(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
