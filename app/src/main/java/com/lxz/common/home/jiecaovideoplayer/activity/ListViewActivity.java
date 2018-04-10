package com.lxz.common.home.jiecaovideoplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lxz.common.R;
import com.lxz.common.home.recycleview.activity.RecycleViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListViewActivity extends AppCompatActivity {

    @Bind(R.id.normal_list)
    Button normalList;
    @Bind(R.id.viewpayer_list)
    Button viewpayerList;
    @Bind(R.id.multi_holder_list)
    Button multiHolderList;
    @Bind(R.id.recyleview)
    Button recyleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
    }
    private Class cls;
    @OnClick({R.id.normal_list, R.id.viewpayer_list, R.id.multi_holder_list, R.id.recyleview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.normal_list:
                cls = ListViewNormalActivity.class;
                break;
            case R.id.viewpayer_list:
                cls = ListViewViewpagerActivity.class;
                break;
            case R.id.multi_holder_list:
                cls = ListViewMultiHolderActivity.class;
                break;
            case R.id.recyleview:
                cls = RecyclerViewNormalActivity.class;
                break;
        }
        startToActivity(cls);
    }
    private void startToActivity(Class cls){
        if(cls != null){
            startActivity(new Intent(this,cls));
        }
    }
}
