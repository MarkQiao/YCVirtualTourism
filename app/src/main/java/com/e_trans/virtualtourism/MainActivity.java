package com.e_trans.virtualtourism;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.dueeeke.videoplayer.player.VideoViewManager;
import com.e_trans.virtualtourism.Base.BaseActivity;
import com.e_trans.virtualtourism.ui.AerialVideoFragment;
import com.e_trans.virtualtourism.ui.LawEnforcementListFragment;

import java.util.HashMap;

public class MainActivity extends BaseActivity {
    private TabHost mTabHost;
    private TabManager mTabManager;
    private LayoutInflater inflater;

    private final String TAB1 = "MAIN";
    private final String TAB2 = "VIDEO";
    private final String TAB3 = "FOLLOWING";
    private final String TAB4 = "MINE";
    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        inflater = LayoutInflater.from(this);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabHost.getTabWidget().setDividerDrawable(null); // 去除分割线

        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);


        mTabManager.addTab(mTabHost.newTabSpec(TAB1).setIndicator(createTabIndicatorView(R.layout.tab_following)), AerialVideoFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec(TAB2).setIndicator(createTabIndicatorView(R.layout.tab_main)), LawEnforcementListFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec(TAB3).setIndicator(createTabIndicatorView(R.layout.tab_video)), AerialVideoFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec(TAB4).setIndicator(createTabIndicatorView(R.layout.tab_mine)), AerialVideoFragment.class, null);
    }
    private View createTabIndicatorView(int layoutResource) {
        return inflater.inflate(layoutResource, null);
    }


    public static class TabManager implements TabHost.OnTabChangeListener {
        private final FragmentActivity mActivity;
        private final TabHost mTabHost;
        private final int mContainerId;
        private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
        TabInfo mLastTab;

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;
            private Fragment fragment;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabManager(FragmentActivity activity, TabHost tabHost,
                          int containerId) {
            mActivity = activity;
            mTabHost = tabHost;
            mContainerId = containerId;
            mTabHost.setOnTabChangedListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(mActivity));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);
            info.fragment = mActivity.getSupportFragmentManager()
                    .findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager()
                        .beginTransaction();
                ft.hide(info.fragment);
                ft.commitAllowingStateLoss();
            }

            mTabs.put(tag, info);
            mTabHost.addTab(tabSpec);
        }

        @Override
        public void onTabChanged(String tabId) {
            TabInfo newTab = mTabs.get(tabId);
            if (mLastTab != newTab) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager()
                        .beginTransaction();
                if (mLastTab != null) {
                    if (mLastTab.fragment != null) {
                        ft.hide(mLastTab.fragment);
                    }
                }
                if (newTab != null) {
                    if (newTab.fragment == null) {
                        newTab.fragment = Fragment.instantiate(mActivity,
                                newTab.clss.getName(), newTab.args);
                        ft.add(mContainerId, newTab.fragment, newTab.tag);
                    } else {
                        ft.show(newTab.fragment);
                    }
                }

                mLastTab = newTab;
                ft.commitAllowingStateLoss();
                mActivity.getSupportFragmentManager()
                        .executePendingTransactions();
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (VideoViewManager.instance().getCurrentVideoPlayer() != null) {
            VideoViewManager.instance().getCurrentVideoPlayer().pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTabHost.getCurrentTab() == 0 || mTabHost.getCurrentTab() == 2) {
            return;
        }

        if (VideoViewManager.instance().getCurrentVideoPlayer() != null) {
            VideoViewManager.instance().getCurrentVideoPlayer().resume();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoViewManager.instance().releaseVideoPlayer();
    }


    private long mExitTime;

    @Override
    public void onBackPressed() {
        if (!VideoViewManager.instance().onBackPressed()) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

    public TabHost getTabHost() {
        return mTabHost;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else {
        }
        super.onConfigurationChanged(newConfig);
    }

}
