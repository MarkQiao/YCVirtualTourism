package com.e_trans.virtualtourism.APP;

import android.app.Application;
import android.content.Context;

/**
 * Created by liuzhao on 2018/1/24.
 */

public class MLApplication extends Application {

    public static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }


}
