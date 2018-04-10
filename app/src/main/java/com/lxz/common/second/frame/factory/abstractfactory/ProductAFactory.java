package com.lxz.common.second.frame.factory.abstractfactory;

public class ProductAFactory implements IFactory{

	@Override
	public ProductParent creatProduceParent() {
		return new ProductA();
	}


}
