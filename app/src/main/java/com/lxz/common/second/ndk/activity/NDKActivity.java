package com.lxz.common.second.ndk.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lxz.common.R;
import com.lxz.common.second.ndk.jninative.JNI;
import com.lxz.common.second.ndk.test.Human;
import com.lxz.common.second.ndk.test.Man;
import com.lxz.common.third.commontools.T;
import com.zhy.http.okhttp.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NDKActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_ClickNoStatic)
    Button btnClickNoStatic;
    @Bind(R.id.btn_ClickStatic)
    Button btnClickStatic;
    @Bind(R.id.btn_cToJavaField)
    Button btnCToJavaField;
    @Bind(R.id.btn_cToJavaStaticField)
    Button btnCToJavaStaticField;
    @Bind(R.id.btn_cToJavaMethod)
    Button btnCToJavaMethod;
    @Bind(R.id.btn_cToJavaStaticMethod)
    Button btnCToJavaStaticMethod;
    private JNI jni;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
        ButterKnife.bind(this);
        tvTitle.setText("NDK");
        jni = new JNI();
    }
    @OnClick({R.id.btn_ClickNoStatic, R.id.btn_ClickStatic, R.id.btn_cToJavaField, R.id.btn_cToJavaStaticField,
            R.id.btn_cToJavaMethod, R.id.btn_cToJavaStaticMethod,R.id.btn_cToJavaNonConstructMethod,
            R.id.btn_cToJavaParentMethod,R.id.btn_javaToCStr,R.id.btn_javaToCArray,R.id.btn_javayGetCArray,
            R.id.btn_release_local_ref,R.id.btn_create_global_ref,R.id.btn_get_global_ref,R.id.btn_release_global_ref,
            R.id.btn_exception,R.id.btn_cache,R.id.btn_ids})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ClickNoStatic:
                Toast.makeText(NDKActivity.this, jni.helloFromC(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ClickStatic:
                Toast.makeText(NDKActivity.this, jni.helloFromC1(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cToJavaField:
                String newStr = jni.accessField();
                Toast.makeText(NDKActivity.this, newStr, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cToJavaStaticField:
                Toast.makeText(NDKActivity.this, jni.accessStaticField() + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cToJavaMethod:
                Toast.makeText(NDKActivity.this, jni.accessMethod() + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cToJavaStaticMethod:
                Toast.makeText(NDKActivity.this, jni.accessStaticMethod() + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cToJavaNonConstructMethod:
                Toast.makeText(NDKActivity.this, jni.accessConstructor().getTime() + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cToJavaParentMethod:
                jni.accessParentMethod();
                //Toast.makeText(NDKActivity.this, jni.accessParentMethod(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_javaToCStr:
                Toast.makeText(NDKActivity.this, jni.chineseChar("刘向昭"), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_javaToCArray:
                //Toast.makeText(NDKActivity.this, jni.chineseChar("刘向昭"), Toast.LENGTH_SHORT).show();
                int[] arr = {10,66,33,11,5};
                jni.giveArray(arr);
                StringBuffer buffer = new StringBuffer();
                for(int i = 0;i<arr.length;i++){
                    buffer.append(arr[i]+",");
                }
                T.showShort(NDKActivity.this, buffer.toString());
                break;
            case R.id.btn_javayGetCArray:
                int[] arrFromC = jni.getArray(5);
                StringBuffer buffer1 = new StringBuffer();
                for(int i = 0;i<arrFromC.length;i++){
                    buffer1.append(arrFromC[i]+",");
                }
                T.showShort(NDKActivity.this, buffer1.toString());
                break;
            case R.id.btn_release_local_ref:
                jni.localRef();
                break;
            case R.id.btn_create_global_ref:
                jni.createGlobalRef();
                break;
            case R.id.btn_get_global_ref:
                T.showShort(NDKActivity.this,jni.getGlobalRef());
                break;
            case R.id.btn_release_global_ref:
                jni.deleteGlobalRef();
                break;
            case R.id.btn_exception:
                try {
                    jni.exception();
                }catch (Exception e){
                    T.showShort(NDKActivity.this,"捕获到c抛给java层的异常"+e.getMessage());
                }
                break;
            case R.id.btn_cache:
                for(int i = 0;i<100;i++){
                    jni.cached();
                }
                break;
            case R.id.btn_ids:
                jni.initIds();
                break;
        }
    }
}
