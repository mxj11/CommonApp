package com.lxz.common.home.xutils.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.lxz.common.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by lxz on 2017/8/15 0015.
 */
@ContentView(R.layout.activity_xutils3_fragment)
public class MyFragmentActivity extends FragmentActivity{
    @ViewInject(R.id.tv_title)
    private TextView tvTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tvTitle.setText("在Fragment中使用Xutils3注解");
        //1、得到FragmentManger
        FragmentManager manager = getSupportFragmentManager();
        //2、开启事务
        FragmentTransaction ft = manager.beginTransaction();
        //3、代替Fragment
        ft.replace(R.id.fl_content,new DemoFragment());
        //4、提交事务
        ft.commit();
    }
}
