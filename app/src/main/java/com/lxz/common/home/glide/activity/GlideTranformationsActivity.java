package com.lxz.common.home.glide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.glide.adapter.GlideTranformationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GlideTranformationsActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rv_glide_transformations)
    RecyclerView rvGlideTransformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_tranformations);
        ButterKnife.bind(this);
        tvTitle.setText("图片变换");
        initData();
    }

    private void initData() {
        List<String> data = new ArrayList<>();
        for (int i = 1;i<21;i++){
            data.add(""+i);
        }
        GlideTranformationsAdapter adapter = new GlideTranformationsAdapter(this,data);
        rvGlideTransformations.setAdapter(adapter);
        rvGlideTransformations.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
