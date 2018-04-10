package com.lxz.common.second.frame.mvp.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.lxz.common.R;
import com.lxz.common.second.frame.mvp.adapter.GirlListAdapter;
import com.lxz.common.second.frame.mvp.bean.Girl;
import com.lxz.common.second.frame.mvp.presenter.GirlPresenter;
import com.lxz.common.second.frame.mvp.view.IGirlView;

import java.util.List;

public class MVPActivity extends Activity implements IGirlView {

    private ListView lv;
    private GirlPresenter mGirlPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setView();
        mGirlPresenter = new GirlPresenter(this);
        mGirlPresenter.fetch();
    }

    private void setView() {
        lv = (ListView) findViewById(R.id.lv_list);
    }

    @Override
    public void showToast() {
        Toast.makeText(this, "正在加载.....", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGirl(List<Girl> girls) {
        lv.setAdapter(new GirlListAdapter(girls, this));
    }
}
