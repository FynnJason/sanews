package com.fynnjason.sanews.db;

import com.fynnjason.sanews.bean.SearchRecordBean;

import java.util.List;

/**
 * 搜索Dao数据库的增删改查类
 * 开发者：FynnJason
 */
public class DBUtils {
    private DBUtils() {
    }

    private static class Holder {
        private static final DBUtils instance = new DBUtils();
    }

    public static DBUtils getInstance() {
        return Holder.instance;
    }

    // 搜索记录插入
    public void insertSearchRecord(SearchRecordBean model) {
        DaoManager.getSearchRecordBeanDao().insertOrReplace(model);
    }

    // 搜索记录删除
    public void deleteSearchRecord(SearchRecordBean model) {
        DaoManager.getSearchRecordBeanDao().delete(model);
    }

    // 搜索记录删除
    public void deleteSearchRecord(String searchRecord) {
        DaoManager.getSearchRecordBeanDao().deleteByKey(searchRecord);
    }

    // 搜索记录全部删除
    public void deleteAllSearchRecord() {
        DaoManager.getSearchRecordBeanDao().deleteAll();
    }

    // 搜索记录全部查询
    public List<SearchRecordBean> getAllSearchRecord() {
        return DaoManager.getSearchRecordBeanDao().loadAll();
    }

    // 搜索记录查询单个
    public SearchRecordBean getSearchRecord(String searchRecord) {
        return DaoManager.getSearchRecordBeanDao().load(searchRecord);
    }


}
