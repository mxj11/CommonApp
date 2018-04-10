package com.lxz.common.third.universalfragment.model;

/**
 * Created by lxz on 2017/11/6 0006.
 */

public abstract class FunctionOnlyWithResult<Result> extends Function {
    public FunctionOnlyWithResult(String funName) {
        super(funName);
    }
    public abstract Result function();
}
