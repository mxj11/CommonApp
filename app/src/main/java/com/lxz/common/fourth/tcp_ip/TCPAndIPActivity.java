package com.lxz.common.fourth.tcp_ip;

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

public class TCPAndIPActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_ip)
    AppCompatButton btnIp;
    @Bind(R.id.btn_tcp)
    AppCompatButton btnTcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpand_ip);
        ButterKnife.bind(this);
        tvTitle.setText("tcp/ip协议");
    }
    private Class cls;
    @OnClick({R.id.btn_ip, R.id.btn_tcp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ip:
                cls = IPActivity.class;
                break;
            case R.id.btn_tcp:
                cls = TCPActivity.class;
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
