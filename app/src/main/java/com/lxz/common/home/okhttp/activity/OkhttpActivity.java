package com.lxz.common.home.okhttp.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.home.adapter.FrameListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OkhttpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Bind(R.id.lv_okhttp_list)
    ListView lvOkhttpList;

    private String[] okhttpList = new String[]{
            "原生okhttp","okhttp-utils","okhttp-okgo"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
        setAdapter();
        setListener();
    }

    private void setListener() {
        lvOkhttpList.setOnItemClickListener(this);
    }

    private void setAdapter() {
        lvOkhttpList.setAdapter(new FrameListAdapter(MyApplication.getMyApplicationInstance(),okhttpList));
    }
    private Class cls;
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(okhttpList[position].toLowerCase().equals("原生okhttp")){
            cls = OkhttpRawActivity.class;
            startToActivity(cls);
        }else if(okhttpList[position].toLowerCase().equals("okhttp-utils")){
            cls = OkhttpUtilsHYActivity.class;
            startToActivity(cls);
        }else{
//            cls = OkhttpUtilsJeasonActivity.classes;
            try {
                PackageManager packageManager = getPackageManager();
                Intent intent=new Intent();
                intent = packageManager.getLaunchIntentForPackage("com.lzy.demo");
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(OkhttpActivity.this,"请在项目中先运行安装okgolib",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void startToActivity(Class cls) {
        if(cls != null){
            startActivity(new Intent(MyApplication.getMyApplicationInstance(),cls));
            cls = null;
        }
    }
}
