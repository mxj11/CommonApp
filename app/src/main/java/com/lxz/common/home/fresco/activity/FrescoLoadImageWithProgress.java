package com.lxz.common.home.fresco.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FrescoLoadImageWithProgress extends AppCompatActivity {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.sdv_fresco_spimg)
    SimpleDraweeView sdvFrescoSpimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_load_image_with_progress);
        ButterKnife.bind(this);
        tvTitle.setText("带进度条的图片");
        initData();
    }

    private void initData() {
        // 设置样式
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());

        GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();

        sdvFrescoSpimg.setHierarchy(hierarchy);

        // 加载图片的地址
        Uri uri = Uri.parse("http://www.sinaimg.cn/qc/photo_auto/photo/34/09/39653409/39653409_140.jpg");

        // 加载图片
        sdvFrescoSpimg.setImageURI(uri);
    }
}
