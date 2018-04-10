package com.lxz.common.home.glide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bt_glide_base)
    Button btGlideBase;
    @Bind(R.id.bt_glide_recyclerview)
    Button btGlideRecyclerview;
    @Bind(R.id.bt_glide_tranfromations)
    Button btGlideTranfromations;
    @Bind(R.id.activity_glide)
    LinearLayout activityGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        tvTitle.setText("glide使用");
    }
    private Class cls;
    @OnClick({R.id.bt_glide_base, R.id.bt_glide_recyclerview, R.id.bt_glide_tranfromations})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_glide_base:
                cls = GlideBaseActivity.class;
                break;
            case R.id.bt_glide_recyclerview:
                cls = GlideRecyclerviewActivity.class;
                break;
            case R.id.bt_glide_tranfromations:
                cls = GlideTranformationsActivity.class;
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
