package com.e_trans.virtualtourism.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.view.CacheImageView;
import com.e_trans.virtualtourism.widget.BaseAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @author MarkQiao
 * @create 2019/1/25
 * @Describe
 */
public class RoadAdapter extends BaseAdapter {
    private Context context;
    private List<DataBean> mList;

    public RoadAdapter(int layoutResId, List<DataBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.mList = data;
    }

    @BindView(R.id.item_image)
    CacheImageView mItemImage;
    TextView license_plate_number_tv,tv_common,tv_count,tv_address,tv_price;

    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        DataBean d = (DataBean) data;
        mItemImage = holder.itemView.findViewById(R.id.item_image);
        license_plate_number_tv = holder.itemView.findViewById(R.id.item_title);
        tv_common = holder.itemView.findViewById(R.id.tv_common);
        tv_count  = holder.itemView.findViewById(R.id.tv_count);
        tv_address  = holder.itemView.findViewById(R.id.tv_address);
        tv_price= holder.itemView.findViewById(R.id.tv_price);

        tv_price.setText("￥"+d.getGoodsPrice());
        license_plate_number_tv.setText(d.getGoodsName());
        tv_common.setText(d.getCommon());
        tv_count.setText(d.getCount());
        tv_address.setText(d.getAddress());
        Glide.with(context).load(d.getGoodsUrl()).apply(new RequestOptions().placeholder(R.drawable.welcome).error(R.drawable.welcome)).into(mItemImage);
    }


}