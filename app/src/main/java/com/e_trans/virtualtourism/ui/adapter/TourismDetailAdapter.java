package com.e_trans.virtualtourism.ui.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.ui.proview.DetailProvider;
import com.e_trans.virtualtourism.ui.proview.PriceProvider;
import com.e_trans.virtualtourism.ui.proview.TJProvider;


/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class TourismDetailAdapter extends MultipleItemRvAdapter<DataBean, BaseViewHolder> {
    //价格
    public static final int TYPE_PRICE = 800;
    //通栏图文
    public static final int TYPE_DETAIL = 900;
    //通栏文本
    public static final int TYPE_TJ = 1000;

    public TourismDetailAdapter() {
        super(null);
        finishInitialize();
    }

    @Override
    protected int getViewType(DataBean testBean) {
        int type = testBean.getType();
        if (type == 8) {
            return TYPE_PRICE;
        } else if (type == 10) {
            return TYPE_DETAIL;
        } else if (type == 9) {
            return TYPE_TJ;
        }
        return 0;
    }

    @Override
    public void registerItemProvider() {
        //通栏商品大图片
        mProviderDelegate.registerProvider(new PriceProvider());
        //通栏文本
        mProviderDelegate.registerProvider(new TJProvider());
        //通栏图文
        mProviderDelegate.registerProvider(new DetailProvider());
    }
}
