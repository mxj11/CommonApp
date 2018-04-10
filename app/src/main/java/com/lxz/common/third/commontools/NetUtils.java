package com.lxz.common.third.commontools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * 跟网络相关的工具类
 *
 * @author zhy
 */
public class NetUtils {
    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 获取wifi的ip
     * @param context
     * @return
     */
    public static String getWifiIP(Context context){
        //获取WifiManager
        WifiManager wifimanage=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //检查wifi是否开启
        if(!wifimanage.isWifiEnabled())  {
            wifimanage.setWifiEnabled(true);
        }
        WifiInfo wifiinfo= wifimanage.getConnectionInfo();
        String ip=intToIp(wifiinfo.getIpAddress());
        return ip;
    }

    //将获取的int转为真正的ip地址,参考的网上的，修改了下

    private static String intToIp(int ipInt)  {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取广播地址
     * @param context
     * @return
     */
    public static String getBroadcastIP(Context context){
        String ip = getWifiIP(context);
        return ip.substring(0,ip.lastIndexOf("."))+".255";
    }

}
