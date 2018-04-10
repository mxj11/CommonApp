package com.lxz.common.fourth.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.lxz.common.R;
import com.lxz.common.fourth.xml.Dom4jParser;
import com.lxz.common.fourth.xml.PullParser;
import com.lxz.common.fourth.xml.SAXParser;
import com.lxz.common.third.commontools.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XMLActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_dom4j)
    AppCompatButton btnDom4j;
    @Bind(R.id.btn_sax)
    AppCompatButton btnSax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        ButterKnife.bind(this);
        tvTitle.setText("XML解析");
    }

    @OnClick({R.id.btn_dom4j, R.id.btn_sax,R.id.btn_pull})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dom4j:
                T.showLong(XMLActivity.this,new Dom4jParser().dom4jparser(XMLActivity.this).toString());
                break;
            case R.id.btn_sax:
                T.showLong(XMLActivity.this,new SAXParser().saxParser(XMLActivity.this).toString());
                break;
            case R.id.btn_pull:
                T.showLong(XMLActivity.this,new PullParser().pullParser(XMLActivity.this).toString());
                break;
        }
    }
}
