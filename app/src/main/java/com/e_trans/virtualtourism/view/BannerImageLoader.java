package com.e_trans.virtualtourism.view;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * @author MarkQiao
 * @create 2019/1/25
 * @Describe
 */
public class BannerImageLoader  extends ImageLoader {
    @Override
    public void displayImage(Context context, Object obj, ImageView imageView) {

        com.nostra13.universalimageloader.core.ImageLoader imageLoaderInstance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoaderInstance.displayImage((String) obj, imageView);

    }
}

