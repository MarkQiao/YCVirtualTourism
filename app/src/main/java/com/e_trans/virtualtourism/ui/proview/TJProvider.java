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

public class TJProvider extends BaseItemProvider<DataBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return TourismDetailAdapter.TYPE_TJ;
    }

    @Override
    public int layout() {
        return R.layout.app_item_tj;
    }

    @Override
    public void onClick(BaseViewHolder helper, DataBean data, int position) {
        super.onClick(helper, data, position);
    }

    @Override
    public void convert(BaseViewHolder helper, DataBean data, int position) {
        helper.setText(R.id.tv_detail, data.getGoodsDesc());
    }
}
