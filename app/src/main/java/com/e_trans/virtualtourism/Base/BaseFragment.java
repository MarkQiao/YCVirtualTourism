package com.e_trans.virtualtourism.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public abstract class BaseFragment extends Fragment {

    protected FragmentActivity mActivity;
    public View mRootView;

    private long showTime = 0;
    protected Toast moToastInstance;
    private static final int SHORT_TOAST = Toast.LENGTH_SHORT;
    private static final int LONG_TOAST = Toast.LENGTH_LONG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        beforeInit();
        mActivity = getActivity();
        super.onCreate(savedInstanceState);
    }

    protected void beforeInit() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initViewSaved(mRootView, savedInstanceState);
        return mRootView;
    }

    /**
     * 初始化布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 保存状态
     *
     * @param mRootView
     * @param savedInstanceState
     */
    public abstract void initViewSaved(View mRootView, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initData() {
    }

    /**
     * Toast 短时间,常用
     *
     * @param message
     */
    public void showToast(String message) {
        if (System.currentTimeMillis() - showTime > 3000) {
            if (moToastInstance == null)
                moToastInstance = Toast.makeText(getActivity(), message, SHORT_TOAST);
            else {
                moToastInstance.setDuration(SHORT_TOAST);
                moToastInstance.setText(message);
            }
            moToastInstance.show();
            showTime = System.currentTimeMillis();
        }
    }


    /**
     * Toast 长时间,不常用
     *
     * @param message
     * @param isShort
     */
    public void showToast(String message, boolean isShort) {
        if (System.currentTimeMillis() - showTime > 3000) {

            if (isShort) {
                if (moToastInstance == null)
                    moToastInstance = Toast.makeText(getActivity(), message, SHORT_TOAST);
                else {
                    moToastInstance.setDuration(SHORT_TOAST);
                    moToastInstance.setText(message);
                }
            } else {
                if (moToastInstance == null)
                    moToastInstance = Toast.makeText(getActivity(), message, LONG_TOAST);
                else {
                    moToastInstance.setDuration(LONG_TOAST);
                    moToastInstance.setText(message);
                }
            }

            moToastInstance.show();
            showTime = System.currentTimeMillis();
        }
    }

    /**
     * 启动Activity
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }
    /**
     * 判断点击崩溃
     */
    private long mLastClickTime = 0;
    public static final int TIME_INTERVAL = 1000;
    public boolean RepeatOnclick(){
        if (System.currentTimeMillis() - mLastClickTime >= TIME_INTERVAL) {
            mLastClickTime = System.currentTimeMillis();
            return true;
        }else {
            return false;
        }
    }

    protected boolean isVisible;
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible(){
        lazyLoad();
    }
    protected abstract void lazyLoad();
    protected void onInvisible(){}



}
