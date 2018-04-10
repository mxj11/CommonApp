package com.lxz.common.second.frame.single;
/**
 * 单例模式之双重检验锁
 * 作用:
 * @author lxz
 * @Date 2017-9-25
 * @Time 下午5:07:02
 *
 */
public class DoubleCheckModel {
	private DoubleCheckModel(){}
	private volatile static DoubleCheckModel instance;
	public static DoubleCheckModel getInstance(){
		if(instance == null){
			synchronized (DoubleCheckModel.class) {
				if(instance == null){//2、如果没有加这个非空判断，线程B拿到锁后又去new一个新的对象，所以要加非空判断
					instance = new DoubleCheckModel();
				}
			}
			//1、线程A释放锁
		}
		return instance;
	}
}
