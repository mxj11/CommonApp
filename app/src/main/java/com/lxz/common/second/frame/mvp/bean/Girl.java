package com.lxz.common.second.frame.mvp.bean;

public class Girl {
	private String decribe;
	private int pictures;
	
	public Girl(String decribe, int pictures) {
		super();
		this.decribe = decribe;
		this.pictures = pictures;
	}
	public String getDecribe() {
		return decribe;
	}
	public void setDecribe(String decribe) {
		this.decribe = decribe;
	}
	public int getPictures() {
		return pictures;
	}
	public void setPictures(int pictures) {
		this.pictures = pictures;
	}
	@Override
	public String toString() {
		return "Girl [decribe=" + decribe + ", pictures=" + pictures + "]";
	}
	
}
