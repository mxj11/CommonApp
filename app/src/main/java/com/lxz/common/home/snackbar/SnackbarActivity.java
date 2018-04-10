package com.lxz.common.home.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.lxz.common.R;
import com.lxz.common.third.commontools.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnackbarActivity extends AppCompatActivity {

    @Bind(R.id.btn1)
    AppCompatButton btn1;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        ButterKnife.bind(this);
        tvTitle.setText("SnackBar");
    }

    @OnClick({R.id.btn1,R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn1:
                Snackbar snackbar = Snackbar.make(view,"请求成功",Snackbar.LENGTH_LONG);
                //设置SnackBar背景颜色
                snackbar.getView().setBackgroundResource(R.color.colorPrimary);
                //设置按钮文字颜色
                snackbar.setActionTextColor(Color.WHITE);
                //设置点击事件
                snackbar.setAction("点我", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShort(SnackbarActivity.this,"点击");
                    }
                });
                //设置回调
                snackbar.setCallback(new Snackbar.Callback(){
                    @Override
                    public void onShown(Snackbar sb) {
//                        T.showShort(SnackbarActivity.this,"出现");
                    }

                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
//                        T.showShort(SnackbarActivity.this,"取消");
                    }
                });
                snackbar.show();
                break;
            case R.id.btn2:
                TSnackbar tSnackbar = TSnackbar.make(view, "顶部", Snackbar.LENGTH_LONG);
                //设置SnackBar背景颜色
                tSnackbar.getView().setBackgroundResource(R.color.colorPrimary);
                //设置按钮文字颜色
                tSnackbar.setActionTextColor(Color.WHITE);
                tSnackbar.setAction("点我", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShort(SnackbarActivity.this,"点击");
                    }
                });
                tSnackbar.show();
                break;
        }
    }
}
