package com.e_trans.virtualtourism;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.e_trans.virtualtourism.Base.ViewPagerAdapter;
import com.e_trans.virtualtourism.tablayout.entity.TabEntity;
import com.e_trans.virtualtourism.tablayout.listener.OnTabClickListener;
import com.e_trans.virtualtourism.tablayout.ui.TabLayout;
import com.e_trans.virtualtourism.ui.AerialVideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] mTitles = {"景点", "航拍", "线路"};//, "美文"
    private int[] mIcons = {R.drawable.ic_jd, R.drawable.ic_video, R.drawable.ic_xls};//, R.drawable.ic_mw
    List<Fragment> frags = new ArrayList<Fragment>();
    List<TabEntity> entitys = new ArrayList<TabEntity>();

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        for (int i = 0; i < mTitles.length; i++) {
            frags.add(AerialVideoFragment.newInstance(mTitles[i]));
            entitys.add(new TabEntity(mIcons[i], mTitles[i]));
        }

        tabLayout.setTabData(entitys);
        tabLayout.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void OnTabClick(View view, int position) {

                viewPager.setCurrentItem(position);
            }
        });

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), frags));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.scrollToTab(position, positionOffset);
            }
        });

    }

}
