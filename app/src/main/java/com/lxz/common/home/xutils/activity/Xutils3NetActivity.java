package com.lxz.common.home.xutils.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_xutils3_net)
public class Xutils3NetActivity extends AppCompatActivity {
    @ViewInject(R.id.tv_title)
    private TextView tvTitle;
    @ViewInject(R.id.btn_get)
    private Button btnGet;
    @ViewInject(R.id.btn_post)
    private Button btnPost;
    @ViewInject(R.id.btn_downloadfile)
    private Button btnDownload;
    @ViewInject(R.id.btn_uploadfile)
    private Button btnUpload;
    @ViewInject(R.id.tv_result)
    private TextView tvShow;
    @ViewInject(R.id.progressbar)
    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xutils3_net);
        x.view().inject(this);
        tvTitle.setText("xutils3联网");
    }
    @Event(value = {R.id.btn_get,R.id.btn_post,R.id.btn_downloadfile,R.id.btn_uploadfile})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.btn_get:
                xutils3Get();
                 break;
            case R.id.btn_post:
                xutils3Post();
                 break;
            case R.id.btn_downloadfile:
                xutils3Download();
                 break;
            case R.id.btn_uploadfile:
                xutils3Upload();
                 break;
        }
    }

    private void xutils3Upload() {
        RequestParams params = new RequestParams("http://192.168.1.16:8080/FileUpload/FileUploadServlet");
        //以表单方式上传
        params.setMultipart(true);
        //设置上传文件的路径
        params.addBodyParameter("File",new File(Environment.getExternalStorageDirectory()+"/atguigu/480.mp4"),null,"oppo.mp4");
        x.http().post(params, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法，并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG", "onSuccess==" + file.toString());
                Toast.makeText(Xutils3NetActivity.this, "onSuccess==" + file.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG", "onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG", "onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressbar.setMax((int) total);
                progressbar.setProgress((int) current);
                Log.e("TAG", "onLoading==" + current + "/" + total + ",isDownloading==" + isDownloading);

            }
        });
    }

    private void xutils3Download() {
        RequestParams params = new RequestParams("http://vfx.mtime.cn/Video/2016/09/15/mp4/160915092608935956_480.mp4");
        //设置保存数据
        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/atguigu/480.mp4");
        //设置是否可以立即取消下载
        params.setCancelFast(true);
        //设置是否自动根据头信息命名
        params.setAutoRename(false);
        //设置断点续传
        params.setAutoResume(true);

        params.setExecutor(new PriorityExecutor(3, true));//自定义线程池,有效的值范围[1, 3], 设置为3时, 可能阻塞图片加载.


        x.http().get(params, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法，并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG","onSuccess=="+file.toString());
                Toast.makeText(Xutils3NetActivity.this, "onSuccess=="+file.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","onError=="+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG","onCancelled=="+cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG","onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG","onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG","onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressbar.setMax((int) total);
                progressbar.setProgress((int) current);
                Log.e("TAG", "onLoading=="+current+"/"+total+",isDownloading=="+isDownloading);

            }
        });
    } 

    private void xutils3Post() {
        RequestParams param = new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().post(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                tvShow.setText("post请求结果"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                tvShow.setText("请求error");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void xutils3Get() {
        RequestParams param = new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                tvShow.setText("get请求结果"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                tvShow.setText("请求error");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
