package com.lxz.common.home.tablayoutandviewpager.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;
import com.lxz.common.home.tablayoutandviewpager.adapter.MyPagerAdapter;
import com.lxz.common.home.tablayoutandviewpager.fragment.MyFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TabLayoutMainActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<MyFragment> fragments;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_main);
        ButterKnife.bind(this);
        tvTitle.setText("TabLayoutAndViewPager");

        //初始化数据
        fragments = new ArrayList<>();
        for (int i=0;i<12;i++){
            fragments.add(new MyFragment("标题"+i,"内容"+i));
        }
        //设置ViewPager的适配器
        adapter = new MyPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        //关联ViewPager
        tabLayout.setupWithViewPager(viewPager);
        //设置固定的
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TabLayoutMainActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
