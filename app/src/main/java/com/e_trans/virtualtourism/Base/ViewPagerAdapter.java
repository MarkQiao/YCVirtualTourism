package com.e_trans.virtualtourism.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MarkQiao
 * @create 2019/1/19
 * @Describe
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> frags;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> frags) {
        super(fm);
        this.frags = frags;
    }

    @Override
    public int getCount() {
        return frags.size();
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }
}
