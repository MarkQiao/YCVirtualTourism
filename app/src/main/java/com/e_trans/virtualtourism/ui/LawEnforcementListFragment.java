package com.e_trans.virtualtourism.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import com.e_trans.virtualtourism.Base.BaseFragment;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.ui.adapter.LawEnforcementAdapter;
import com.e_trans.virtualtourism.view.VehicleListControl;
import com.e_trans.virtualtourism.view.refreshview.XRefreshView;
import com.e_trans.virtualtourism.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * markqiao
 */
public class LawEnforcementListFragment extends BaseFragment  {

    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.xrefreshview)
    XRefreshView xrefreshview;
    @BindView(R.id.data_null)
    FrameLayout data_null;
    @BindView(R.id.net_error)
    FrameLayout net_error;
    private VehicleListControl attr;
    private int mLoadCount = 1;//加载更多的页数
    private LawEnforcementAdapter personInfoAdapter;
    private List<String> mList = new ArrayList<>();//集合,存储请求回来的数据
    @Override
    public int getLayoutId() {
        return R.layout.fragment_law_enforcement_list;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        ButterKnife.bind(this, mRootView);
        attr = new VehicleListControl(getContext());
        attr.listAttr(xrefreshview, rvView);
        personInfoAdapter = new LawEnforcementAdapter(R.layout.item_nearby, mList, getActivity());
        rvView.setAdapter(personInfoAdapter);
        personInfoAdapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });

        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                refresh();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                loadMore();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 下拉刷新
     */

    private void refresh() {
        xrefreshview.startRefresh();//启动动画
        getData();
        xrefreshview.setPullLoadEnable(true);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        xrefreshview.startRefresh();//启动动画
        mLoadCount++;
        getData();
    }
    @Override
    protected void onInvisible() {
        super.onInvisible();
    }

    @Override
    protected void lazyLoad() {
    }
    public void getData() {

    }

}
