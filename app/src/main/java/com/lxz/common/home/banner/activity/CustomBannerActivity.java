package com.lxz.common.home.banner.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.home.banner.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomBannerActivity extends AppCompatActivity {

    @Bind(R.id.banner1)
    Banner banner1;
    @Bind(R.id.banner2)
    Banner banner2;
    @Bind(R.id.banner3)
    Banner banner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        ButterKnife.bind(this);
        banner1.setImages(MyApplication.images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner2.setImages(MyApplication.images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner3.setImages(MyApplication.images)
                .setBannerTitles(MyApplication.titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
}
