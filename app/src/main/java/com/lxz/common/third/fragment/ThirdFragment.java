package com.lxz.common.third.fragment;

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
import com.lxz.common.second.h5.activity.AndroidAndH5Activity;
import com.lxz.common.third.commontools.activity.ConmonToolsActivity;
import com.lxz.common.third.universalfragment.activity.UniversalActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/12 0012.
 */

public class ThirdFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @Bind(R.id.lv_learn)
    ListView lvLearn;
    private View view;
    private String[] frameList = new String[] {"常用工具类","万能Fragment"};

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        lvLearn.setOnItemClickListener(this);
        setAdapter();
    }
    private void setAdapter() {
        lvLearn.setAdapter(new FrameListAdapter(getActivity(),frameList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class cls = null;
        if(frameList[position].equalsIgnoreCase("常用工具类")){
            cls = ConmonToolsActivity.class;
        }else{
            if(frameList[position].equalsIgnoreCase("万能Fragment")){
                cls = UniversalActivity.class;
            }
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
