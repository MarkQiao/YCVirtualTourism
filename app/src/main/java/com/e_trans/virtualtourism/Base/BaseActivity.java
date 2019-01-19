package com.e_trans.virtualtourism.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


/**
 * Created by jack on 2017/6/13
 */

public abstract class BaseActivity<PRESENTER extends BasePresenter> extends AppCompatActivity  {
    protected PRESENTER mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == mPresenter) {
            mPresenter = createPresenter();
        }
        setContentView(layoutRes());
//        StatusBarCompat.translucentStatusBar(this, true);
        ButterKnife.bind(this);
        initView();
    }
    protected abstract int layoutRes();
    protected PRESENTER createPresenter() {
        return null;
    }
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
