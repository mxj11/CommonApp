package com.lxz.common.fourth.udp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UDPActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_udp)
    TextView tvUdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_udp);
        ButterKnife.bind(this);
        tvTitle.setText("udp");
        showNote();
    }

    private void showNote() {
        String note = "udp的特点:\n" +
                "•  将数据及源和目的封装成数据包中，不需要建立连接\n" +
                "•  每个数据报的大小在限制在64k内\n" +
                "•  因无连接，是不可靠协议\n" +
                "•  不需要建立连接，速度快\n";
        tvUdp.setText(note);
    }

    @OnClick(R.id.btn_udp)
    public void onViewClicked() {
        startActivity(new Intent(this,UDPChatActvity.class));
    }
}
