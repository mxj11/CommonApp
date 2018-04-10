package com.lxz.common.home.json.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxz.common.R;
import com.lxz.common.home.json.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GsonActivity extends AppCompatActivity {

    @Bind(R.id.bt_gson_tojavaobject)
    Button btGsonTojavaobject;
    @Bind(R.id.bt_gson_tojavalist)
    Button btGsonTojavalist;
    @Bind(R.id.bt_gson_javatojsonobject)
    Button btGsonJavatojsonobject;
    @Bind(R.id.bt_gson_javatojsonarray)
    Button btGsonJavatojsonarray;
    @Bind(R.id.tv_gson_orignal)
    TextView tvGsonOrignal;
    @Bind(R.id.tv_gson_last)
    TextView tvGsonLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_gson);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_gson_tojavaobject, R.id.bt_gson_tojavalist, R.id.bt_gson_javatojsonobject, R.id.bt_gson_javatojsonarray})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_gson_tojavaobject:
                jsonToJavaObjectByGson();
                break;
            case R.id.bt_gson_tojavalist:
                jsonToJavaListByGson();
                break;
            case R.id.bt_gson_javatojsonobject:
                javaToJsonObjectByGson();
                break;
            case R.id.bt_gson_javatojsonarray:
                javaToJsonArrayByGson();
                break;
        }
    }

    // （4）将Java对象的List转换为json字符串[]
    private void javaToJsonArrayByGson() {

        // 1 获取或创建Java对象
        List<ShopInfo> shops = new ArrayList<ShopInfo>();
        ShopInfo baoyu = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");
        ShopInfo longxia = new ShopInfo(2, "龙虾", 251.0, "longxia");

        shops.add(baoyu);
        shops.add(longxia);

        // 2 生成JSON数据
        Gson gson = new Gson();
        String json = gson.toJson(shops);

        // 3 展示数据
        tvGsonOrignal.setText(shops.toString());
        tvGsonLast.setText(json);
    }

    // （3）将Java对象转换为json字符串{}
    private void javaToJsonObjectByGson() {

        // 1 获取或创建Java对象
        ShopInfo shopInfo = new ShopInfo(1,"鲍鱼",250.0,"baoyu");

        // 2 生成JSON数据
        Gson gson = new Gson();

        String json = gson.toJson(shopInfo);

        // 3 展示数据
        tvGsonOrignal.setText(shopInfo.toString());
        tvGsonLast.setText(json);
    }

    // （2）将json格式的字符串[]转换为Java对象的List
    private void jsonToJavaListByGson() {

        // 1 获取或创建JSON数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";

        // 2 解析JSON数据
        Gson gson = new Gson();

        List<ShopInfo> shops = gson.fromJson(json, new TypeToken<List<ShopInfo>>() {
        }.getType());

        // 3 展示数据
        tvGsonOrignal.setText(json);
        tvGsonLast.setText(shops.toString());
    }

    // (1）将json格式的字符串{}转换为Java对象
    private void jsonToJavaObjectByGson() {

        // 1 获取或创建JSON数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}\n";

        // 2 解析JSON数据
        Gson gson = new Gson();

        ShopInfo shopInfo = gson.fromJson(json, ShopInfo.class);

        // 3 展示数据
        tvGsonOrignal.setText(json);
        tvGsonLast.setText(shopInfo.toString());

    }
}
