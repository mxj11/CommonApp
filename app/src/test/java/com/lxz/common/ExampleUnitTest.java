package com.lxz.common;

import com.lxz.common.app.MyApplication;
import com.lxz.common.fourth.generic.classes.GenericClass;
import com.lxz.common.fourth.generic.method.GenericMethod;
import com.lxz.common.fourth.xml.Dom4jParser;
import com.lxz.common.third.commontools.NetUtils;
import com.lxz.common.third.commontools.PropertiesUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    /**
     * 测试泛型方法
     */
    @Test
    public void testGenericMethod(){
        long i = new GenericMethod().getData(12);
        assertEquals(i,12);
    }

    /**
     * 测试泛型类
     */
    @Test
    public void testGenericClass(){
        String[] oldArr = {"111","222","333","444","555","666"};
        String[] newArr = new GenericClass<String>().reverse(oldArr);
        for(String item : newArr){
            System.out.println(item);
        }
    }

    /**
     * 测试使用dom4j解析xml
     */
    @Test
    public void testDom4jParser(){
       // new Dom4jParser().dom4jparser(MyApplication.getMyApplicationInstance());
//        PropertiesUtils.getValue("bind_device_success_audio_name",MyApplication.getMyApplicationInstance());
    }

    @Test
    public void testBroadcastIP(){
        String ip = NetUtils.getBroadcastIP(MyApplication.getMyApplicationInstance());
        System.out.println(ip);
    }


}