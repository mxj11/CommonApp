package com.lxz.common.fourth.fragment;

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
import com.lxz.common.fourth.activity.AnnotationActivity;
import com.lxz.common.fourth.activity.ReflectActivity;
import com.lxz.common.fourth.activity.XMLActivity;
import com.lxz.common.fourth.http.HTTPActivity;
import com.lxz.common.fourth.netmodel.NetModelActivity;
import com.lxz.common.fourth.tcp_ip.TCPAndIPActivity;
import com.lxz.common.fourth.udp.UDPActivity;
import com.lxz.common.home.adapter.FrameListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/12 0012.
 */

public class FourthFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @Bind(R.id.lv_frame_list)
    ListView lvFrameList;
    private View view;
    private String[] frameList = new String[] {
            "泛型","反射","注解","xml解析","http协议","网络模型","TCP/IP协议","UDP协议"
    };
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fourth, null);
        ButterKnife.bind(this, view);
        initListener();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        setAdapter();
    }
    private void initListener() {
        lvFrameList.setOnItemClickListener(this);
    }
    private void setAdapter() {
        lvFrameList.setAdapter(new FrameListAdapter(MyApplication.getMyApplicationInstance(),frameList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private Class cls;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(frameList[position].equalsIgnoreCase("泛型")){
            cls = null;
        }else if(frameList[position].equalsIgnoreCase("xml解析")){
            cls = XMLActivity.class;
        }else if(frameList[position].equalsIgnoreCase("反射")){
            cls = ReflectActivity.class;
        }else if(frameList[position].equalsIgnoreCase("注解")){
            cls = AnnotationActivity.class;
        }else if(frameList[position].equalsIgnoreCase("http协议")){
            cls = HTTPActivity.class;
        }else if(frameList[position].equalsIgnoreCase("TCP/IP协议")){
            cls = TCPAndIPActivity.class;
        }else if(frameList[position].equalsIgnoreCase("UDP协议")){
            cls = UDPActivity.class;
        }else if(frameList[position].equalsIgnoreCase("网络模型")){
            cls = NetModelActivity.class;
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
