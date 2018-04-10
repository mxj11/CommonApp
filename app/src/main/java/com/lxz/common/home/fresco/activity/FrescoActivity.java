package com.lxz.common.home.fresco.activity;

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

public class FrescoActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bt_fresco_spimg)
    Button btFrescoSpimg;
    @Bind(R.id.bt_fresco_crop)
    Button btFrescoCrop;
    @Bind(R.id.bt_fresco_circleAndCorner)
    Button btFrescoCircleAndCorner;
    @Bind(R.id.bt_fresco_jpeg)
    Button btFrescoJpeg;
    @Bind(R.id.bt_fresco_gif)
    Button btFrescoGif;
    @Bind(R.id.bt_fresco_multi)
    Button btFrescoMulti;
    @Bind(R.id.bt_fresco_listener)
    Button btFrescoListener;
    @Bind(R.id.bt_fresco_resize)
    Button btFrescoResize;
    @Bind(R.id.bt_fresco_modifyImg)
    Button btFrescoModifyImg;
    @Bind(R.id.bt_fresco_autoSizeImg)
    Button btFrescoAutoSizeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        ButterKnife.bind(this);
        tvTitle.setText("Fresco使用");
    }
    Class cls;
    @OnClick({R.id.bt_fresco_spimg, R.id.bt_fresco_crop, R.id.bt_fresco_circleAndCorner, R.id.bt_fresco_jpeg, R.id.bt_fresco_gif, R.id.bt_fresco_multi, R.id.bt_fresco_listener, R.id.bt_fresco_resize, R.id.bt_fresco_modifyImg, R.id.bt_fresco_autoSizeImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco_spimg:
                cls = FrescoLoadImageWithProgress.class;
                break;
            case R.id.bt_fresco_crop:
                cls = FrescoCropActivity.class;
                break;
            case R.id.bt_fresco_circleAndCorner:
                cls = FrescoCircleAndCornerActivity.class;
                break;
            case R.id.bt_fresco_jpeg:
                break;
            case R.id.bt_fresco_gif:
                break;
            case R.id.bt_fresco_multi:
                break;
            case R.id.bt_fresco_listener:
                break;
            case R.id.bt_fresco_resize:
                break;
            case R.id.bt_fresco_modifyImg:
                break;
            case R.id.bt_fresco_autoSizeImg:
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
