package com.e_trans.virtualtourism;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.e_trans.virtualtourism.Base.BaseActivity;
import com.e_trans.virtualtourism.utils.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WelcomeActivity extends BaseActivity implements Animation.AnimationListener {

    @BindView(R.id.welcome_image_view)
    ImageView welcome_image_view;
    private static final int SUCCEED = 1; // 加载
    private Animation alphaAnimation = null;

    @SuppressLint("HandlerLeak")
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCEED:
                    Intent mIntent = new Intent(WelcomeActivity.this,
                            MainActivity.class);
                    startActivity(mIntent);
            }
            finish();
        }
    };


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mhandler.sendEmptyMessageDelayed(SUCCEED, 0);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected int layoutRes() {
        return R.layout.welcome_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.translucentStatusBar(this, true);
        // mProgressBar.setVisibility(View.GONE);
        alphaAnimation = AnimationUtils.loadAnimation(this,
                R.anim.welcome_alpha);
        alphaAnimation.setFillEnabled(true); // 启动Fill保持
        alphaAnimation.setFillAfter(true); // 设置动画的最后一帧是保持在View上面
        welcome_image_view.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(this); // 为动画设置监听
    }
}
