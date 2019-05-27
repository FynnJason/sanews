package com.fynnjason.sanews.ui;

import android.support.annotation.NonNull;

import com.fynnjason.sanews.R;
import com.fynnjason.sanews.base.BaseMvpLazyFragment;
import com.fynnjason.sanews.mvp.presenter.HomePresenter;
import com.fynnjason.sanews.mvp.view.HomeView;

public class HomeFragment extends BaseMvpLazyFragment<HomeView, HomePresenter> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

}
