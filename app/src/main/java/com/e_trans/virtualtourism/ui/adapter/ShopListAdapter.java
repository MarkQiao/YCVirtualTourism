package com.e_trans.virtualtourism.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.view.CacheImageView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MarkQiao
 * @create 2019/1/26
 * @Describe
 */
public class ShopListAdapter extends BaseAdapter {
    private List<DataBean.GoodsInfoBean> mList;
    private Context mContext;

    public ShopListAdapter(List<DataBean.GoodsInfoBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shoplist, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataBean.GoodsInfoBean goodsInfoBean = mList.get(position);
        Glide.with(mContext)
                .load(goodsInfoBean.getGoodsUrl())
                .into(holder.mItemImage);
        holder.mItemTitle.setText(goodsInfoBean.getGoodname());
        holder.tv_common.setText(goodsInfoBean.getCommon());
        holder.tv_address.setText(goodsInfoBean.getAddress());
        holder.tv_price.setText("￥" + goodsInfoBean.getGoodsPrice());
        holder.tv_count.setText(goodsInfoBean.getCount());
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.item_image)
        CacheImageView mItemImage;
        @BindView(R.id.item_title)
        TextView mItemTitle;
        @BindView(R.id.tv_common)
        TextView tv_common;
        @BindView(R.id.tv_address)
        TextView tv_address;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_count)
        TextView tv_count;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
