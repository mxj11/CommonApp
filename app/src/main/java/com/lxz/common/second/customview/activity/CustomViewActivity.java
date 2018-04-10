package com.lxz.common.second.customview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewActivity extends AppCompatActivity {

    @Bind(R.id.btn_one)
    AppCompatButton btnOne;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
        tvTitle.setText("自定义View");
    }

    Class cls = null;

    @OnClick({R.id.btn_one,R.id.btn_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                cls = MyCustomActivity.class;
                break;
            case R.id.btn_two:
                cls = EventActivity.class;
                break;
        }
        startToActivity(cls);
    }

    private void startToActivity(Class cls) {
        if (cls != null) {
            startActivity(new Intent(MyApplication.getMyApplicationInstance(), cls));
            cls = null;
        }
    }
}
