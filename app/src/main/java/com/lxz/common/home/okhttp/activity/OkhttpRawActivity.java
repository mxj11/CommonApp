package com.lxz.common.home.okhttp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxz.common.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 原生的okhttp需要自己开启线程，比较麻烦
 */
public class OkhttpRawActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = OkhttpRawActivity.class.getSimpleName();
    private static final int GET = 0;
    private static final int POST = 1;
    @Bind(R.id.tv_show_data)
    TextView tvShowData;
    private OkHttpClient client = new OkHttpClient();
    @Bind(R.id.btn_raw_get)
    Button btnRawGet;
    @Bind(R.id.btn_raw_post)
    Button btnRawPost;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET:
                    String result_get = (String) msg.obj;
                    tvShowData.setText(result_get);
                    break;
                case POST:
                    String result_post = (String) msg.obj;
                    tvShowData.setText(result_post);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_raw);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_raw_get, R.id.btn_raw_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_raw_get:
                tvShowData.setText("");
                get_();
                break;
            case R.id.btn_raw_post:
                tvShowData.setText("");
                post_();
                break;
        }
    }

    public void get_() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    String result = get("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Log.e(TAG, result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String get(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void post_() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    String result = post("http://api.m.mtime.cn/PageSubArea/TrailerList.api", "");
                    Log.e(TAG, result);
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String post(String url, String json) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
















