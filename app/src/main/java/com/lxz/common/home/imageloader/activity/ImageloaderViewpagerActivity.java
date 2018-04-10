package com.lxz.common.home.imageloader.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.imageloader.adapter.ImageloaderViewpagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageloaderViewpagerActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.vp_imageloader_viewpager)
    ViewPager vpImageloaderViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader_viewpager);
        ButterKnife.bind(this);
        // 标题
        tvTitle.setText("Imageloader应用在viewpager中");

        // 初始化viewpager
        ImageloaderViewpagerAdapter imageloaderViewpagerAdapter = new ImageloaderViewpagerAdapter(this);

        vpImageloaderViewpager.setAdapter(imageloaderViewpagerAdapter);

        // 显示第一个条目
        vpImageloaderViewpager.setCurrentItem(1);
    }
}
