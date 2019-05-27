package com.fynnjason.sanews.db;

import com.fynnjason.sanews.greendao.SearchRecordBeanDao;

/**
 * 获取所有的Dao类
 * 开发者：FynnJason
 */
public class DaoManager {
    private static SearchRecordBeanDao sSearchRecordBeanDao; // 搜索历史

    public static SearchRecordBeanDao getSearchRecordBeanDao() {
        if (sSearchRecordBeanDao == null) {
            sSearchRecordBeanDao = DBHelper.getInstance().getDaoSession().getSearchRecordBeanDao();
        }
        return sSearchRecordBeanDao;
    }

}
