package com.lxz.common.second.frame.single;
/**
 * 单例模式之懒汉式
 * 作用:懒汉式会造成线程不安全
 * @author lxz
 * @Date 2017-9-25
 * @Time 下午3:11:48
 *
 */
public class LazyModel {
	private LazyModel(){}
	private static LazyModel instance;
	public static LazyModel getInstance(){
		if(instance == null){
			instance = new LazyModel();
		}
		return instance;
	}
}
