package com.lxz.common.home.eventbus.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxz.common.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondEventBusActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bt_eventbus_send_main)
    Button btEventbusSendMain;
    @Bind(R.id.bt_eventbus_send_sticky)
    Button btEventbusSendSticky;
    @Bind(R.id.tv_eventbus_send_result)
    TextView tvEventbusSendResult;
    private boolean isFirstFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_event_bus);
        ButterKnife.bind(this);
        tvTitle.setText("第二个界面");
    }

    @OnClick({R.id.bt_eventbus_send_main, R.id.bt_eventbus_send_sticky})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_eventbus_send_main:
                //4、发送消息
                send(new EventMessage("我是来自第二个界面的消息"));
                SecondEventBusActivity.this.finish();
                break;
            case R.id.bt_eventbus_send_sticky:
                if(isFirstFlag) {
                    isFirstFlag = false;
                    // 四 注册
                    EventBus.getDefault().register(SecondEventBusActivity.this);
                }
                break;
        }
    }
    private void send(EventMessage eventMessage) {
        EventBus.getDefault().post(eventMessage);
    }

    // 三 接收粘性事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void StickyEventBus(EventSticky event){
        // 显示接收的数据
        tvEventbusSendResult.setText(event.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 五 解注册
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(SecondEventBusActivity.this);
    }
}
