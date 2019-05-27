package com.fynnjason.sanews.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import butterknife.ButterKnife;

/**
 * 开发者：FynnJason
 * 类说明：activity基类
 */
public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    public ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 强制竖屏，大多数情况都是强制APP竖屏，以免横竖屏切换时生命周期造成的问题
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 设置布局
        setContentView(getLayoutId());
        // ButterKnife绑定
        ButterKnife.bind(getActivity());
        // 沉浸式
        mImmersionBar = ImmersionBar.with(getActivity());
        // 初始化其他
        initView();
        initListener();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化视图
     */
    public void initView() {
    }

    /**
     * 初始化监听事件
     */
    public void initListener() {
    }

    /**
     * 剩余的逻辑代码，都放在这里处理
     */
    public void loadData() {
    }

    /**
     * 获取上下文对象
     */
    public Activity getActivity() {
        return this;
    }

    /**
     * 获取activity布局
     */
    public abstract int getLayoutId();


    /**
     * 防止Fragment发生异常重置后，导致Activity中的Fragment重叠
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    //-----------------------隐藏软键盘的一种比较优秀方式-----------------------//
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
