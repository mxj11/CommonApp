package com.lxz.common.home.picasso.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.home.picasso.adapter.PicassoTransformationsAdapter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PicassoActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bt_picasso_base)
    Button btPicassoBase;
    @Bind(R.id.bt_picasso_listview)
    Button btPicassoListview;
    @Bind(R.id.bt_picasso_tranformations)
    Button btPicassoTranformations;
    @Bind(R.id.iv_picasso_result1)
    ImageView ivPicassoResult1;
    @Bind(R.id.iv_picasso_result2)
    ImageView ivPicassoResult2;
    @Bind(R.id.iv_picasso_result3)
    ImageView ivPicassoResult3;
    @Bind(R.id.activity_picasso)
    LinearLayout activityPicasso;

    private String url = "http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);
        tvTitle.setText("Picasso使用");
    }

    @OnClick({R.id.bt_picasso_base, R.id.bt_picasso_listview, R.id.bt_picasso_tranformations})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_picasso_base:
                baseUse();
                break;
            case R.id.bt_picasso_listview:
                startActivity(new Intent(PicassoActivity.this,PicassoListviewActivity.class));
                break;
            case R.id.bt_picasso_tranformations:
                startActivity(new Intent(PicassoActivity.this,PicassoTransfromationsActivity.class));
                break;
        }
    }

    private void baseUse() {
        //请求加载一张图片
        Picasso.with(this).load(url).into(ivPicassoResult1);
        //请求加载一张裁剪图片
        Picasso.with(this).load(url).resize(100,100).into(ivPicassoResult2);
        //请求加载一张旋转180度图片
        Picasso.with(this).load(url).rotate(180).into(ivPicassoResult3);
    }


}
