package com.fynnjason.sanews.mvp.presenter;

import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fynnjason.sanews.mvp.model.MainModel;
import com.fynnjason.sanews.mvp.view.MainView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class MainPresenter extends MvpBasePresenter<MainView> {

    private MainModel mMainModel;

    public MainPresenter() {
        mMainModel = new MainModel();
    }

    // 添加TextView
    public void addMenuTextView(TextView... textViews) {
        for (TextView textView : textViews) {
            mMainModel.getMenuTvList().add(textView);
        }
    }

    // 添加ImageView
    public void addMenuImageView(ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            mMainModel.getMenuIvList().add(imageView);
        }
    }

    // 切换底部菜单
    public void changeMenu(FragmentManager fragmentManager, int position) {
        if (position != mMainModel.mCurrentPosition) {
            // 原位置的状态改变
            mMainModel.getMenuTvList().get(mMainModel.mCurrentPosition).setSelected(false);
            mMainModel.getMenuIvList().get(mMainModel.mCurrentPosition).setSelected(false);
            // 现在位置状态改变
            mMainModel.getMenuTvList().get(position).setSelected(true);
            mMainModel.getMenuIvList().get(position).setSelected(true);
            // Fragment切换
            mMainModel.changeFragment(fragmentManager, position);
            // 更新位置
            mMainModel.mCurrentPosition = position;
        }
    }

    // 显示第一次进入APP的Fragment
    public void showFirstMenu(FragmentManager fragmentManager) {
        mMainModel.showFirstFragment(fragmentManager);
        mMainModel.getMenuTvList().get(0).setSelected(true);
        mMainModel.getMenuIvList().get(0).setSelected(true);
    }
}
