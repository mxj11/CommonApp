package com.lxz.common.home.afinal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxz.common.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;

public class AfinalActivity extends FinalActivity {
    @ViewInject(id=R.id.tv_title)
    private TextView tvTitle;
    @ViewInject(id=R.id.tv_afinal_result)
    private TextView tvShow;
    @ViewInject(id = R.id.iv_afinal)
    private ImageView ivShow;
    @ViewInject(id = R.id.bt_afinal_loadimage,click = "loadImage")
    private Button btnLoadImage;
    @ViewInject(id = R.id.bt_afinal_gettext,click = "getText")
    private Button btnGetText;
    @ViewInject(id = R.id.bt_afinal_loadfile,click = "download")
    private Button btnDownload;
    @ViewInject(id = R.id.bt_afinal_updatetext,click = "upload")
    private Button btnUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal);
        tvTitle.setText("Afinal使用");
    }
    public void loadImage(View view){
        FinalBitmap bitmap = FinalBitmap.create(this);
        bitmap.configLoadfailImage(R.mipmap.ic_launcher);// 网络请求图片时默认显示的图片
        bitmap.display(ivShow,"http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg");
    }
    public void getText(View view){
        FinalHttp http = new FinalHttp();
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        http.get(url, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                tvShow.setText("afinal加载成功:"+o.toString());
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                tvShow.setText("afinal加载失败:"+strMsg);
            }

            @Override
            public void onStart() {
                tvShow.setText("开始加载");
            }
        });
    }
    public void download(View view){
        FinalHttp finalHttp = new FinalHttp();

        // 请求网络资源的地址
        String url = "http://vfx.mtime.cn/Video/2016/10/11/mp4/161011092841270064_480.mp4";

        // 存放视频文件到本地位置
        String target = getFilesDir()+"/afinalmusic.mp4";

        finalHttp.download(url, target, true, new AjaxCallBack<File>() {
            @Override
            public AjaxCallBack<File> progress(boolean progress, int rate) {
                return super.progress(progress, rate);
            }

            @Override
            public void onStart() {
                tvShow.setText("afinal开始下载");
            }

            @Override
            public void onLoading(long count, long current) {
                tvShow.setText("afinal下载中:");
            }

            @Override
            public void onSuccess(File file) {
                tvShow.setText("afinal下载成功:");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                tvShow.setText("afinal下载失败:"+strMsg);
            }
        });

    }
    public void upload(View view){
        FinalHttp finalHttp = new FinalHttp();

        // 文件上传到服务器的位置
        String url  = "http://192.168.0.103:8080/FileUpload/FileUploadServlet";

        AjaxParams params = new AjaxParams();
        // 获取要上传的本地资源
        try {
            params.put("File",new File(getFilesDir()+"/afinalmusic.mp4"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finalHttp.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public AjaxCallBack<Object> progress(boolean progress, int rate) {
                return super.progress(progress, rate);
            }

            @Override
            public void onStart() {
                tvShow.setText("afinal开始上传");
            }

            @Override
            public void onLoading(long count, long current) {
                tvShow.setText("afinal上传中:");
            }

            @Override
            public void onSuccess(Object o) {
                tvShow.setText("afinal上传成功:");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                tvShow.setText("afinal上传失败:"+strMsg);
            }
        });
    }
}
