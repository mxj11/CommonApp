package com.lxz.common.third.universalfragment.model;

/**
 * Created by lxz on 2017/11/6 0006.
 */

public abstract class FunctionOnlyWithParam<Param> extends Function {
    public FunctionOnlyWithParam(String funName) {
        super(funName);
    }
    public abstract void function(Param param);
}
