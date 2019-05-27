package com.fynnjason.sanews.mvp.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fynnjason.sanews.R;
import com.fynnjason.sanews.ui.HomeFragment;
import com.fynnjason.sanews.ui.MineFragment;
import com.fynnjason.sanews.ui.MsgFragment;
import com.fynnjason.sanews.ui.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainModel {

    private Fragment mHomeFragment = new HomeFragment();
    private Fragment mNewsFragment = new NewsFragment();
    private Fragment mMsgFragment = new MsgFragment();
    private Fragment mMineFragment = new MineFragment();

    private List<Fragment> mFragmentList = new ArrayList<>();

    private List<TextView> mMenuTvList = new ArrayList<>();

    private List<ImageView> mMenuIvList = new ArrayList<>();

    public int mCurrentPosition; // 当前位置

    private List<Fragment> getFragmentList() {
        if (mFragmentList.size() == 0) {
            mFragmentList.add(mHomeFragment);
            mFragmentList.add(mNewsFragment);
            mFragmentList.add(mMsgFragment);
            mFragmentList.add(mMineFragment);
        }
        return mFragmentList;
    }

    public List<TextView> getMenuTvList() {
        return mMenuTvList;
    }

    public List<ImageView> getMenuIvList() {
        return mMenuIvList;
    }

    // 切换Fragment
    public void changeFragment(FragmentManager fragmentManager, int position) {
        Fragment positionFragment = getFragmentList().get(position);
        Fragment currentFragment = getFragmentList().get(mCurrentPosition);
        if (positionFragment.isAdded()) {
            fragmentManager.beginTransaction().show(positionFragment).hide(currentFragment).commitAllowingStateLoss();
        } else {
            fragmentManager.beginTransaction().add(R.id.layout_container, positionFragment).hide(currentFragment).commitAllowingStateLoss();
        }
    }

    // 第一次进入APP时，显示HomeFragment
    public void showFirstFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().add(R.id.layout_container, mHomeFragment).commitAllowingStateLoss();
    }

}
