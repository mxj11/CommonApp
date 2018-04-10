package com.lxz.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.lxz.common.base.BaseFragment;
import com.lxz.common.fourth.fragment.FourthFragment;
import com.lxz.common.home.fragment.HomeFragment;
import com.lxz.common.second.fragment.SecondFragment;
import com.lxz.common.third.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    private List<BaseFragment> fragments;
    /**
     * 选择哪个页面
     */
    private int position;

    private BaseFragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(this);
        rgMain.check(R.id.rb_home);
    }

    /**
     * 页面数据初始化
     */
    private void initFragments() {
        fragments = new ArrayList<BaseFragment>();
        fragments.add(new HomeFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new FourthFragment());
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checked) {
        switch (checked){
            case R.id.rb_home:
                position = 0;
                break;
            case R.id.rb_second:
                position = 1;
                break;
            case R.id.rb_third:
                position = 2;
                break;
            case R.id.rb_fourth:
                position = 3;
                break;
            default:
                position = 0;
                break;
        }

        BaseFragment to = getFragment(position);
        switchChangeFragment(lastFragment,to);
    }

    /**
     * 切换Fragment
     * @param from 即将要隐藏的Fragment
     * @param to  即将要显示的Fragment
     */
    private void switchChangeFragment(BaseFragment from,BaseFragment to) {
        if(from != to){
            lastFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(!to.isAdded()){
                //隐藏from
                if(from != null){
                    ft.hide(from);
                }
                //添加to到FrameLayout
                if(to != null){
                    ft.add(R.id.fl_main,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }
    }

    /**
     * 获取选择的Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        return fragments.get(position);
    }


}
