package com.lxz.common.second.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.base.BaseFragment;
import com.lxz.common.home.adapter.FrameListAdapter;
import com.lxz.common.second.datastructure.activity.DataStructureAndAlgorithmsActivity;
import com.lxz.common.second.frame.activity.FrameActivity;
import com.lxz.common.second.h5.activity.AndroidAndH5Activity;
import com.lxz.common.second.customview.activity.CustomViewActivity;
import com.lxz.common.second.multichannel.MultiChannelPackingActivity;
import com.lxz.common.second.ndk.activity.NDKActivity;
import com.lxz.common.second.performance.activity.PerformanceOptimizationActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/12 0012.
 */

public class SecondFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @Bind(R.id.lv_android_senior)
    ListView lvAndroidSenior;
    private View view;
    private String[] frameList = new String[] {"Android与H5互调","自定义view","NDK","移动架构","数据结构与算法","性能优化","多渠道打包"};
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        setAdapter();
        lvAndroidSenior.setOnItemClickListener(this);
    }

    private void setAdapter() {
        lvAndroidSenior.setAdapter(new FrameListAdapter(getActivity(),frameList));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class cls = null;
        if(frameList[position].equalsIgnoreCase("Android与H5互调")){
            cls = AndroidAndH5Activity.class;
        }else if(frameList[position].equalsIgnoreCase("自定义view")){
            cls = CustomViewActivity.class;
        }else if(frameList[position].equalsIgnoreCase("NDK")){
            cls = NDKActivity.class;
        }else if(frameList[position].equalsIgnoreCase("移动架构")){
            cls = FrameActivity.class;
        }else if(frameList[position].equalsIgnoreCase("数据结构与算法")){
            cls = DataStructureAndAlgorithmsActivity.class;
        }else if(frameList[position].equalsIgnoreCase("性能优化")){
            cls = PerformanceOptimizationActivity.class;
        }else if(frameList[position].equalsIgnoreCase("多渠道打包")){
            cls = MultiChannelPackingActivity.class;
        }
        startToActivity(cls);
    }
    private void startToActivity(Class cls) {
        if(cls != null){
            startActivity(new Intent(MyApplication.getMyApplicationInstance(),cls));
            cls = null;
        }
    }
}
