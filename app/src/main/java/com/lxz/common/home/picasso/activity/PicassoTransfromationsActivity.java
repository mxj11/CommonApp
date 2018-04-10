package com.lxz.common.home.picasso.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.picasso.adapter.PicassoTransformationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicassoTransfromationsActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_picasso_transfromations)
    ListView lvPicassoTransfromations;
    List<String> mData=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_transfromations);
        ButterKnife.bind(this);
        tvTitle.setText("Picasso图片变换使用");
        setAdapter();
    }

    private void setAdapter() {
        mData = new ArrayList<String>();
        for(int i = 1;i < 36;i++){
            mData.add(i+"");
        }
        lvPicassoTransfromations.setAdapter(new PicassoTransformationsAdapter(this,mData));
    }
}
