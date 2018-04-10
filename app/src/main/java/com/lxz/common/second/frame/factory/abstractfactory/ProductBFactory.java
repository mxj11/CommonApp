package com.lxz.common.second.frame.factory.abstractfactory;

public class ProductBFactory implements IFactory {

	@Override
	public ProductParent creatProduceParent() {
		return new ProductB();
	}
}
