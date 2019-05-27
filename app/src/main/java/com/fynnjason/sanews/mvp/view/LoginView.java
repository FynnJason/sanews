package com.fynnjason.sanews.mvp.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface LoginView extends MvpView {
    void loginSuccess();

    void loginError(int code, String msg);
}
