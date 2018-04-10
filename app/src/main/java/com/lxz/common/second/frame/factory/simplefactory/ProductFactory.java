package com.lxz.common.second.frame.factory.simplefactory;

/**
 * Created by lxz on 2017/9/23 0023.
 * 简单工厂模式
 * 场景：创建对象
 * 简单工厂：提供创建对象的功能，不需要关心具体的实现
 * 好处：降低客户端与模块之间的耦合度（最少知识原则）
 */

public class ProductFactory {
    public static final int PRODUCTA = 0;
    public static final int PRODUCTB = 1;
    public static final int PRODUCTC = 2;
    public static ProductParent create(int type) {
        switch (type) {
            case PRODUCTA:
                return new ProductA();
            case PRODUCTB:
                return new ProductB();
            case PRODUCTC:
                return new ProductC();
            default:
                return new ProductA();
        }
    }
}
