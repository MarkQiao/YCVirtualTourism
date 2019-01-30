package com.e_trans.virtualtourism.ui.videoplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.e_trans.virtualtourism.APP.MLApplication;
import com.e_trans.virtualtourism.Base.BaseActivity;
import com.e_trans.virtualtourism.Base.Config;
import com.e_trans.virtualtourism.Bean.RefreshEvent;
import com.e_trans.virtualtourism.Bean.videoBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.ui.adapter.DouYinAdapter;
import com.e_trans.virtualtourism.utils.PrefUtils;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.e_trans.virtualtourism.utils.ToastUtil;
import com.e_trans.virtualtourism.view.CircleImageView;
import com.e_trans.virtualtourism.view.TextImageView;
import com.e_trans.virtualtourism.view.VerticalViewPager;
import com.e_trans.virtualtourism.widget.DouYinController;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.e_trans.virtualtourism.Base.Url.URLPublic;

/**
 * Created by hackest on 2018/3/5.
 */

public class VerticalVideoActivity extends BaseActivity {

    @BindView(R.id.verticalviewpager)
    VerticalViewPager mVerticalViewpager;
    private List<videoBean> mList = new ArrayList<>();
    private int mCurrentItem;
    private IjkVideoView mIjkVideoView;
    private DouYinAdapter mDouYinAdapter;
    private List<View> mViews = new ArrayList<>();
    private DouYinController mDouYinController;
    private TextView mTvVideoTitle;
    private CircleImageView mIvUserAvatar;
    private TextView mTvUsername;
    private TextImageView mTvLikeCount;
    private TextImageView mTvPlayCount;
    ImageView mCover;
    private int mPlayingPosition;
    private int position;
    @OnClick(R.id.iv_back)
    void back() {
        onBackPressed();
    }


    @Override
    protected int layoutRes() {
        return R.layout.activity_vertical_video;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mList=  getIntent().getParcelableArrayListExtra("videoUrlList");
        position = getIntent().getIntExtra("position", -1);
        mCurrentItem = position;
        StatusBarCompat.translucentStatusBar(this, true);
        mIjkVideoView = new IjkVideoView(this);
        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mDouYinController = new DouYinController(this);
        mIjkVideoView.setVideoController(mDouYinController);
        getImageData();

    }

    private void startPlay() {
        View view = mViews.get(mCurrentItem);
        FrameLayout frameLayout = view.findViewById(R.id.container);
        mCover = view.findViewById(R.id.cover_img);

        mDouYinController.setSelect(false);

        if (mCover != null && mCover.getDrawable() != null) {
            mDouYinController.getThumb().setImageDrawable(mCover.getDrawable());
        }

        ViewGroup parent = (ViewGroup) mIjkVideoView.getParent();

        if (parent != null) {
            parent.removeAllViews();
        }

        frameLayout.addView(mIjkVideoView);
        mIjkVideoView.setUrl(URLPublic+mList.get(mCurrentItem).getVdioPath());
        mIjkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_DEFAULT);
        mIjkVideoView.start();
        mPlayingPosition = mCurrentItem;
    }


    @Override
    protected void onPause() {
        super.onPause();
        mIjkVideoView.pause();
        if (mDouYinController != null) {
            mDouYinController.getIvPlay().setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mIjkVideoView.resume();

        if (mDouYinController != null) {
            mDouYinController.setSelect(false);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIjkVideoView.release();
    }

    int count;
    public void getImageData() {

        for (videoBean item : mList) {
            View view = LayoutInflater.from(this).inflate(R.layout.view_video_item, null);
            mCover = view.findViewById(R.id.cover_img);

            mIvUserAvatar = (CircleImageView) view.findViewById(R.id.iv_user_avatar);
            mTvUsername = (TextView) view.findViewById(R.id.tv_username);
            mTvLikeCount = (TextImageView) view.findViewById(R.id.tv_like_count);
            mTvPlayCount = (TextImageView) view.findViewById(R.id.tv_play_count);
            mTvVideoTitle = view.findViewById(R.id.tv_video_title);
            Glide.with(getContext()).load(item.getImgPath()).apply(new RequestOptions().dontAnimate()).into(mCover);
//            GlideUtils.loadImage(App.getInstance(), item.getAuthorImgUrl(), mIvUserAvatar, null);
            mTvVideoTitle.setText(item.getExplain());
            mTvUsername.setText("");
            Random rand = new Random();
            count = rand.nextInt(100) + 1;
            mTvPlayCount.setText("");
            mTvLikeCount.setText("");
            mViews.add(view);
            mTvLikeCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int STATUS = PrefUtils.getInt(getApplicationContext(), Config.LOGIN_DATA_BEAN_STATUS, 0);
                    if(STATUS==0){

                    }else{
                        mTvLikeCount.setText((count+1)+"");
                    }
                }
            });
        }

        mDouYinAdapter = new DouYinAdapter(mViews);
        mVerticalViewpager.setAdapter(mDouYinAdapter);


        if (position != -1) {
            mVerticalViewpager.setCurrentItem(position);
        }

        mVerticalViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
                mIjkVideoView.pause();
                if (mCurrentItem == mList.size() - 1) {
                    ToastUtil.showToast("加载中，请稍后");
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

                if (mPlayingPosition == mCurrentItem) return;
                if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                    mIjkVideoView.release();
                    ViewParent parent = mIjkVideoView.getParent();
                    if (parent != null && parent instanceof FrameLayout) {
                        ((FrameLayout) parent).removeView(mIjkVideoView);
                    }
                    startPlay();
                }
            }
        });
        mVerticalViewpager.post(new Runnable() {
            @Override
            public void run() {
                startPlay();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new RefreshEvent(null, mCurrentItem, 0));
    }
}