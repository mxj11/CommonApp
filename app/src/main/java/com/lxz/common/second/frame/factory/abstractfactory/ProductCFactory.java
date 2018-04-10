package com.lxz.common.second.frame.factory.abstractfactory;

public class ProductCFactory implements IFactory {

	@Override
	public ProductParent creatProduceParent() {
		return new ProductC();
	}
}
