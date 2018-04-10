package com.lxz.common.fourth.tcp_ip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TCPActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_tcp)
    TextView tvTcp;
    @Bind(R.id.btn_tcp_visitor)
    AppCompatButton btnTcpVisitor;
    @Bind(R.id.btn_tcp_tran)
    AppCompatButton btnTcpTran;
    @Bind(R.id.btn_tcp_upload_text)
    AppCompatButton btnTcpUploadText;
    @Bind(R.id.btn_tcp_upload_img)
    AppCompatButton btnTcpUploadImg;
    @Bind(R.id.btn_tcp_concurrence_upload_img)
    AppCompatButton btnTcpConcurrenceUploadImg;
    @Bind(R.id.btn_tcp_concurrence_login)
    AppCompatButton btnTcpConcurrenceLogin;
    @Bind(R.id.activity_tcp)
    LinearLayout activityTcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);
        ButterKnife.bind(this);
        tvTitle.setText("tcp");
        showNote();
    }

    private void showNote() {
        String note = "tcp的特点:\n" +
                "•  建立连接，形成传输数据的通道。\n" +
                "•  在连接中进行大数据量传输\n" +
                "•  通过三次握手完成连接，是可靠协议\n" +
                "•  必须建立连接，效率会稍低\n";
        tvTcp.setText(note);
    }
    private Class cls;
    @OnClick({R.id.btn_tcp_visitor, R.id.btn_tcp_tran, R.id.btn_tcp_upload_text,
            R.id.btn_tcp_upload_img, R.id.btn_tcp_concurrence_upload_img,R.id.btn_tcp_concurrence_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tcp_visitor:
                cls = CSVistorActivity.class;
                break;
            case R.id.btn_tcp_tran:
                cls = ServerTextConvertorActivity.class;
                break;
            case R.id.btn_tcp_upload_text:
                cls = UploadTextActivity.class;
                break;
            case R.id.btn_tcp_upload_img:
                cls = UploadImgActivity.class;
                break;
            case R.id.btn_tcp_concurrence_upload_img:
                cls = ConcurrenceUploadImgActivity.class;
                break;
            case R.id.btn_tcp_concurrence_login:
                cls = ConcurrenceLoginActivity.class;
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
