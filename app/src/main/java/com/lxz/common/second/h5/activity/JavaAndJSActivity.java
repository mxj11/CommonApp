package com.lxz.common.second.h5.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JavaAndJSActivity extends AppCompatActivity {

    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_and_js);
        ButterKnife.bind(this);
        initWebView();
    }

    private void initWebView() {
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        //设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);

        //支持双击-前提是页面要支持才显示
//        webSettings.setUseWideViewPort(true);

        //支持缩放按钮-前提是页面要支持才显示
        webSettings.setBuiltInZoomControls(true);

        //设置客户端-不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());

        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJSInterface(),"Android");


        //加载网络资源
//        webView.loadUrl("http://10.0.2.2:8080/assets/JavaAndJavaScriptCall.html");
        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");

        //显示页面
//        setContentView(webView);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String number = etNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if(TextUtils.isEmpty(number) || TextUtils.isEmpty(password)){
            Toast.makeText(JavaAndJSActivity.this,"账号或密码不为空",Toast.LENGTH_SHORT).show();
        }else{
            login(number);
        }
    }

    private void login(String number) {
        webView.loadUrl("javascript:javaCallJs(" + "'" + number + "'" + ")");
        setContentView(webView);
    }

    /**
     * js可以调用该类的方法
     */
    class AndroidAndJSInterface{
        @JavascriptInterface
        public void showToast(){
            Toast.makeText(JavaAndJSActivity.this, "我被js调用了", Toast.LENGTH_SHORT).show();
        }
    }
}
