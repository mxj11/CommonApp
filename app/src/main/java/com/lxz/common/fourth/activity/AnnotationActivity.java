package com.lxz.common.fourth.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;
import com.lxz.common.fourth.annotation.Author;
import com.lxz.common.fourth.annotation.Author1;
import com.lxz.common.fourth.annotation.Author2;
import com.lxz.common.fourth.annotation.Author3;

import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnnotationActivity extends AppCompatActivity {


    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        ButterKnife.bind(this);
        tvTitle.setText("注解");
    }

    @OnClick({R.id.btn_base, R.id.btn_default_value, R.id.btn_default_name, R.id.btn_meta_annotation,R.id.btn_annotation_reflect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_base:
                baseCustomAnnotation();
                break;
            case R.id.btn_default_value:
                defaultValueAnnotation();
                break;
            case R.id.btn_default_name:
                defaultNameAnnotation();
                break;
            case R.id.btn_meta_annotation:
                metaAnnotation();
                break;
            case R.id.btn_annotation_reflect:
                annotationReflect();
                break;
        }
    }

    /**
     * 注解反射
     */
    @Author3(remark = "保存信息！",name = "lxz",age = 28)
    private void annotationReflect() {//获取注解信息并封装到对象中
        try {
            //获取AnnotationActivity字节码
            Class clazz = AnnotationActivity.class;
//            Method method = clazz.getDeclaredMethod("annotationReflect");
            Method method = clazz.getMethod("annotationReflect");
            //获取方法上的注解
            Author3 author3 = method.getAnnotation(Author3.class);
//            Toast.makeText(this, author3.remark()+author3.name()+author3.age(), Toast.LENGTH_SHORT).show();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    //元注解，表示注解的注解！
    @Author2(time = "2017-5-6",value = {"元注解","自定义注解"})
    private void metaAnnotation() {

    }
    @Author1("lxz")
    private void defaultNameAnnotation() {

    }
    @Author2(time = "2017-12-22",value = {"haha","xixi"})
    private void defaultValueAnnotation() {

    }
    @Author(name = "lxz",time = "2017-12-22")
    private void baseCustomAnnotation() {

    }
}
