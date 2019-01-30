package com.e_trans.virtualtourism;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.e_trans.virtualtourism.Base.BaseActivity;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.ui.adapter.ShopListAdapter;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.e_trans.virtualtourism.utils.Utis.getJson;

/**
 * Created by liuzhao on 2018/1/24.
 */

public class RoadListActivity extends BaseActivity {

    private List<DataBean> mList = new ArrayList<>();//集合,存储请求回来的数据
    private List<DataBean.GoodsInfoBean> shopLists = new ArrayList<>();
    @BindView(R.id.list_shop)
    ListView rvView;
    private ShopListAdapter mRoadAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_goback)
    ImageView ivTitleGoback;
    @BindView(R.id.data_null)
    FrameLayout data_null;
    @BindView(R.id.net_error)
    FrameLayout net_error;
    private int type;
    @Override
    protected int layoutRes() {
        return  R.layout.activity_road_list;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.translucentStatusBar(this, true);
        type= getIntent().getIntExtra("type",5);
        getData(type);
        ivTitleGoback.setVisibility(View.VISIBLE);
        mRoadAdapter = new ShopListAdapter( shopLists, this);
        rvView.setAdapter(mRoadAdapter);
        rvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataBean.GoodsInfoBean shopList = shopLists.get(position);
                startActivity(new Intent(getContext(), TourismDetailActivity.class)
                        .putExtra("model", (Serializable) shopList));
            }
        });

    }

    @OnClick({R.id.iv_title_goback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_goback:
                finish();
                break;
        }
    }


    public void getData(int ty) {
        switch (ty){
            case 1: tvTitle.setText("经典线路");
                break;
            case 2: tvTitle.setText("旅游攻略");
                break;
            case 3: tvTitle.setText("旅游景点");
                break;
        }
        String result= getJson("goods.json",getContext());
        mList = new Gson().fromJson(result, new TypeToken<List<DataBean>>() {
        }.getType());

        if (ty == 7) {
            shopLists.clear();
        }
        for (DataBean testBean : mList) {
            if (ty == 7) {
                shopLists.addAll(testBean.getGoodsInfo());
            } else if (ty == testBean.getType()) {
                shopLists.clear();
                shopLists.addAll(testBean.getGoodsInfo());
            }
        }

}


}
