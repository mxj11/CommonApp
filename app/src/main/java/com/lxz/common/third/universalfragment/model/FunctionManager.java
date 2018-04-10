package com.lxz.common.third.universalfragment.model;

import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by lxz on 2017/11/6 0006.
 */

public class FunctionManager {
    private FunctionNoParamNoResult functionNoParamNoResult;

    //单例模式
    private FunctionManager(){
        mFunctionNoParamNoResult = new HashMap<>();
        mFunctionOnlyWithParam = new HashMap<>();
        mFunctionOnlyWithResult = new HashMap<>();
        mFunctionParamAndResult = new HashMap<>();
    }
    private static FunctionManager manager;
    public static FunctionManager getFrunctionManager(){
        if(manager == null){
            manager = new FunctionManager();
        }
        return manager;
    }
    private HashMap<String,FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String,FunctionOnlyWithParam> mFunctionOnlyWithParam;
    private HashMap<String,FunctionOnlyWithResult> mFunctionOnlyWithResult;
    private HashMap<String,FunctionParamAndResult> mFunctionParamAndResult;

    public FunctionManager addFunction(FunctionNoParamNoResult function){
        mFunctionNoParamNoResult.put(function.funName,function);
        return this;
    }

    public void invokeFunction(String funName){
        if(TextUtils.isEmpty(funName))return;
        if(mFunctionNoParamNoResult != null){
            functionNoParamNoResult = mFunctionNoParamNoResult.get(funName);
            if(functionNoParamNoResult != null){
                functionNoParamNoResult.function();
            }else{
                try {
                    throw new FuctionExcection("has no this function:"+funName);
                } catch (FuctionExcection fuctionExcection) {
                    fuctionExcection.printStackTrace();
                }
            }
        }
    }

}
