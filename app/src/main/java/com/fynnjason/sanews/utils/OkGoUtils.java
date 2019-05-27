package com.fynnjason.sanews.utils;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * 基于OkGo网络框架封装初始化方法；
 * github地址：https://github.com/jeasonlzy/okhttp-OkGo
 */
public class OkGoUtils {

    /**
     * 核心初始化方法
     * @param application Application
     */
    public static void init(Application application) {
        // 创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        // 日志等级
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        // 日志log颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        // 添加日志拦截器
        builder.addInterceptor(loggingInterceptor);
        // 全局的读取超时时间
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
        // 全局的写入超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);
        // 全局的连接超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        // 初始化
        OkGo.getInstance().init(application)
                .setOkHttpClient(builder.build()) // 设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE) // 全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE) // 全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3); // 超时重连次数，默认为三次
//                .addCommonHeaders(headers) // 全局公共头
//                .addCommonParams(params); // 全局公共参数
    }

}
