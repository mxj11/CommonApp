package com.lxz.common.home.imageloader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.imageloader.adapter.ImageloaderGridviewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageloaderGridviewActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.gv_imageloader_gridview)
    GridView gvImageloaderGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader_gridview);
        ButterKnife.bind(this);
        tvTitle.setText("Imageloader在gridview中使用");
        setAdapter();
    }

    private void setAdapter() {
        gvImageloaderGridview.setAdapter(new ImageloaderGridviewAdapter(this));
    }
}
