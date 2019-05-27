package com.fynnjason.sanews.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.fynnjason.sanews.R;
import com.fynnjason.sanews.mvp.presenter.LoginPresenter;
import com.fynnjason.sanews.mvp.view.LoginView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 开发者：FynnJason
 * 类说明：登录界面
 */
public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void loginSuccess() {
        ToastUtils.showShort("登录成功！");
    }

    @Override
    public void loginError(int code, String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick({R.id.et_mobile, R.id.et_psw, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_mobile:
                break;
            case R.id.et_psw:
                break;
            case R.id.btn_login:
                presenter.login(etMobile.getText().toString(), etPsw.getText().toString());
                break;
        }
    }
}
