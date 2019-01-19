package com.e_trans.virtualtourism.recyclerview.adapter;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.WQApplication;
import com.e_trans.virtualtourism.bean.LevideoData;
import com.e_trans.virtualtourism.utils.DensityUtil;
import com.e_trans.virtualtourism.utils.Utils;
import com.e_trans.virtualtourism.utils.WindowUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by jack on 2017/6/14
 */

public class MainViewHolder extends CygBaseRecyclerViewHolder<LevideoData> {

    @BindView(R.id.nearby_img)
    SimpleDraweeView mNearbyImg;
    @BindView(R.id.tv_video_title) TextView mTvTitle;
    @BindView(R.id.tv_play_count) TextView mTvPlayCount;
    @BindView(R.id.tv_like_count) TextView mTvLikeCount;

    public MainViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }

    @Override
    protected void onItemDataUpdated(@Nullable LevideoData data) {
        if (data != null) {
            ViewGroup.LayoutParams params = mNearbyImg.getLayoutParams();
            params.width = (WindowUtil.getScreenWidth(WQApplication.getInstance()) - DensityUtil.dip2px(WQApplication.getInstance(), 2)) / 2;
            params.height = (params.width) * 8 / 5;
            mNearbyImg.setLayoutParams(params);

            final Uri uri = Uri.parse(data.getDynamicCover());

            if (isNotEqualsUriPath(mNearbyImg, data.getDynamicCover())) {
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true)//设置为true将循环播放Gif动画
                        .setOldController(mNearbyImg.getController())
                        .setControllerListener(new BaseControllerListener<ImageInfo>() {

                            @Override
                            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                                mNearbyImg.setTag(R.id.nearby_img, uri);
                            }

                        })
                        .build();

                mNearbyImg.setController(controller);
            }
            Log.e("-----------------", "------------>"+uri);
        //            mNearbyImg.setImageURI(Uri.parse(data.getCoverImgUrl()));

            mTvTitle.setText(data.getTitle());
            mTvPlayCount.setText(Utils.formatNumber(data.getPlayCount()) + "播放");
            mTvLikeCount.setText(Utils.formatNumber(data.getLikeCount()) + "赞");


        }
    }

    /**
     * 解决fresco 闪屏
     *
     * @param mNearbyImg
     * @param imgUrl
     * @return
     */
    public boolean isNotEqualsUriPath(SimpleDraweeView mNearbyImg, String imgUrl) {
        if (TextUtils.isEmpty(imgUrl) || TextUtils.isEmpty(mNearbyImg.getTag(R.id.nearby_img) + "")) {
            return false;
        }
        return !(mNearbyImg.getTag(R.id.nearby_img) + "").equals(imgUrl);
    }

}
