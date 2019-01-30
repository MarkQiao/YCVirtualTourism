package com.e_trans.virtualtourism;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e_trans.virtualtourism.Base.BaseActivity;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.e_trans.virtualtourism.utils.statusbar.StatusBarFontHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowImgActivity extends BaseActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_show_l)
    TextView tv_show_l;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_goback)
    ImageView ivTitleGoback;
    @Override
    protected int layoutRes() {
        return  R.layout.activity_showimg;
    }

    @Override
    protected void initView() {
        StatusBarCompat.translucentStatusBar(this, true);
        ButterKnife.bind(this);
        tvTitle.setText("实时图片展示");
        Glide.with(getContext()).load(getIntent().getStringExtra("imgurl")).apply(new RequestOptions().dontAnimate()).into(imageView);
        tv_show_l.setText(getIntent().getStringExtra("title"));
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
