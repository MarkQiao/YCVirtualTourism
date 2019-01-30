package com.e_trans.virtualtourism.ui.meijing;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.e_trans.virtualtourism.Base.BaseActivity;
import com.e_trans.virtualtourism.Bean.MeiListBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.ShowImgActivity;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.e_trans.virtualtourism.utils.Utis.getJson;

/**
 * @author MarkQiao
 * @create 2019/1/23
 * @Describe
 */
public class MeiJingActivity extends BaseActivity implements MyAdapter.OnItemClickListener {

    MeiListBean mMeiListBean;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_goback)
    ImageView ivTitleGoback;
    private MyAdapter adapter;
    @Override
    protected int layoutRes() {
        return R.layout.activity_imgshow_list;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("实时图片展示");
        StatusBarCompat.translucentStatusBar(this, true);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvView.setLayoutManager(layoutManager);

        adapter = new MyAdapter(getData(), this);
        rvView.setAdapter(adapter);
        rvView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        rvView.setItemAnimator(new DefaultItemAnimator());
        rvView.setHasFixedSize(true);
        rvView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                super.onTouchEvent(rv, e);
            }
        });
        adapter.setOnItemClickListener(this);
    }

    public List<MeiListBean.MeisBean> getData() {
        String result = getJson("ss.json", this);
        Gson gson = new Gson();
        mMeiListBean = gson.fromJson(result, MeiListBean.class);
        return mMeiListBean.getDynamic();
    }

    @OnClick({R.id.iv_title_goback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_goback:
                finish();
                break;
        }
    }
    @Override
    public void onClick(RecyclerView.ViewHolder VH, int position) {
        startActivity(new Intent(this,
                ShowImgActivity.class).putExtra("imgurl", mMeiListBean.getDynamic().get(position).getPath()).putExtra("title",mMeiListBean.getDynamic().get(position).getLocation()));
    }
}