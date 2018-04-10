package com.lxz.common.second.frame.mvp.presenter;

import com.lxz.common.second.frame.mvp.bean.Girl;
import com.lxz.common.second.frame.mvp.model.GirlModelImpl;
import com.lxz.common.second.frame.mvp.model.IGirlModel;
import com.lxz.common.second.frame.mvp.view.IGirlView;

import java.util.List;


public class GirlPresenter {
	//View层的引用
	IGirlView mGirlView;
	//private WeakReference<T> mWeakView;//弱引用，GC回收
	//Model层的引用
	IGirlModel girlModel = new GirlModelImpl();

	public GirlPresenter(IGirlView view) {
		super();
//		this.mWeakView = new WeakReference<T>(view);
		this.mGirlView = view;
	}

	//逻辑处理
	public void fetch(){
		if(girlModel != null){
			if(mGirlView != null ){//必须判断非空
				mGirlView.showToast();
			}
			//加载数据
			girlModel.loadGirl(new IGirlModel.GirlOnLoadListener() {
				@Override
				public void onComplete(List<Girl> girls) {
					//返回数据，显示到view层
					if(mGirlView != null){//必须判断非空
						mGirlView.showGirl(girls);
					}
				}
			});
		}
	}
	

}
