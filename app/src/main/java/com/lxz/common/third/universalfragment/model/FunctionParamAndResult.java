package com.lxz.common.third.universalfragment.model;

/**
 * Created by lxz on 2017/11/6 0006.
 */

public abstract class FunctionParamAndResult<Result,Param> extends Function {
    public FunctionParamAndResult(String funName) {
        super(funName);
    }
    public abstract Result function(Param param);
}
