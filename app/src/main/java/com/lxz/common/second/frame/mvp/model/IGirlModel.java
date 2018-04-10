package com.lxz.common.second.frame.mvp.model;

import com.lxz.common.second.frame.mvp.bean.Girl;

import java.util.List;


public interface IGirlModel {
	void loadGirl(GirlOnLoadListener listener);
	interface GirlOnLoadListener{
		void onComplete(List<Girl> girls);
	}
}
