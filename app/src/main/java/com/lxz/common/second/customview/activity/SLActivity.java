package com.lxz.common.second.customview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lxz.common.R;
import com.lxz.common.home.adapter.FrameListAdapter;
import com.lxz.common.third.commontools.L;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SLActivity extends AppCompatActivity {

    @Bind(R.id.lv)
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sl);
        ButterKnife.bind(this);
        String[] data = initData();
        L.e(data.toString());
        lv.setAdapter(new FrameListAdapter(this,data));
    }

    private String[] initData() {
        String[] str = new String[20];
        for(int i = 0;i<20;i++){
            str[i] = "data"+i;
        }
        return str;
    }
}
