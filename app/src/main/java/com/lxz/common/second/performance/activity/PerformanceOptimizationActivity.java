package com.lxz.common.second.performance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PerformanceOptimizationActivity extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_optimization);
        ButterKnife.bind(this);
        tvTitle.setText("性能优化");
    }
}
