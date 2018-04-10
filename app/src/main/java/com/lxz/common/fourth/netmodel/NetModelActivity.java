package com.lxz.common.fourth.netmodel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NetModelActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_show_note)
    TextView tvShowNote;
    @Bind(R.id.tv_protocol)
    TextView tvProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_model);
        ButterKnife.bind(this);
        tvTitle.setText("网络模型");
        showNote();
    }

    private void showNote() {
        String netModel = "\n二、网络模型：OSI参考模型和 TCP/IP参考模型" +
                "" +
                "" +
                "";
        String netElement = "一、网络通讯要素：\n" +
                "ip地址：网络中设备的标识；\n" +
                "端口号：\n1、用于标识进程的逻辑地址，不同进程的标识；\n2、有效端口：0~65535，其中0~1024系统使用或保留端口。；\n" +
                "传输协议：通讯的规则：常用的传输协议有tcp、udp协议。\n";
        tvShowNote.setText(netElement + netModel);
        String protocol = "应用层协议:http、ftp；传输层协议:tcp、udp；网际层协议:ip";
        tvProtocol.setText(protocol);
    }
}
