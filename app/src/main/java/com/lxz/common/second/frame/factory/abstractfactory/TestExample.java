package com.lxz.common.second.frame.factory.abstractfactory;

//import org.junit.Test;

/**
 * Created by lxz on 2017/9/23 0023.
 */

public class TestExample {
	//@Test
    public void produce(){
        IFactory factory = new ProductAFactory();
        ProductParent productA = factory.creatProduceParent();
    }
}
