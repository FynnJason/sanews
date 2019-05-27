package com.fynnjason.sanews.utils;

import android.util.Log;

import com.fynnjason.sanews.BuildConfig;


/**
 * 日志输出工具类，只输出debug
 * 开发者：FynnJason
 */
public class LogUtils {

    public static void d(String msg) {
        if (BuildConfig.DEBUG) Log.d("MyLog", msg);
    }

}
