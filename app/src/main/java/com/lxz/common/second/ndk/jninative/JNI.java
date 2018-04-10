package com.lxz.common.second.ndk.jninative;

import com.lxz.common.second.ndk.test.Human;
import com.lxz.common.second.ndk.test.Man;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by lxz on 2017/9/28 0028.
 */

public class JNI {
    static {
        System.loadLibrary("myNdk");
    }

    /**
     * 当native方法为非静态方法时：jobject 代表native方法所属的对象
     * @return
     */
    public native String helloFromC();

    /**
     * 当native方法为静态方法时：jclass 代表native方法所属类的class对象(JNI.classes)
     * @return
     */
    public static native String helloFromC1();

    public String key = "HelloWorld";
    /**
     * 访问属性，返回修改之后的属性内容
     * @return 修改后属性
     */
    public native String accessField();

    public static int count = 9;
    /**
     * 访问静态属性
     * @return
     */
    public native int accessStaticField();

    /**
     * 访问非静态方法方法
     */
    public native int accessMethod();
    //产生指定范围的随机数
    public int genRandomInt(int max){
        System.out.println("genRandomInt 执行了...");
        return new Random().nextInt(max);
    }

    /**
     * 访问静态方法
     */
    public native String accessStaticMethod();
    //产生UUID字符串
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
    //c访问java无参构造方法
    public native Date accessConstructor();
    //c访问java类的父类方法
    public native void accessParentMethod();

    public Human human = new Man();


    public native String chineseChar(String in);
    //java给c传递数组
    public native void giveArray(int []arr);
    //java从c获取数组
    public native int[] getArray(int len);
    public native void localRef();
    public native void createGlobalRef();
    public native String getGlobalRef();
    public native void deleteGlobalRef();
    public native void exception();
    public native void cached();
    public static native void initIds();
   /* public native String alertStringByC(String str);
    */


}
