package com.lxz.common.second.h5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidAndH5Activity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_java_and_js)
    Button btnJavaAndJs;
    @Bind(R.id.btn_js_call_java)
    Button btnJsCallJava;
    @Bind(R.id.btn_js_call_phone)
    Button btnJsCallPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_and_h);
        ButterKnife.bind(this);
        tvTitle.setText("Android和H5互调");
    }

    @OnClick({R.id.btn_java_and_js, R.id.btn_js_call_java, R.id.btn_js_call_phone})
    public void onViewClicked(View view) {
        Class cls = null;
        switch (view.getId()) {
            case R.id.btn_java_and_js:
                cls = JavaAndJSActivity.class;
                break;
            case R.id.btn_js_call_java:
                cls = JsCallVideoActivity.class;
                break;
            case R.id.btn_js_call_phone:
                cls = JsCallToCallPhoneActivity.class;
                break;
        }
        startToActivity(cls);
    }
    private void startToActivity(Class cls) {
        if(cls != null){
            startActivity(new Intent(MyApplication.getMyApplicationInstance(),cls));
            cls = null;
        }
    }
}
