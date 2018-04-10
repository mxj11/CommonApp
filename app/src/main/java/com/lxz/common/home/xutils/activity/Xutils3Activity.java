package com.lxz.common.home.xutils.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_xutils)
public class Xutils3Activity extends AppCompatActivity {
    @ViewInject(R.id.tv_title)
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xutils);
        x.view().inject(this);
        tvTitle.setText("xutis3使用");
    }
    private Class cls;
    @Event(value = {R.id.btn_annotation,R.id.btn_net,R.id.btn_image,R.id.btn_image_list})
    private void getEvent(View view){//必须私有，公有不行
        switch (view.getId()){
            case R.id.btn_annotation:
                cls = MyFragmentActivity.class;
                break;
            case R.id.btn_net:
                cls = Xutils3NetActivity.class;
                break;
            case R.id.btn_image:

                break;
            case R.id.btn_image_list:

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
