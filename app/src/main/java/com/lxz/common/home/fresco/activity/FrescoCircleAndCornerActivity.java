package com.lxz.common.home.fresco.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoCircleAndCornerActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.sdv_fresco_circleandcorner)
    SimpleDraweeView sdvFrescoCircleandcorner;
    @Bind(R.id.bt_fresco_circle)
    Button btFrescoCircle;
    @Bind(R.id.bt_fresco_corner)
    Button btFrescoCorner;
    private Uri uri;
    private GenericDraweeHierarchyBuilder builder;
    private RoundingParams parames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_circle_and_corner);
        ButterKnife.bind(this);
        tvTitle.setText("设置图片圆形圆角");
        initData();
    }

    @OnClick({R.id.bt_fresco_circle, R.id.bt_fresco_corner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fresco_circle:
                // 设置圆形图片
                parames = RoundingParams.asCircle();
                GenericDraweeHierarchy hierarchy = builder.setRoundingParams(parames).build();
                sdvFrescoCircleandcorner.setHierarchy(hierarchy);

                sdvFrescoCircleandcorner.setImageURI(uri);
                break;
            case R.id.bt_fresco_corner:

                parames = RoundingParams.fromCornersRadius(50f);
                parames.setOverlayColor(getResources().getColor(android.R.color.holo_red_light));//覆盖层
                parames.setBorder(getResources().getColor(android.R.color.holo_blue_light), 5);//边框

                GenericDraweeHierarchy hierarchy1 = builder.setRoundingParams(parames).build();
                sdvFrescoCircleandcorner.setHierarchy(hierarchy1);

                // 加载图片
                sdvFrescoCircleandcorner.setImageURI(uri);
                break;
        }
    }
    private void initData() {
        tvTitle.setText("圆形和圆角图片");

        uri = Uri.parse("http://www.sinaimg.cn/qc/photo_auto/photo/91/66/39169166/39169166_140.jpg");
 
        builder = new GenericDraweeHierarchyBuilder(getResources());
    }
}
