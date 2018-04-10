package com.lxz.common.second.frame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.second.frame.mvp.activity.MVPActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameActivity extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_factory)
    Button btnFactory;
    @Bind(R.id.btn_signl)
    Button btnSignl;
    @Bind(R.id.btn_mvp)
    Button btnMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);
        tvTitle.setText("移动架构");
    }

    @OnClick({R.id.btn_factory, R.id.btn_signl, R.id.btn_mvp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_factory:
                break;
            case R.id.btn_signl:
                break;
            case R.id.btn_mvp:
                startToActivity(MVPActivity.class);
                break;
        }
    }
    private void startToActivity(Class cls) {
        if(cls != null){
            startActivity(new Intent(MyApplication.getMyApplicationInstance(),cls));
        }
    }
}
