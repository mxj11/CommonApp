package com.lxz.common.app;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lxz.common.MainActivity;
import com.lxz.common.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zxy.recovery.core.Recovery;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lxz on 2017/8/12 0012.
 */

public class MyApplication extends Application {
    public static List<?> images=new ArrayList<>();
    public static List<String> titles=new ArrayList<>();
    //屏幕的高
    public static int H;
    private static MyApplication mContext;

    /**
     * 获取上下文对象
     * @return
     */
    public static MyApplication getMyApplicationInstance(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //initRecover();
        initXutils3();
        initImageloader(this);
        initFresco();
        initBanner();
    }

    private void initRecover() {
        //让软件状态还原的框架
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .init(this);
    }


    private void initBanner() {
        H=getScreenH(this);
        Fresco.initialize(this);




        String[] urls = getResources().getStringArray(R.array.url4);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        titles= Arrays.asList(tips);
    }


    /**
     * 得到屏幕的高
     * @param aty
     * @return
     */
    public int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    /**
     * Fresco初始化
     */
    private void initFresco() {
        Fresco.initialize(this);
    }

    /**
     * xutils3初始化
     */
    private void initXutils3(){
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    /**
     * Imageloader初始化
     * @param context
     */
    private void initImageloader(Context context) {

        // 初始化参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)               // 线程优先级
                .denyCacheImageMultipleSizesInMemory()                  // 当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
                .discCacheFileNameGenerator(new Md5FileNameGenerator()) // 将保存的时候的URI名称用MD5
                .tasksProcessingOrder(QueueProcessingType.LIFO)         // 设置图片下载和显示的工作队列排序
                .writeDebugLogs()                                       // 打印debug log
                .build();

        // 全局初始化此配置
        ImageLoader.getInstance().init(config);
    }
}
