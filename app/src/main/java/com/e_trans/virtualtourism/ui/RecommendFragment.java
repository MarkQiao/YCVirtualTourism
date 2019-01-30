package com.e_trans.virtualtourism.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.RoadListActivity;
import com.e_trans.virtualtourism.ui.meijing.MeiJingActivity;
import com.e_trans.virtualtourism.utils.ClickAnimationUtils;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.e_trans.virtualtourism.utils.Utis;
import com.e_trans.virtualtourism.utils.statusbar.StatusBarFontHelper;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;

import static com.e_trans.virtualtourism.Base.Url.URLPublic;


public class RecommendFragment extends Fragment {

    public static final String BUNDLE_TITLE = "title";
    private String[] imgs = {"guiyang.jpg",
            "guiyang2.jpg",
            "guiyang3.jpg",
            "guiyang4.jpg"};
    private String[] titles = {"贵州旅游", "魅力贵州", "梯田", "古村"};
    private Toast toast;

    public static RecommendFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container, false);


    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        StatusBarCompat.translucentStatusBar(getActivity(), true);
        AutoScrollViewPager pager = (AutoScrollViewPager) getView().findViewById(R.id.scroll_pager);
        final TextView title = (TextView) getView().findViewById(R.id.title);
        CirclePageIndicator indicator = (CirclePageIndicator) getView().findViewById(R.id.indicator);
        FrameLayout ll_lx = (FrameLayout) getView().findViewById(R.id.fl_lx);
        FrameLayout fl_gk = (FrameLayout) getView().findViewById(R.id.fl_gk);
        FrameLayout fl_gl = (FrameLayout) getView().findViewById(R.id.fl_gl);
        FrameLayout fl_xl = (FrameLayout) getView().findViewById(R.id.fl_xl);
        ClickAnimationUtils.ClickScaleAnim(ll_lx, 200);
        ClickAnimationUtils.ClickScaleAnim(fl_gk, 200);
        ClickAnimationUtils.ClickScaleAnim(fl_gl, 200);
        ClickAnimationUtils.ClickScaleAnim(fl_xl, 200);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                title.setText(titles[i]);
            }
        });

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(container.getContext());
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getContext()).load(URLPublic + imgs[position]).apply(new RequestOptions().placeholder(R.drawable.imgerr).error(R.drawable.imgerr)).into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        indicator.setViewPager(pager);
        indicator.setSnap(true);

        pager.setScrollFactor(5);
        pager.setOffscreenPageLimit(4);
        pager.startAutoScroll(2000);
        pager.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(AutoScrollViewPager autoScrollPager, int position) {
               /* if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(getActivity(), "You clicked page: " + (position + 1),
                        Toast.LENGTH_SHORT);
                toast.show();*/
            }
        });
    }

    @OnClick({R.id.fl_lx, R.id.fl_gk, R.id.fl_gl, R.id.fl_xl})
    public void onViewClicked(View view) {
        if (Utis.RepeatOnclick()) {
            switch (view.getId()) {
                case R.id.fl_lx:
                    //条件查询
             /*   Intent mIntent = new Intent(getContext().getApplicationContext(),
                        WebViewActivity.class);
                startActivity(mIntent);*/
                    startActivity(new Intent(getContext().getApplicationContext(),
                            MeiJingActivity.class));
                    break;
                case R.id.fl_xl:
                    //条件查询
                    startActivity(new Intent(getContext().getApplicationContext(),
                            RoadListActivity.class).putExtra("type", 1));
                    break;
                case R.id.fl_gl:
                    //条件查询
                    startActivity(new Intent(getContext().getApplicationContext(),
                            RoadListActivity.class).putExtra("type", 2));
                    break;
                case R.id.fl_gk:
                    //条件查询
                    startActivity(new Intent(getContext().getApplicationContext(),
                            RoadListActivity.class).putExtra("type", 3));
                    break;
            }
        }

    }


    @Override
    public void onDetach() {
        super.onDetach();
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
