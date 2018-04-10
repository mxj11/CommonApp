package com.lxz.common.home.coordinatorLayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    @Bind(R.id.lv)
    RecyclerView lv;
    private String[] list = new String[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        for (int i = 0; i < 30; i++) {
            list[i] = "item" + i;
        }
        setAdapter();
    }

    private void setAdapter() {
        lv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        lv.setAdapter(new RecelerViewAdapter(list, this));
    }

}
