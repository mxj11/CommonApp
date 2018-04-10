package com.lxz.common.fourth.tcp_ip;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.lxz.common.third.commontools.T;
import android.os.Handler;

public class IPActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.brn_get_local_ip)
    AppCompatButton brnGetLocalIp;
    @Bind(R.id.brn_get_remote_ip)
    AppCompatButton brnGetRemoteIp;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String result = (String) msg.obj;
                    T.showLong(IPActivity.this,result);
                    break;
                case 1:
                    String result1 = (String) msg.obj;
                    T.showLong(IPActivity.this,result1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        ButterKnife.bind(this);
        tvTitle.setText("ip");
    }

    @OnClick({R.id.brn_get_local_ip, R.id.brn_get_remote_ip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.brn_get_local_ip:
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            InetAddress ia = InetAddress.getLocalHost();
                            Message msg = handler.obtainMessage();
                            msg.what = 0;
                            msg.obj = "本地主机地址:"+ia.getHostAddress()+"本地主机名称:"+ia.getHostName();
                            handler.sendMessage(msg);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
            case R.id.brn_get_remote_ip:
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            InetAddress ia = InetAddress.getByName("www.baidu.com");
                            Message msg = handler.obtainMessage();
                            msg.what = 1;
                            msg.obj = "百度主机地址:"+ia.getHostAddress()+"百度主机名称:"+ia.getHostName();
                            handler.sendMessage(msg);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
    }
}
