package com.e_trans.virtualtourism.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.e_trans.virtualtourism.view.refreshview.XRefreshView;
import com.e_trans.virtualtourism.view.refreshview.XRefreshViewFooter;

public class VehicleListControl {

    private Context context;

    public VehicleListControl(Context context) {
        this.context = context;
    }

    /**
     * 设置列表属性
     *
     * @param xView
     * @param rView
     */
    public void listAttr(XRefreshView xView, RecyclerView rView) {
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(context));
        xView.setMoveForHorizontal(true);
        xView.setPullLoadEnable(true);
        xView.setAutoLoadMore(false);
        xView.enableReleaseToLoadMore(true);
        xView.enableRecyclerViewPullUp(true);
        xView.enablePullUpWhenLoadCompleted(true);
        xView.setCustomFooterView(new XRefreshViewFooter(context));
    }

}
