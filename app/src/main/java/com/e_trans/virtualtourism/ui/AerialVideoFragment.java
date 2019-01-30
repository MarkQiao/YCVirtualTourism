package com.e_trans.virtualtourism.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.e_trans.virtualtourism.Base.BaseFragment;
import com.e_trans.virtualtourism.Bean.RefreshEvent;
import com.e_trans.virtualtourism.Bean.videoBean;
import com.e_trans.virtualtourism.MainActivity;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.ui.adapter.LawEnforcementAdapter;
import com.e_trans.virtualtourism.ui.videoplayer.VerticalVideoActivity;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.e_trans.virtualtourism.utils.statusbar.StatusBarFontHelper;
import com.e_trans.virtualtourism.view.VehicleListControl;
import com.e_trans.virtualtourism.view.refreshview.XRefreshView;
import com.e_trans.virtualtourism.widget.BaseAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.e_trans.virtualtourism.Base.Url.URLPublic;
import static com.e_trans.virtualtourism.utils.Utis.getTitlelis;

/**
 * markqiao
 */
public class AerialVideoFragment extends BaseFragment {

    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.xrefreshview)
    XRefreshView xrefreshview;
    @BindView(R.id.data_null)
    FrameLayout data_null;
    @BindView(R.id.net_error)
    FrameLayout net_error;
    private VehicleListControl attr;
    private int mLoadCount = 0;//加载更多的页数
    private LawEnforcementAdapter personInfoAdapter;
    public ArrayList<videoBean> mList = new ArrayList<>();//集合,存储请求回来的数据

    @Override
    public int getLayoutId() {
        return R.layout.fragment_law_enforcement_list;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        ButterKnife.bind(this, mRootView);
        StatusBarCompat.translucentStatusBar(getActivity(), true);
        attr = new VehicleListControl(getContext());
        attr.listAttr(xrefreshview, rvView);
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        rvView.setLayoutManager(layoutManage);
        personInfoAdapter = new LawEnforcementAdapter(R.layout.item_nearby, mList, getActivity());
//        personInfoAdapter.setHasStableIds (true);
        ((DefaultItemAnimator) rvView.getItemAnimator()).setSupportsChangeAnimations(false);
        rvView.setAdapter(personInfoAdapter);
        refresh();
        personInfoAdapter.setItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), VerticalVideoActivity.class);
                intent.putParcelableArrayListExtra("videoUrlList", mList);//序列化
                intent.putExtra("position",position) ;
                startActivity(intent);//启动intent
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
        mLoadCount = 0;
        getData(0, 1);
        xrefreshview.setPullLoadEnable(true);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        xrefreshview.startRefresh();//启动动画
        mLoadCount++;
        getData(mLoadCount, 2);
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
    }

    @Override
    protected void lazyLoad() {
    }

    public void getData(int page, int type) {

        List<videoBean> mLists = new ArrayList<>();//集合,存储请求回来的数据
        if (page < 7) {
            for (int i = 0; i < 6; i++) {
                videoBean mvideoBean = new videoBean(URLPublic + "img/" + (i + (6 * page) + 1) + ".webp", "video/" + (i + (6 * page) + 1) + ".mp4",getTitlelis[((i + (6 * page) + 1)-1)]);
                Log.e("---->", "getData: " + mvideoBean.getImgPath());
                mLists.add(mvideoBean);
            }
        } else {
            mLists = null;
        }

        if (type == 1) {
            mList.clear();//下拉刷新时,清空集合
            if (mLists.size() == 0) {
                mList.clear();//下拉刷新时,清空集合
                data_null.setVisibility(View.VISIBLE);
                net_error.setVisibility(View.GONE);
                xrefreshview.stopRefresh();
                xrefreshview.stopLoadMore();
                return;
            }
            xrefreshview.stopRefresh();
            if (mLists != null) {
                mList.clear();//下拉刷新时,清空集合
                mList.addAll(mLists);
                personInfoAdapter.notifyDataSetChanged();
            } else {
                showToast("无数据请重新检索");
                personInfoAdapter.notifyDataSetChanged();
            }
        } else {
            xrefreshview.stopLoadMore();
            if (mLists != null) {
                mList.addAll(mLists);
                Log.e("-----wang---------->", "这个是上拉加载" );
                personInfoAdapter.notifyDataSetChanged();
            } else {
                showToast("已经没有更多了");
                personInfoAdapter.notifyDataSetChanged();
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getImageData(RefreshEvent event) {
        rvView.scrollToPosition(event.getPosition());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            StatusBarCompat.translucentStatusBar(getActivity(), true);
        } else {
            StatusBarCompat.setStatusBarColor(getActivity(), 0xfffffff);
            StatusBarFontHelper.setStatusBarMode(getActivity(), true);
        }
    }
}
