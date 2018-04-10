package com.lxz.common.home.imageloader.activity;

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

public class ImageLoaderActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bt_imageloader_listview)
    Button btImageloaderListview;
    @Bind(R.id.bt_imageloader_gridview)
    Button btImageloaderGridview;
    @Bind(R.id.bt_imageloader_viewpager)
    Button btImageloaderViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        ButterKnife.bind(this);
        tvTitle.setText("ImageLoader使用");
    }
    private Class cls;
    @OnClick({R.id.bt_imageloader_listview, R.id.bt_imageloader_gridview, R.id.bt_imageloader_viewpager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_imageloader_listview:
                cls = ImageloaderListviewActivity.class;
                break;
            case R.id.bt_imageloader_gridview:
                cls = ImageloaderGridviewActivity.class;
                break;
            case R.id.bt_imageloader_viewpager:
                cls = ImageloaderViewpagerActivity.class;
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
