package com.e_trans.virtualtourism.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;


import com.e_trans.virtualtourism.Bean.videoBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.utils.DensityUtil;
import com.e_trans.virtualtourism.utils.WindowUtil;
import com.e_trans.virtualtourism.widget.BaseAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;


import java.util.List;

public class LawEnforcementAdapter extends BaseAdapter {
    private Context context;
    private List<videoBean> mList;

    public LawEnforcementAdapter(int layoutResId, List<videoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.mList = data;
    }
    SimpleDraweeView mNearbyImg;
    TextView license_plate_number_tv;
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        videoBean d = (videoBean) data;
        mNearbyImg = holder.itemView.findViewById(R.id.nearby_img);
        license_plate_number_tv=holder.itemView.findViewById(R.id.tv_video_title);
        ViewGroup.LayoutParams params = mNearbyImg.getLayoutParams();
        params.width = (WindowUtil.getScreenWidth(context.getApplicationContext()) - DensityUtil.dip2px(context.getApplicationContext(), 2)) / 2;
        params.height = (params.width) * 8 / 5;
        mNearbyImg.setLayoutParams(params);
        final Uri uri = Uri.parse(d.getImgPath());

        if (isNotEqualsUriPath(mNearbyImg, d.getImgPath())) {
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
            license_plate_number_tv.setText(d.getExplain());
            mNearbyImg.setController(controller);

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
