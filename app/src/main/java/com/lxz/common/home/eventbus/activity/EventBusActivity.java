package com.lxz.common.home.eventbus.activity;

import android.content.Intent;
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

public class EventBusActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bt_eventbus_send)
    Button btEventbusSend;
    @Bind(R.id.bt_eventbus_sticky)
    Button btEventbusSticky;
    @Bind(R.id.tv_eventbus_result)
    TextView tvEventbusResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);
        tvTitle.setText("EventBus使用");
        //1、注册发送消息监听
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.bt_eventbus_send, R.id.bt_eventbus_sticky})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_eventbus_send:
                //跳转到要发送消息的页面，本页面作为接受消息
                Intent intent = new Intent(EventBusActivity.this, SecondEventBusActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_eventbus_sticky:
                //二、发送粘性事件
                EventBus.getDefault().postSticky(new EventSticky("我是粘性事件"));
                Intent intent1 = new Intent(EventBusActivity.this, SecondEventBusActivity.class);
                startActivity(intent1);
                break;
        }
    }

    // 5接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MesssageEventBus(EventMessage event){
        // 显示接收的消息
        tvEventbusResult.setText(event.name);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2、取消注册发送消息监听
        EventBus.getDefault().unregister(this);
    }
}
