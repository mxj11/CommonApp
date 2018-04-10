package com.lxz.common.home.tablayoutandviewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lxz.common.home.tablayoutandviewpager.fragment.MyFragment;

import java.util.ArrayList;

/**
 * Created by lxz on 2017/9/2 0002.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    ArrayList<MyFragment> fragments;
    public MyPagerAdapter(FragmentManager fm, ArrayList<MyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
