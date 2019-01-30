package com.e_trans.virtualtourism.ui.proview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.ui.adapter.DetailItemAdapter;
import com.e_trans.virtualtourism.ui.adapter.TourismDetailAdapter;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:图片Item
 */

public class DetailProvider extends BaseItemProvider<DataBean, BaseViewHolder> {
    @Override
    public int viewType() {
        return TourismDetailAdapter.TYPE_DETAIL;
    }

    @Override
    public int layout() {
        return R.layout.app_item_home_scroll;
    }

    @Override
    public void onClick(BaseViewHolder helper, DataBean data, int position) {
        super.onClick(helper, data, position);
    }

    @Override
    public void convert(BaseViewHolder helper, DataBean data, int position) {
        RecyclerView mRecyclerView = helper.getView(R.id.item_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        DetailItemAdapter adapter = new DetailItemAdapter();
        adapter.setNewData(data.getGoodsInfo());
        mRecyclerView.setAdapter(adapter);
    }
}
