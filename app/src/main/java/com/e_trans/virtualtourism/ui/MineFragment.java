package com.e_trans.virtualtourism.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dueeeke.videoplayer.player.VideoCacheManager;
import com.e_trans.virtualtourism.Base.BaseFragment;
import com.e_trans.virtualtourism.Base.Config;
import com.e_trans.virtualtourism.LoginActivity;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.utils.FileUtils;
import com.e_trans.virtualtourism.utils.PrefUtils;
import com.e_trans.virtualtourism.view.CustomRecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.e_trans.virtualtourism.utils.Utis.packageName;


/**
 * 我的页面
 * Created by hackest on 2018-02-01.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.ll_footmark)
    View mFootmark;
    @BindView(R.id.rv_history)
    CustomRecyclerView mRvHistory;
    @BindView(R.id.ll_my_favorite)
    View mMyFavorite;
    @BindView(R.id.ll_feedback)
    View mFeedback;
    @BindView(R.id.ll_push)
    View mPush;
    @BindView(R.id.ll_version)
    View mVersion;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_username)
    TextView mTvUser;
    @BindView(R.id.ll_clear_cache)
    View mClearCache;
    @BindView(R.id.me_login_bt)
    View me_login_bt;
    private int userNum;
    LinearLayoutManager mLayoutManager;
    int stat;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        ButterKnife.bind(this, mRootView);
        mTvVersion.setText("V" + packageName(getContext()));
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onStart() {
        stat = PrefUtils.getInt(getContext(), Config.LOGIN_DATA_BEAN_STATUS, 0);
        if (stat == 1) {
            mPush.setVisibility(View.VISIBLE);
            mTvUser.setText("张恒");
        } else {
            mPush.setVisibility(View.GONE);
            mTvUser.setText("请登录");
        }

        super.onStart();
    }

    @OnClick(R.id.me_login_bt)
    void Onlogin() {
        if (stat != 1) {
            startActivity(LoginActivity.class);
        }

    }

    @OnClick(R.id.ll_clear_cache)
    void clearCache() {

    }

    @OnClick(R.id.ll_my_favorite)
    void footMarkClick() {

    }

    @OnClick(R.id.ll_push)
    void backClick() {
        mPush.setVisibility(View.GONE);
        stat=0;
        PrefUtils.putInt(getContext(), Config.LOGIN_DATA_BEAN_STATUS, 0);
        mTvUser.setText("请登录");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
