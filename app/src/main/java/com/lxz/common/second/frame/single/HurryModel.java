package com.lxz.common.second.frame.single;
/**
 * 单例模式之饿汉式
 * 作用:避免多线程同步问题，但是随着类加载而被实例化，没有懒汉式随用随加载
 * @author lxz
 * @Date 2017-9-25
 * @Time 下午3:42:12
 *
 */
public class HurryModel {
	private HurryModel(){}
	private static HurryModel instance = new HurryModel();
	public HurryModel getInstance(){
		return instance;
	}
}
