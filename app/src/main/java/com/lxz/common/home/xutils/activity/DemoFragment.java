package com.lxz.common.home.xutils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by lxz on 2017/8/15 0015.
 */
@ContentView(R.layout.fragment_demo)
public class DemoFragment extends Fragment{
    @ViewInject(value = R.id.btn_fragment)
    private Button btn;
    @ViewInject(value = R.id.tv_text)
    private TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }
    @Event(value = R.id.btn_fragment)
    private void getEvent(View view){
        Toast.makeText(getActivity(),"我是按钮被初始化了",Toast.LENGTH_SHORT).show();
        tv.setText("我文本也被初始化了");
    }
}
