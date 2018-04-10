package com.lxz.common.home.json.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lxz.common.R;
import com.lxz.common.app.MyApplication;
import com.lxz.common.home.adapter.FrameListAdapter;
import com.lxz.common.home.json.fastjson.FastjsonActivity;
import com.lxz.common.home.json.gson.GsonActivity;
import com.lxz.common.home.json.jsonraw.JsonRawActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JsonActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Bind(R.id.lv_json_list)
    ListView lvJsonList;
    private String[] jsonList = new String[]{"原生json解析","Gson解析","fastjson解析"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);
        setAdapter();
        setListener();
    }

    private void setListener() {
        lvJsonList.setOnItemClickListener(this);
    }

    private void setAdapter() {
        lvJsonList.setAdapter(new FrameListAdapter(MyApplication.getMyApplicationInstance(),jsonList));
    }
    private Class cls;
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if(jsonList[position].equalsIgnoreCase("原生json解析")){
            cls = JsonRawActivity.class;
        }else if(jsonList[position].equalsIgnoreCase("Gson解析")){
            cls = GsonActivity.class;
        }else if(jsonList[position].equalsIgnoreCase("Fastjson解析")){
            cls = FastjsonActivity.class;
        }
        startToActivity(cls);
    }
    private void startToActivity(Class cls) {
        if(cls != null){
            startActivity(new Intent(MyApplication.getMyApplicationInstance(),cls));
            cls = null;
        }
    }
}
