package com.lxz.common.second.frame.single;
/**
 * 单例模式之静态内部类
 * 作用:不会随着StaticInnerModel类的加载而instance被实例化，随用随加载，
 * @author lxz
 * @Date 2017-9-25
 * @Time 下午3:51:31
 *
 */
public class StaticInnerModel {
	private StaticInnerModel(){}
	private static class StaticInnerHolder{
		private static final StaticInnerModel instance = new StaticInnerModel();
	}
	public static StaticInnerModel getInstance(){
		return StaticInnerHolder.instance;
	}
}
