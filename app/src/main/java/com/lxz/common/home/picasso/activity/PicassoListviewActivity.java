package com.lxz.common.home.picasso.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.picasso.adapter.PicassoListviewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicassoListviewActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_picasso)
    ListView lvPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_listview);
        ButterKnife.bind(this);
        tvTitle.setText("Picasso在listview中使用");
        setAdapter();
    }

    private void setAdapter() {
        lvPicasso.setAdapter(new PicassoListviewAdapter(this));
    }
}
