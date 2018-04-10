package com.lxz.common.home.volley.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lxz.common.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolleyActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bt_volley_get)
    Button btVolleyGet;
    @Bind(R.id.bt_volley_post)
    Button btVolleyPost;
    @Bind(R.id.bt_volley_getjson)
    Button btVolleyGetjson;
    @Bind(R.id.bt_volley_imagerequest)
    Button btVolleyImagerequest;
    @Bind(R.id.bt_volley_imageloader)
    Button btVolleyImageloader;
    @Bind(R.id.bt_volley_networkimageview)
    Button btVolleyNetworkimageview;
    @Bind(R.id.iv_volley_result)
    ImageView ivVolleyResult;
    @Bind(R.id.iv_volley_networkimagview)
    NetworkImageView ivVolleyNetworkimagview;
    @Bind(R.id.tv_volley_result)
    TextView tvVolleyResult;
    @Bind(R.id.activity_volley)
    LinearLayout activityVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_volley_get, R.id.bt_volley_post, R.id.bt_volley_getjson, R.id.bt_volley_imagerequest, R.id.bt_volley_imageloader, R.id.bt_volley_networkimageview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_volley_get:
                volleyGet();
                break;
            case R.id.bt_volley_post:
                volleyPost();
                break;
            case R.id.bt_volley_getjson:
                volleyGetJson();
                break;
            case R.id.bt_volley_imagerequest:
                volleyImageRequest();
                break;
            case R.id.bt_volley_imageloader:
                volleyImageLoader();
                break;
            case R.id.bt_volley_networkimageview:
                volleyNetWordImage();
                break;
        }
    }

    private void volleyNetWordImage() {
        // 让控件显示
        ivVolleyNetworkimagview.setVisibility(View.VISIBLE);

        // 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        // 创建一个Imageloader
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

        // 默认图片和异常图片设置
        ivVolleyNetworkimagview.setDefaultImageResId(R.mipmap.ic_launcher);
        ivVolleyNetworkimagview.setErrorImageResId(R.mipmap.ic_launcher);

        // 加载图片
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        ivVolleyNetworkimagview.setImageUrl(url, imageLoader);
    }

    private void volleyImageLoader() {
        // 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

        // 加载图片
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        ivVolleyResult.setVisibility(View.VISIBLE);
        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(ivVolleyResult, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, imageListener);
    }

    private void volleyImageRequest() {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        // 2 创建一个图片的请求
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                // 正确接收到图片
                ivVolleyResult.setVisibility(View.VISIBLE);
                ivVolleyResult.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ivVolleyResult.setImageResource(R.mipmap.ic_launcher);
            }
        });

        // 3 将请求添加到请求队列中
        requestQueue.add(imageRequest);
    }

    private void volleyGetJson() {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        // 2 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                tvVolleyResult.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvVolleyResult.setText("请求失败" + volleyError);
            }
        });

        // 3 将创建的请求添加到请求队列中
        requestQueue.add(jsonObjectRequest);
    }

    private void volleyPost() {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        StringRequest stringRequset = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvVolleyResult.setText("volley-post请求成功:"+s);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvVolleyResult.setText("volley-post请求失败:"+volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
//                        map.put("value1","param1");
                return map;
            }
        };
        // 3 将创建的请求添加到请求队列中
        requestQueue.add(stringRequset);
    }

    private void volleyGet() {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        StringRequest stringRequset = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvVolleyResult.setText("volley-get请求成功:"+s);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvVolleyResult.setText("volley-get请求失败:"+volleyError);
            }
        });
        // 3 将创建的请求添加到请求队列中
        requestQueue.add(stringRequset);
    }
}
