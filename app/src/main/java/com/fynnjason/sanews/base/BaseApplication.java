package com.fynnjason.sanews.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.fynnjason.sanews.db.DBHelper;
import com.fynnjason.sanews.utils.OkGoUtils;

import me.jessyan.autosize.AutoSizeConfig;

/**
 * Application基类
 * 开发者：FynnJason
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化工具库
        Utils.init(this);
        // 初始化网络框架
        OkGoUtils.init(this);
        // 初始化数据库
        DBHelper.getInstance().init(this);
        // 初始化屏幕适配
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(true)
                .setSupportSP(true);
    }

}
