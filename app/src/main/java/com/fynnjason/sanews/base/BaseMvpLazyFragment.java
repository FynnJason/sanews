package com.fynnjason.sanews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpLazyFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P> {

    public View rootView;
    public Unbinder unbinder;

    private boolean mIsLoad; // 是否已经加载过数据
    private boolean mIsViewVisible; // 视图是否可见状态
    public Activity mActivity; // 父容器的Activity

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //避免视图重复创建
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        // butterknife出现的问题，在viewPager中快速左右滑动fragment，可能会造成unbinder为null
        if (unbinder == null) {
            unbinder = ButterKnife.bind(getFragment(), rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsViewVisible) {
            load();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    // 这里的方法只会当视图第一次可见时加载一次
    private void load() {
        // 有两种办法解决getActivity为null所带来的bug
        // 1是在onAttach中绑定一个activity引用，这样activity就一直有了
        // 2是根据getActivity是否为null来加载数据，因为用户快速滑动时，其实去加载数据并显示在视图上是没有必要的，我是这么觉得的，第二种配合setUserVisibleHint生命周期来处理
        if (getActivity() == null) {
            return;
        }
        if (mIsLoad) {
            return;
        }
        mIsLoad = true;
        initView();
        initListener();
        loadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsViewVisible = isVisibleToUser;
        // 只有当视图可见时，才去执行方法
        if (mIsViewVisible && rootView != null) {
            load();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() != null && mActivity == null) {
            mActivity = getActivity();
        }
    }

    /**
     * 视图id
     *
     * @return 视图id
     */
    public abstract int getLayoutId();

    /**
     * 当前Fragment实例
     *
     * @return Fragment
     */
    public Fragment getFragment() {
        return this;
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
     * 剩余逻辑代码
     */
    public void loadData() {
    }
}
