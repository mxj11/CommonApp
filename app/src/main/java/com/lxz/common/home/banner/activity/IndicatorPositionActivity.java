package com.lxz.common.home.banner.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.home.banner.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IndicatorPositionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.spinnerPosition)
    Spinner spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_position);
        ButterKnife.bind(this);
        spinnerPosition.setOnItemSelectedListener(this);

        banner.setImages(MyApplication.images)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                //设置指示器在不同的位置
                banner.setIndicatorGravity(BannerConfig.LEFT);
                break;
            case 1:
                banner.setIndicatorGravity(BannerConfig.CENTER);
                break;
            case 2:
                banner.setIndicatorGravity(BannerConfig.RIGHT);
                break;
        }
        banner.start();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
