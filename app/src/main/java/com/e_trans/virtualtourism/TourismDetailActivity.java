package com.e_trans.virtualtourism;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e_trans.virtualtourism.Bean.DataBean;
import com.e_trans.virtualtourism.ui.adapter.TourismDetailAdapter;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.e_trans.virtualtourism.view.BannerImageLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author MIK
 * @project com.yianke.pet.view.activity
 * @date 2019/1/21
 */
public class TourismDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_goback)
    ImageView ivTitleGoback;
    private Context context;
    @BindView(R.id.app_home_list)
    RecyclerView mRecyclerView;
    private List<DataBean> data;
    private DataBean testBean;
    private DataBean.GoodsInfoBean shopList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this, true);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        tvTitle.setText("详情");
        shopList = (DataBean.GoodsInfoBean) getIntent().getSerializableExtra("model");
        init();
    }

    private void init() {
        context = this;
        loadingDatas();
    }

    @OnClick({R.id.iv_title_goback})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_title_goback:
                finish();
                break;
            default:
                break;
        }
    }

    public void loadingDatas() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        String jsonData = new String(getAssertsFile(context, "detail.json"));
        data = new Gson().fromJson(jsonData, new TypeToken<List<DataBean>>() {
        }.getType());

        testBean = data.get(0);
        testBean.setAddress(shopList.getAddress());
        testBean.setCommon(shopList.getCommon());
        testBean.setGoodsName(shopList.getGoodname());
        testBean.setGoodsPrice(shopList.getGoodsPrice());
        testBean.setDate(shopList.getDate());
        data.set(0, testBean);
        DataBean testBean1 = data.get(1);
        testBean1.setType(9);
        testBean1.setGoodsDesc(shopList.getGoodsDesc());
        testBean1.setGoodsName(shopList.getGoodname());
        data.set(1, testBean1);

        TourismDetailAdapter adapter = new TourismDetailAdapter();
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = data.get(position).getType();
                if (type == 8 || type == 9) {
                    return 6;
                } else if (type == 10) {
                    return 6;
                }
                return data.size();
            }
        });
        mRecyclerView.setAdapter(adapter);
        adapter.setHeaderView(getHeaderView(mRecyclerView));
        adapter.setNewData(data);
        adapter.setOnItemChildClickListener(new OnItemChildClick());
    }

    private class OnItemChildClick implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            switch (view.getId()) {
                case R.id.tv_joinCart:
                    Toast.makeText(context, "加入购物车成功", Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case R.id.tv_settle:
                    DataBean testBean = data.get(position);
                 /*   startActivity(new Intent(context, SettlementActivity.class)
                            .putExtra("model", (Serializable) testBean));*/
                    break;
            }
        }
    }

    private View getHeaderView(RecyclerView v) {
        List<String> bannerImg = new ArrayList<>();
        bannerImg.add("https://imgs.qunarzz.com/p/tts3/1806/68/c589350e05dcb702.jpg_r_390x260x90_cab04caa.jpg");
        bannerImg.add("https://imgs.qunarzz.com/p/tts2/1803/4f/58665e9cf2da3a02.jpg_r_390x260x90_9864fc1e.jpg");
        bannerImg.add("https://imgs.qunarzz.com/p/tts0/1803/69/e6d4c8946a8cf002.jpg_r_390x260x90_8de257cd.jpg");
        bannerImg.add("https://imgs.qunarzz.com/p/tts7/1507/14/a7d15f3f286dd6.jpg_r_390x260x90_ab9490bd.jpg");
        bannerImg.add("https://imgs.qunarzz.com/p/tts6/1805/a6/659218aec5a58802.jpg_r_390x260x90_e90d6bb9.jpg");
        View convertView = LayoutInflater
                .from(context)
                .inflate(R.layout.app_include_detail, (ViewGroup) v.getParent(), false);
        Banner mBanner = convertView.findViewById(R.id.app_home_header_banner);
        mBanner.setImages(bannerImg)
                .setImageLoader((ImageLoaderInterface) new BannerImageLoader() )
                .setDelayTime(3000)
                .start();
        return convertView;
    }


    public static byte[] getAssertsFile(Context context, String fileName) {
        InputStream inputStream = null;
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open(fileName);
            if (inputStream == null) {
                return null;
            }

            BufferedInputStream bis = null;
            int length;
            try {
                bis = new BufferedInputStream(inputStream);
                length = bis.available();
                byte[] data = new byte[length];
                bis.read(data);

                return data;
            } catch (IOException e) {

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {

                    }
                }
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
