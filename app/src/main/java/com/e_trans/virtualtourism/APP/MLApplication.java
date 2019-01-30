package com.e_trans.virtualtourism.APP;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by liuzhao on 2018/1/24.
 */

public class MLApplication extends Application {
//    private static MLApplication sInstance;
    public static Context appContext;
/*
    protected MLApplication() {
        sInstance = this;
    }
    public static MLApplication getInstance() {
        return sInstance;
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
        AutoLayoutConifg.getInstance().useDeviceSize();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getApplicationContext())
                .setDownsampleEnabled(true)   // 对图片进行自动缩放
                .setResizeAndRotateEnabledForNetwork(true)    // 对图片进行自动缩放
                .setBitmapsConfig(Bitmap.Config.RGB_565) //  //图片设置RGB_565，减小内存开销  fresco默认情况下是RGB_8888
                //other settings
                .build();
        Fresco.initialize(this, config);
    }

}
