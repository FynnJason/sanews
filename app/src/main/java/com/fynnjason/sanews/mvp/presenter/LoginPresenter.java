package com.fynnjason.sanews.mvp.presenter;

import com.fynnjason.sanews.callback.NetCallBack;
import com.fynnjason.sanews.mvp.model.LoginModel;
import com.fynnjason.sanews.mvp.view.LoginView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * 开发者：FynnJason
 * 类说明：登录Presenter
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {

    private LoginModel mModel;

    private LoginModel getModel() {
        if (mModel == null) {
            mModel = new LoginModel();
        }
        return mModel;
    }

    public void login(String mobile, String psw) {
        getModel().login(mobile, psw, new NetCallBack() {
            @Override
            public void success(String json) {
                ifViewAttached(LoginView::loginSuccess);
            }

            @Override
            public void fail(int code, String msg) {
                ifViewAttached(view -> view.loginError(code, msg));
            }
        });
    }

}
