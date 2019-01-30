package com.e_trans.virtualtourism.Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by jack on 2017/6/13
 */

public abstract class BaseActivity extends AppCompatActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract int layoutRes();


    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public Context getContext() {
        return this;
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
    /**
     * 启动Activity
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

}
