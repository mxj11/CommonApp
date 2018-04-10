package com.lxz.common.home.banner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.home.banner.GlideImageLoader;
import com.lxz.common.home.banner.adapter.SampleAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BannerActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener{
    static final int REFRESH_COMPLETE = 0X1112;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.list)
    ListView listview;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;
    private Banner banner;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    String[] urls = getResources().getStringArray(R.array.url);
                    List list = Arrays.asList(urls);
                    List arrayList = new ArrayList(list);
                    //把新的图片地址加载到Banner
                    banner.update(arrayList);
                    //下拉刷新控件隐藏
                    swipe.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
        tvTitle.setText("Banner横幅");
        //加载Banner
        View header= LayoutInflater.from(this).inflate(R.layout.header,null);
        banner = (Banner) header.findViewById(R.id.banner);
        //设置Banner的高和宽
        banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyApplication.H/4));

        //Banner以头的方式添加到ListView中
        listview.addHeaderView(banner);

        //集合数据
        String[] data=getResources().getStringArray(R.array.demo_list);
        //设置ListView的适配器
        listview.setAdapter(new SampleAdapter(this,data));
        //设置ListView的item的点击事件
        listview.setOnItemClickListener(this);
        //设置下拉刷新监听
        swipe.setOnRefreshListener(this);

        //简单使用--Banner加载图片地址
        banner.setImages(MyApplication.images)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position){
            case 1:
                startActivity(new Intent(this, BannerAnimationActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, BannerStyleActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, IndicatorPositionActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, CustomBannerActivity.class));
                break;
        }
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }
}
