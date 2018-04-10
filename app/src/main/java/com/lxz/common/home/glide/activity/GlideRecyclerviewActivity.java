package com.lxz.common.home.glide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.glide.adapter.GlideRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GlideRecyclerviewActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rv_glide)
    RecyclerView rvGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_recyclerview);
        ButterKnife.bind(this);
        tvTitle.setText("在recyclerview中加载图片");
        initData();
    }

    private void initData() {
        GlideRecyclerViewAdapter adapter = new GlideRecyclerViewAdapter(this);
        rvGlide.setAdapter(adapter);
        rvGlide.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
