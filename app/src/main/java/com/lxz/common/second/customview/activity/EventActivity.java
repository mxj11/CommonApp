package com.lxz.common.second.customview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lxz.common.R;
import com.lxz.common.third.commontools.L;
import com.lxz.common.third.commontools.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * 结论：
    1.控件的Listener事件触发的顺序是先onTouch，再onClick。
    2.控件的onTouch返回true，将会onClick事件没有了---阻止了事件的传递。
    返回false，才会传递onClick事件(才会传递up事件)
    3、onTouchListener的onTouch方法优先级比onTouchEvent高，会先触发。
    4、如果onTouchListener的onTouch方法返回了true，那么view里面的onTouchEvent就不会被调用了。
    5、顺序dispatchTouchEvent-->onTouchListener---return false-->onTouchEvent
    6、如果view为disenable,则：onTouchListener里面不会执行，但是会执行onTouchEvent(event)方法
    7、onTouchEvent方法中的ACTION_UP分支中触发onclick事件监听
        onTouchListener-->onTouch方法返回true，消耗此事件。down,但是up事件是无法到达onClickListener.
        onTouchListener-->onTouch方法返回false，不会消耗此事件

 */
public class EventActivity extends AppCompatActivity implements View.OnTouchListener {
    private MyButton btn2;

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.rl)
    LinearLayout rl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        btn1.setOnTouchListener(this);
        rl.setOnTouchListener(this);
        btn2 = (MyButton) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                T.showShort(EventActivity.this,"Button2 click");
                L.e("Button2 click");
            }
        });
        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                T.showShort(EventActivity.this,"Button2 touch");
                L.e("Button2 touch");
                return false;
//                return true;//onTouchEvent不会被触发，onClick事件也不会被触发
            }
        });
    }

    @OnClick({R.id.btn1, R.id.rl,R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
//                T.showShort(EventActivity.this,"Button1 click");
                L.e("Button1 click");
                break;
            case R.id.rl:
//                T.showShort(EventActivity.this,"RelativeLayout click");
                L.e("RelativeLayout click");
                break;
            case R.id.btn3:
                startActivity(new Intent(EventActivity.this,SLActivity.class));
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.btn1:
                L.e("Button1 touch");
                break;
            case R.id.rl:
//                T.showShort(EventActivity.this,"RelativeLayout touch");
                L.e("RelativeLayout touch");
                break;
        }
        return false;
//        return true;//onclick事件被消耗
    }
}
