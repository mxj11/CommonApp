package com.lxz.common.third.commontools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.fourth.xml.Dom4jParser;
import com.lxz.common.third.commontools.PropertiesUtils;
import com.lxz.common.third.commontools.T;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConmonToolsActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conmon_tools);
        ButterKnife.bind(this);
        tvTitle.setText("常用工具类");
        findViewById(R.id.btn_properties).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(ConmonToolsActivity.this,PropertiesUtils.getValue("bind_device_success_audio_name",ConmonToolsActivity.this));
            }
        });
    }
}
