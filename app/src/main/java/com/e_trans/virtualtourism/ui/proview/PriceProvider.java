package com.e_trans.virtualtourism.ui.proview;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.ui.adapter.TourismDetailAdapter;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:图片Item
 */

public class PriceProvider extends BaseItemProvider<DataBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return TourismDetailAdapter.TYPE_PRICE;
    }

    @Override
    public int layout() {
        return R.layout.app_item_price;
    }

    @Override
    public void onClick(BaseViewHolder helper, DataBean data, int position) {
        super.onClick(helper, data, position);
    }

    @Override
    public void convert(BaseViewHolder helper, DataBean data, int position) {
        helper.setText(R.id.tv_itemName, data.getGoodsName());
        helper.setText(R.id.tv_common, "评分:" + data.getCommon());
        helper.setText(R.id.tv_address, data.getAddress());
        helper.setText(R.id.tv_price, "￥" + data.getGoodsPrice());
        helper.setText(R.id.tv_date, data.getDate());
//        helper.addOnClickListener(R.id.tv_joinCart);
//        helper.addOnClickListener(R.id.tv_settle);
    }
}
