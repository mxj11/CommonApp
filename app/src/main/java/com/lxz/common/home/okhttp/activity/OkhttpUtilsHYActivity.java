package com.lxz.common.home.okhttp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 一般的get请求
 * 一般的post请求
 * 基于Http Post的文件上传（类似表单）
 * 文件下载/加载图片
 * 上传下载的进度回调
 * 支持取消某个请求
 * 支持自定义Callback
 * 支持HEAD、DELETE、PATCH、PUT
 * 支持session的保持
 * 支持自签名网站https的访问，提供方法设置下证书就行
 */
public class OkhttpUtilsHYActivity extends AppCompatActivity {

    @Bind(R.id.btn_hy_get)
    Button btnHyGet;
    @Bind(R.id.btn_hy_post)
    Button btnHyPost;
    @Bind(R.id.btn_hy_download)
    Button btnHyDownload;
    @Bind(R.id.btn_hy_upload)
    Button btnHyUpload;
    @Bind(R.id.btn_hy_image)
    Button btnHyImage;
    @Bind(R.id.btn_hy_list_image)
    Button btnHyListImage;
    @Bind(R.id.tv_hy_show_data)
    TextView tvHyShowData;
    @Bind(R.id.pb_hy)
    ProgressBar pbHy;
    @Bind(R.id.iv_hy_icon)
    ImageView ivHyIcon;
    @Bind(R.id.activity_okhttp_utils_hy)
    LinearLayout activityOkhttpUtilsHy;
    private String TAG = OkhttpUtilsHYActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_utils_hy);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_hy_get, R.id.btn_hy_post, R.id.btn_hy_download, R.id.btn_hy_upload, R.id.btn_hy_image, R.id.btn_hy_list_image})
    public void onViewClicked(View view) {
        tvHyShowData.setText("");
        switch (view.getId()) {
            case R.id.btn_hy_get:
                getRequest();
                break;
            case R.id.btn_hy_post:
                postRequest();
                break;
            case R.id.btn_hy_download:
                download();
                break;
            case R.id.btn_hy_upload:
                upload();
                break;
            case R.id.btn_hy_image:
                imageRequset();
                break;
            case R.id.btn_hy_list_image:
                listImageRequest();
                break;
        }
    }


    private void listImageRequest() {

    }

    private void imageRequset() {//没有做缓存
        tvHyShowData.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvHyShowData.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        ivHyIcon.setImageBitmap(bitmap);
                    }
                });
    }

    private void upload() {
        String mBaseUrl = "http://192.168.0.165:8080/FileUpload/FileUploadServlet";
        File file = new File(Environment.getExternalStorageDirectory(), "logo.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "test1.txt");
        if (!file.exists()||!file2.exists())
        {
            Toast.makeText(OkhttpUtilsHYActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "杨光福");
        params.put("password", "123");

        String url = mBaseUrl ;
        OkHttpUtils.post()//
                .addFile("mFile", "server_afu.png", file)//
                .addFile("mFile", "server_test.txt", file2)//
                .url(url)
                .params(params)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }

                    @Override
                    public void onBefore(Request request, int id) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        pbHy.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }


                });
    }

    private void download() {
        String url = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724055620533327_480.mp4";
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "okhttp-utils-test.mp4") {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(File file, int id) {
                Log.e(TAG, "onResponse :" + file.getAbsolutePath());
            }

            @Override
            public void onBefore(Request request, int id) {

            }

            @Override
            public void inProgress(float progress, long total, int id) {
                pbHy.setProgress((int) (100 * progress));
                Log.e(TAG, "inProgress :" + (int) (100 * progress));
            }
        });
    }


    private void postRequest() {
        Log.i(TAG,"post请求");
        String url = "http://api.api68.com/pks/getLotteryPksInfo.do?issue=&lotCode=10001";
//        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils.post().url(url).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                tvHyShowData.setText(response);
            }
        });
    }

    private void getRequest() {
        Log.i(TAG,"get请求");
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils.get().url(url).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                tvHyShowData.setText(response);
            }
        });
    }
}
