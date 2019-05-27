package com.fynnjason.sanews.ui.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.fynnjason.sanews.R;
import com.fynnjason.sanews.base.BaseMvpActivity;
import com.fynnjason.sanews.mvp.presenter.MainPresenter;
import com.fynnjason.sanews.mvp.view.MainView;
import com.fynnjason.sanews.ui.HomeFragment;
import com.fynnjason.sanews.ui.MineFragment;
import com.fynnjason.sanews.ui.MsgFragment;
import com.fynnjason.sanews.ui.NewsFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> {

    @BindView(R.id.layout_container)
    FrameLayout layoutContainer;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.layout_home)
    LinearLayout layoutHome;
    @BindView(R.id.iv_news)
    ImageView ivNews;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.layout_news)
    LinearLayout layoutNews;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.layout_msg)
    LinearLayout layoutMsg;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.layout_mine)
    LinearLayout layoutMine;
    @BindView(R.id.layout_menu)
    LinearLayout layoutMenu;

    private FragmentManager mFragmentManager; // Fragment管理器

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        presenter.addMenuTextView(tvHome, tvNews, tvMsg, tvMine);
        presenter.addMenuImageView(ivHome, ivNews, ivMsg, ivMine);

        mFragmentManager = getSupportFragmentManager();
        presenter.showFirstMenu(mFragmentManager);
        mImmersionBar.reset().statusBarDarkFont(true).statusBarColor(R.color.white).fitsSystemWindows(true).init();
    }

    @OnClick({R.id.layout_home, R.id.layout_news, R.id.layout_msg, R.id.layout_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 首页
            case R.id.layout_home:
                presenter.changeMenu(mFragmentManager, 0);
                mImmersionBar.reset().statusBarDarkFont(true).statusBarColor(R.color.white).fitsSystemWindows(true).init();
                break;
            // 新闻(资讯)
            case R.id.layout_news:
                presenter.changeMenu(mFragmentManager, 1);
                mImmersionBar.reset().statusBarDarkFont(true).statusBarColor(R.color.white).fitsSystemWindows(true).init();
                break;
            // 消息(聊天)
            case R.id.layout_msg:
                presenter.changeMenu(mFragmentManager, 2);
                mImmersionBar.reset().statusBarDarkFont(true).statusBarColor(R.color.white).fitsSystemWindows(true).init();
                break;
            // 我的
            case R.id.layout_mine:
                presenter.changeMenu(mFragmentManager, 3);
                mImmersionBar.reset().statusBarDarkFont(true).init();
                break;
        }
    }


    private long mExitTime = 0; // 记录退出时间

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                mExitTime = System.currentTimeMillis();
                ToastUtils.showShort("再按一次退出APP");
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
