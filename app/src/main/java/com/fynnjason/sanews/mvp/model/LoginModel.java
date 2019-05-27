package com.fynnjason.sanews.mvp.model;

import com.fynnjason.sanews.callback.NetCallBack;

/**
 * 开发者：FynnJason
 * 类说明：登录Model
 */
public class LoginModel {
    public void login(String mobile, String psw, NetCallBack callBack) {
        // 模拟网络请求
        if (mobile.equals("123456")) {
            callBack.success("json字符串");
        } else {
            callBack.fail(0, "手机号错误！");
        }
    }
}
