package com.lxz.common.home.pulltorefresh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PullToRefreshActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listview)
    Button listview;
    @Bind(R.id.gridview)
    Button gridview;
    @Bind(R.id.fragment)
    Button fragment;
    @Bind(R.id.viewpager)
    Button viewpager;
    @Bind(R.id.viewpager2)
    Button viewpager2;
    @Bind(R.id.webview)
    Button webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ButterKnife.bind(this);
        tvTitle.setText("Android_PullToRefresh");
    }

    @OnClick({R.id.listview, R.id.gridview, R.id.fragment, R.id.viewpager, R.id.viewpager2, R.id.webview})
    public void onViewClicked(View view) {
        Class cls = null;
        switch (view.getId()) {
            case R.id.listview:
                cls = PullToRefreshListActivity.class;
                break;
            case R.id.gridview:
                cls = PullToRefreshGridActivity.class;
                break;
            case R.id.fragment:
                cls = PullToRefreshListFragmentActivity.class;
                break;
            case R.id.viewpager:
                cls = PullToRefreshListInViewPagerActivity.class;
                break;
            case R.id.viewpager2:
                cls = PullToRefreshViewPagerActivity.class;
                break;
            case R.id.webview:
                cls = PullToRefreshWebViewActivity.class;
                break;
        }
        startToActivity(cls);
    }
    private void startToActivity(Class cls){
        if(cls != null){
            startActivity(new Intent(this,cls));
        }
    }
}
