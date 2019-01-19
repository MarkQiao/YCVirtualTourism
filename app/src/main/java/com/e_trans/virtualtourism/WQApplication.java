package com.e_trans.virtualtourism;

import android.app.Application;

/**
 * @author MarkQiao
 * @create 2019/1/19
 * @Describe
 */
public class WQApplication extends Application {
    private static WQApplication sInstance;
    public static WQApplication getInstance() {
        return sInstance;
    }
    protected WQApplication() {
        sInstance = this;
    }
}
