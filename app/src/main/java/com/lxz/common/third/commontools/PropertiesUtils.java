package com.lxz.common.third.commontools;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 文件配置工具类
 * 须将properties文件放在assets文件夹中
 */
public class PropertiesUtils {
	/**
	 * 读取config.properties配置文件key对应的value值
	 * @param key
	 * @return
	 */
	public static String getValue(String key, Context context){
		try {
			Properties properties = new Properties();
			InputStream in = context.getAssets().open("config.properties");
			properties.load(in);
			return (String) properties.get(key);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
