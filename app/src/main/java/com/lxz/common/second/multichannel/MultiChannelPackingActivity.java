package com.lxz.common.second.multichannel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import com.lxz.common.third.commontools.T;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiChannelPackingActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_umeng)
    AppCompatButton btnUmeng;
    @Bind(R.id.btn_meituan)
    AppCompatButton btnMeituan;
    @Bind(R.id.btn_360)
    AppCompatButton btn360;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_channel_packing);
        ButterKnife.bind(this);
        tvTitle.setText("多渠道打包");
    }

    @OnClick({R.id.btn_umeng, R.id.btn_meituan, R.id.btn_360})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_umeng:
                T.showShort(MultiChannelPackingActivity.this,getChannel());
                break;
            case R.id.btn_meituan:
                T.showShort(MultiChannelPackingActivity.this,ChannelUtil.getChannel(MultiChannelPackingActivity.this,"defalut market"));
                break;
            case R.id.btn_360:
                //第一个参数为context，第二个是密码，第三个是默认值
                //MCPTool.getChannelId(this,"12345678","");
                break;
        }
    }

    private String getChannel() {
        try {
            PackageManager pm = getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("UMENG_CHANNEL");
            } catch (PackageManager.NameNotFoundException ignored) {
           }
         return "defalut market";
    }
}
