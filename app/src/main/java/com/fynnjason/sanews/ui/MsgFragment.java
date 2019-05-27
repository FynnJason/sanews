package com.fynnjason.sanews.ui;

import com.fynnjason.sanews.R;
import com.fynnjason.sanews.base.BaseMvpLazyFragment;
import com.fynnjason.sanews.mvp.presenter.HomePresenter;
import com.fynnjason.sanews.mvp.view.HomeView;

public class MsgFragment  extends BaseMvpLazyFragment<HomeView, HomePresenter> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_msg;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

}
