package com.lxz.common.second.frame.mvp.presenter;

import java.lang.ref.WeakReference;

public class BasePresenter<T> {
	private WeakReference<T> mWeakView;

	public void attach(T view){
		this.mWeakView = new WeakReference<T>(view);
	}
	
	public void detach(){if(mWeakView != null){
		mWeakView.clear();
	}

	}
	public T getView(){
		return mWeakView.get();
	}
}
