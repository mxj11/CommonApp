package com.lxz.common.second.frame.single;
/**
 * 单例模式之懒汉式
 * 作用:线程安全，但是效率低下
 * @author lxz
 * @Date 2017-9-25
 * @Time 下午3:39:55
 *
 */
public class LazyModel1 {
	private LazyModel1(){}
	private static LazyModel1 instance;
	public static synchronized LazyModel1 getInstance(){
		if(instance == null){
			instance = new LazyModel1();
		}
		return instance;
	}
}
