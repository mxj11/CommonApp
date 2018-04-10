package com.lxz.common.home.json.fastjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lxz.common.R;
import com.lxz.common.home.json.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FastjsonActivity extends AppCompatActivity {

    @Bind(R.id.bt_fastjson_tojavaobject)
    Button btFastjsonTojavaobject;
    @Bind(R.id.bt_fastjson_tojavalist)
    Button btFastjsonTojavalist;
    @Bind(R.id.bt_fastjson_javatojsonobject)
    Button btFastjsonJavatojsonobject;
    @Bind(R.id.bt_fastjson_javatojsonarray)
    Button btFastjsonJavatojsonarray;
    @Bind(R.id.tv_fastjson_orignal)
    TextView tvFastjsonOrignal;
    @Bind(R.id.tv_fastjson_last)
    TextView tvFastjsonLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastjson);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_fastjson_tojavaobject, R.id.bt_fastjson_tojavalist, R.id.bt_fastjson_javatojsonobject, R.id.bt_fastjson_javatojsonarray})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fastjson_tojavaobject:
                jsonToJavaObjectByFastJson();
                break;
            case R.id.bt_fastjson_tojavalist:
                jsonToJavaListByFastJson();
                break;
            case R.id.bt_fastjson_javatojsonobject:
                javaToJsonObjectByFastJson();
                break;
            case R.id.bt_fastjson_javatojsonarray:
                javaToJsonArrayByFastJson();
                break;
        }
    }
    // （4）将Java对象的List转换为json字符串[]
    private void javaToJsonArrayByFastJson() {

        // 1 创建一个Java集合
        List<ShopInfo> shops = new ArrayList<ShopInfo>();

        ShopInfo baoyu = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");
        ShopInfo longxia = new ShopInfo(2, "龙虾", 251.0, "longxia");

        shops.add(baoyu);
        shops.add(longxia);

        // 2 生成JSON数据
        String json = JSON.toJSONString(shops);

        // 3 显示JSON数据
        tvFastjsonOrignal.setText(shops.toString());
        tvFastjsonLast.setText(json);

    }

    // （3）将Java对象转换为json字符串{}
    private void javaToJsonObjectByFastJson() {

        // 1 创建一个Java对象
        ShopInfo shopInfo = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");

        // 2 生成JSON数据
        String json = JSON.toJSONString(shopInfo);

        // 3 显示数据
        tvFastjsonOrignal.setText(shopInfo.toString());
        tvFastjsonLast.setText(json);
    }


    // （2）将json格式的字符串[]转换为Java对象的List
    private void jsonToJavaListByFastJson() {

        // 1 获取或创建json数据
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
        List<ShopInfo> shopInfos = JSON.parseArray(json, ShopInfo.class);

        // 3 显示数据
        tvFastjsonOrignal.setText(json);
        tvFastjsonLast.setText(shopInfos.toString());
    }

    // （1）将json格式的字符串{}转换为Java对象
    private void jsonToJavaObjectByFastJson() {

        // 1 获取或创建json数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}\n";

        // 2 解析JSON数据
        ShopInfo shopInfo = JSON.parseObject(json, ShopInfo.class);

        // 3 显示数据
        tvFastjsonOrignal.setText(json);
        tvFastjsonLast.setText(shopInfo.toString());
    }
}
