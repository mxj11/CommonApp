package com.lxz.common.home.fragment;

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
import com.lxz.common.home.adapter.FrameListAdapter;
import com.lxz.common.home.afinal.activity.AfinalActivity;
import com.lxz.common.home.banner.activity.BannerActivity;
import com.lxz.common.home.butterknife.activity.ButterKnifeActivity;
import com.lxz.common.home.coordinatorLayout.CoordinatorLayoutActivity;
import com.lxz.common.home.countdownview.activity.CountDownViewActivity;
import com.lxz.common.home.eventbus.activity.EventBusActivity;
import com.lxz.common.home.fresco.activity.FrescoActivity;
import com.lxz.common.home.glide.activity.GlideActivity;
import com.lxz.common.home.imageloader.activity.ImageLoaderActivity;
import com.lxz.common.home.jiecaovideoplayer.activity.JieCaoVideoPlayerActivity;
import com.lxz.common.home.json.activity.JsonActivity;
import com.lxz.common.home.okhttp.activity.OkhttpActivity;
import com.lxz.common.home.opendanmaku.activity.OpenDanmakuActivity;
import com.lxz.common.home.picasso.activity.PicassoActivity;
import com.lxz.common.home.pulltorefresh.activity.PullToRefreshActivity;
import com.lxz.common.home.recycleview.activity.RecycleViewActivity;
import com.lxz.common.home.snackbar.SnackbarActivity;
import com.lxz.common.home.tablayoutandviewpager.Activity.TabLayoutMainActivity;
import com.lxz.common.home.universalvideoview.activity.UniversalVideoViewActivity;
import com.lxz.common.home.volley.activity.VolleyActivity;
import com.lxz.common.home.xutils.activity.Xutils3Activity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxz on 2017/8/12 0012.
 */

public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    View view;
    @Bind(R.id.lv_frame_list)
    ListView lvFrameList;
    private String[] frameList = new String[] {"okhttp","json解析","Xutils","Afinal","Volley","ButterKnife",
            "EventBus","ImageLoader","Picasso","RecyclerView","Glide","Fresco","PulltoRefresh","UniversalVideoView",
            "JieCaoVideoPlayer","Banner","CountdownView秒杀倒计时","OpenDanmaku弹幕","TabLayout&ViewPager",
            "CoordinatorLayout&FloatingActionButton","Snackbar"
    };
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initListener();
        return view;
    }

    private void initListener() {
        lvFrameList.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setAdapter();
    }

    private void setAdapter() {
        lvFrameList.setAdapter(new FrameListAdapter(MyApplication.getMyApplicationInstance(),frameList));
    }

    private Class cls;
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(frameList[position].equalsIgnoreCase("okhttp")){
            cls = OkhttpActivity.class;
        }else if(frameList[position].equalsIgnoreCase("json解析")){
            cls = JsonActivity.class;
        }else if(frameList[position].equalsIgnoreCase("Xutils")){
            cls = Xutils3Activity.class;
        }else if(frameList[position].equalsIgnoreCase("Afinal")){
            cls = AfinalActivity.class;
        }else if(frameList[position].equalsIgnoreCase("Volley")){
            cls = VolleyActivity.class;
        }else if(frameList[position].equalsIgnoreCase("ButterKnife")){
            cls = ButterKnifeActivity.class;
        }else if(frameList[position].equalsIgnoreCase("ImageLoader")){
            cls = ImageLoaderActivity.class;
        }else if(frameList[position].equalsIgnoreCase("EventBus")){
            cls = EventBusActivity.class;
        }else if(frameList[position].equalsIgnoreCase("Picasso")){
            cls = PicassoActivity.class;
        }else if(frameList[position].equalsIgnoreCase("RecyclerView")){
            cls = RecycleViewActivity.class;
        }else if(frameList[position].equalsIgnoreCase("Glide")){
            cls = GlideActivity.class;
        }else if(frameList[position].equalsIgnoreCase("Fresco")){
            cls = FrescoActivity.class;
        }else if(frameList[position].equalsIgnoreCase("PulltoRefresh")){
            cls = PullToRefreshActivity.class;
        }else if(frameList[position].equalsIgnoreCase("UniversalVideoView")){
            cls = UniversalVideoViewActivity.class;
        }else if(frameList[position].equalsIgnoreCase("JieCaoVideoPlayer")){
            cls = JieCaoVideoPlayerActivity.class;
        }else if(frameList[position].equalsIgnoreCase("Banner")){
            cls = BannerActivity.class;
        }else if(frameList[position].equalsIgnoreCase("CountdownView秒杀倒计时")){
            cls = CountDownViewActivity.class;
        }else if(frameList[position].equalsIgnoreCase("OpenDanmaku弹幕")){
            cls = OpenDanmakuActivity.class;
        }else if(frameList[position].equalsIgnoreCase("TabLayout&ViewPager")){
            cls = TabLayoutMainActivity.class;
        }else if(frameList[position].equalsIgnoreCase("CoordinatorLayout&FloatingActionButton")){
            cls = CoordinatorLayoutActivity.class;
        }else if(frameList[position].equalsIgnoreCase("Snackbar")){
            cls = SnackbarActivity.class;
        }
        startToActivity(cls);
    }

    private void startToActivity(Class cls) {
        if(cls != null){
            startActivity(new Intent(MyApplication.getMyApplicationInstance(),cls));
            cls = null;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
