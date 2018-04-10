package com.lxz.common.home.imageloader.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.imageloader.adapter.ImageloaderListviewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageloaderListviewActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_imageloader)
    ListView lvImageloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader_listview);
        ButterKnife.bind(this);
        tvTitle.setText("Imageloader在listView中使用");
        setAdapter();
    }

    private void setAdapter() {
        lvImageloader.setAdapter(new ImageloaderListviewAdapter(this));
    }
}
