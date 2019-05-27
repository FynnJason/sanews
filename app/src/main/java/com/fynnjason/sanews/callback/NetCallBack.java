package com.fynnjason.sanews.callback;

/**
 * 开发者：FynnJason
 * 类说明：mvp中网络请求成功与失败回调
 */
public interface NetCallBack {

    void success(String json);

    void fail(int code, String msg);

}
