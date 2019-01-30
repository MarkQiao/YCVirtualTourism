package com.e_trans.virtualtourism.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.R;


/**
 * Created by Administrator on 2018/7/4.
 * Describe:
 */

public class DetailItemAdapter extends BaseQuickAdapter<DataBean.GoodsInfoBean, BaseViewHolder> {
    public DetailItemAdapter() {
        super(R.layout.app_item_detail_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean.GoodsInfoBean item) {
        AppCompatImageView imageView = (AppCompatImageView) helper.getView(R.id.item_img);
        helper.setText(R.id.tv_name, item.getGoodname());
        loadRoundImage(mContext, 10, item.getGoodsUrl(), imageView);
    }

    public static void loadRoundImage(final Context context, final int cornerRadius, String url, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
