package com.lxz.common.fourth.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;
import com.lxz.common.fourth.reflect.Admin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReflectActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_object)
    AppCompatButton btnObject;
    @Bind(R.id.btn_field)
    AppCompatButton btnField;
    @Bind(R.id.btn_method)
    AppCompatButton btnMethod;
    @Bind(R.id.activity_reflect)
    LinearLayout activityReflect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        ButterKnife.bind(this);
        tvTitle.setText("反射");
    }

    @OnClick({R.id.btn_object, R.id.btn_field, R.id.btn_method})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_object:
                creatObject();
                break;
            case R.id.btn_field:
                getField();
                break;
            case R.id.btn_method:
                getMethod();
                break;
        }
    }

    /**
     * 通过反射调用对象的方法
     */
    private void getMethod() {
        try {
            //1、获取全类名
            String claName = "com.lxz.common.fourth.reflect.Admin";
            //2、获取类字节码
            Class clazz = Class.forName(claName);
            //3、创建对象
            Admin admin = (Admin) clazz.newInstance();

            //4、获取方法对象
            Method method = clazz.getDeclaredMethod("getId");

            //5、调用方法
            int id = (int) method.invoke(admin);
            Toast.makeText(ReflectActivity.this,"getId方法被调用，返回id="+id,Toast.LENGTH_LONG).show();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过反射获取属性名称、值
     */
    private void getField() {
        try {
            //1、获取全类名
            String claName = "com.lxz.common.fourth.reflect.Admin";
            //2、获取类字节码
            Class clazz = Class.forName(claName);
            //3、创建对象
            Admin admin = (Admin) clazz.newInstance();

            //4、获取所有属性
            Field[] fields = clazz.getDeclaredFields();
            StringBuilder builder = new StringBuilder();
            for(Field field : fields){
                // 设置强制访问
                field.setAccessible(true);
                //名称
                String name = field.getName();
                //值
                Object value = field.get(admin);
                builder.append(name+":"+value+",");
            }
            Toast.makeText(ReflectActivity.this,builder.toString(),Toast.LENGTH_LONG).show();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射创建对象
     */
    private void creatObject() {//创建Admin对象
        try {
            //1、获取全类名
            String claName = "com.lxz.common.fourth.reflect.Admin";
            //2、获取类字节码
            Class clazz = Class.forName(claName);
            //3、创建对象
            //Admin admin = (Admin) clazz.newInstance();

            //通过带参数构造器创建对象
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            Admin admin = (Admin) constructor.newInstance("lxz");

            Toast.makeText(this, admin.toString(), Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
