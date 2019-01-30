package com.e_trans.virtualtourism.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e_trans.virtualtourism.Bean.AttractionsListBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.utils.StatusBarCompat;

import java.io.IOException;

import static com.e_trans.virtualtourism.Base.Url.URLGZ;
import static com.e_trans.virtualtourism.Base.Url.URLYY;


public class ScrollingActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public TextView mtv_show;
    public ImageView imageView;
    private Animation fabOpen;
    private Animation fabOnClick;
    FloatingActionButton fab;
    AttractionsListBean.AttractionsBean mAttractionsBean;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        StatusBarCompat.translucentStatusBar(this, true);
        mAttractionsBean = (AttractionsListBean.AttractionsBean) getIntent().getSerializableExtra("mAttractionsBean");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mtv_show = (TextView) findViewById(R.id.tv_show);
        imageView = (ImageView) findViewById(R.id.backdrop);


        //设置收缩展开toolbar字体颜色
       setSupportActionBar(toolbar);
      /*   collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);*/
        //权限判断，如果没有权限就请求权限
//        initMediaPlayer();//初始化播放器 MediaPlayer
        collapsingToolbarLayout.setTitle(mAttractionsBean.getTitle());
        fab = (FloatingActionButton) findViewById(R.id.bt_scan_fab);
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabOnClick = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        mtv_show.setText("\u3000\u3000"+mAttractionsBean.getDescription());
        fab.setImageResource(R.drawable.button_play_animation);
        animationDrawable = (AnimationDrawable) fab.getDrawable();
        Glide.with(this)
                .load(getImgpath(mAttractionsBean.getPublishPanoId()))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.imgerr)
                        .error(R.drawable.imgerr))
                .into(imageView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                fab.startAnimation(fabOnClick);
                if (!mediaPlayer.isPlaying()) {
                    animationDrawable.start(); //开始的时候调用

                    startmusic();
                } else {
                    mediaPlayer.pause();
                    animationDrawable.stop(); //动画结束调用
                }

            }
        });

        /* http://app.quanjingke.com/uploadfile/720/12/12699.jpg*/
//        http://app.quanjingke.com/uploadfile/720audio/14/14476.mp3
//        System.out.println(m.substring(0,m.length()-3));
//       String imgpath=
    }

    private void startmusic(){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(getvdiopath(mAttractionsBean.getPublishPanoId()));//重新设置要播放的音频
            //3 准备播放
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getImgpath(String PublishPanoId) {
        return URLGZ + PublishPanoId.substring(0, PublishPanoId.length() - 3) + "/" + PublishPanoId + ".jpg";
    }

    public String getvdiopath(String PublishPanoId) {
        return URLYY + PublishPanoId.substring(0, PublishPanoId.length() - 3) + "/" + PublishPanoId + ".mp3";
    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    protected void onResume() {
        this.fab.startAnimation(this.fabOpen);
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();//停止音频的播放
        }
        mediaPlayer.release();//释放资源
        super.onDestroy();
    }


}
