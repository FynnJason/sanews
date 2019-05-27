package com.fynnjason.sanews.db;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.fynnjason.sanews.greendao.DaoMaster;
import com.fynnjason.sanews.greendao.DaoSession;

/**
 * GreenDao初始化类
 * GreenDao教程：https://juejin.im/post/5959b5bcf265da6c4d1bb245
 * 开发者：FynnJason
 */
public class DBHelper {

    private DBHelper() {
    }

    private static class Holder {
        private static final DBHelper instance = new DBHelper();
    }

    public static DBHelper getInstance() {
        return Holder.instance;
    }

    /**
     * 初始化GreenDao
     *
     * @param application Application
     */
    public void init(Application application) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application, "xsmr.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    private DaoSession mDaoSession;

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
