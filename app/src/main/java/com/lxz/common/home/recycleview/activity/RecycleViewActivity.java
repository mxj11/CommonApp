package com.lxz.common.home.recycleview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.recycleview.DividerListItemDecoration;
import com.lxz.common.home.recycleview.adapter.RecycleViewAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecycleViewActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.btn_list)
    Button btnList;
    @Bind(R.id.btn_grid)
    Button btnGrid;
    @Bind(R.id.btn_flow)
    Button btnFlow;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.activity_recycle_view)
    LinearLayout activityRecycleView;

    private ArrayList<String> mData;
    private RecycleViewAdapter recycleViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
        tvTitle.setText("RecycleView使用");
        setAdapter();
    }

    private void setAdapter() {
        mData = new ArrayList<>();
        //准备数据集合
        for (int i=0;i<100;i++){
            mData.add("Content_"+i);
        }
        recycleViewAdapter = new RecycleViewAdapter(this, mData);
        recyclerview.setAdapter(recycleViewAdapter);
        //添加RecyclerView的分割线
        recyclerview.addItemDecoration(new DividerListItemDecoration(RecycleViewActivity.this,DividerListItemDecoration.VERTICAL_LIST));
        //LinearLayoutManager.VERTICAL  HORIZONTAL方向设置
        //false从一个开始，true定位到最后一个
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
//        recyclerview.scrollToPosition(mData.size()-1);
        //设置动画
        recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_list, R.id.btn_grid, R.id.btn_flow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                recycleViewAdapter.addData(0,"new_content");
                recyclerview.scrollToPosition(0);
                break;
            case R.id.btn_delete:
                recycleViewAdapter.removeData(0);
                break;
            case R.id.btn_list:
                recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_grid:
                recyclerview.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_flow:
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
