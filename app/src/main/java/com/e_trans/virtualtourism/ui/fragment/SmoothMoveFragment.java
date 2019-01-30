package com.e_trans.virtualtourism.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.e_trans.virtualtourism.Base.BaseFragment;
import com.e_trans.virtualtourism.Bean.AttractionsListBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.ui.activity.ScrollingActivity;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.e_trans.virtualtourism.utils.Utis;
import com.e_trans.virtualtourism.utils.statusbar.StatusBarFontHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.e_trans.virtualtourism.utils.Utis.getJson;

/**
 * @author MarkQiao
 * @create 2018/10/25
 * @Describe
 */
public class SmoothMoveFragment extends BaseFragment  implements
        AMap.OnMarkerClickListener {

    private MapView mMapView;
    private  TextView map_tv_car;
    private AMap mAMap;
    private Polyline mPolyline;
    private UiSettings mUiSettings;
    private MarkerOptions markerOption;
    private Marker marker;
    private String context="";
    private List<Marker> mList=new ArrayList<Marker>();
    private LatLng latlng = new LatLng(26.730456+	((int)(Math.random()*200))*0.001, 106.575631+	((int)(Math.random()*200))*0.001); //定位点,
    List<AttractionsListBean.AttractionsBean> mAttractionslistBean;
    // 西南坐标
    private LatLng southwestLatLng = new LatLng(39.674949, 115.932873);
    // 东北坐标
    private LatLng northeastLatLng = new LatLng(40.159453, 116.767834);
    @Override
    public int getLayoutId() {
        return R.layout.activity_smooth_move;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {

        mMapView = (MapView) mRootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        map_tv_car= (TextView) mRootView.findViewById(R.id.map_tv_car);
        map_tv_car.setText(context);
        init();
        mAMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(26.730456+	((int)(Math.random()*200))*0.001, 106.575631+	((int)(Math.random()*200))*0.001), 8, 0, 0)));
        mAMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        setdata();
        StatusBarCompat.translucentStatusBar(getActivity(), true);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // TODO Auto-generated method stub
        if (Utis.RepeatOnclick()) {
            for (int i = 0; i < mList.size(); i++) {
                if (marker.equals(mList.get(i))) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), ScrollingActivity.class);
                    intent.putExtra("mAttractionsBean", mAttractionslistBean.get(i));//序列化
                    startActivity(intent);//启动intent

//                    showToast(mAttractionslistBean.get(i).getTitle());
                }
            }
        }
        return false;
    }

    @Override
    protected void lazyLoad() {

    }


    public void setdata(){
       String result= getJson("json.json",getActivity());
        Gson gson = new Gson();
        mAttractionslistBean = gson.fromJson(result, AttractionsListBean.class).getDynamic();
        for(AttractionsListBean.AttractionsBean mAttractionsBean : mAttractionslistBean){
            LatLng southwestLatLng = new LatLng(Double.valueOf(mAttractionsBean.getLat()).doubleValue(), Double.valueOf(mAttractionsBean.getLng()).doubleValue());
            mList.add( mAMap.addMarker(new MarkerOptions().position(southwestLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_tt))));
        }
    }
    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        changeCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        latlng, 18, 30, 30)));
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_ren))
                .position(latlng)
                .draggable(true);
        marker = mAMap.addMarker(markerOption);
    }
    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update) {
        mAMap.moveCamera(update);
    }
    /**
     * 初始化AMap对象
     */
    private void init() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mUiSettings = mAMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(true);
//            mUiSettings.setZoomPosition(19);
            mUiSettings.setLogoBottomMargin(-50);
            mUiSettings.setScrollGesturesEnabled(true);
            mUiSettings.setRotateGesturesEnabled(true);
            addMarkersToMap();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    /**
     *  个性化定制的信息窗口视图的类
     *  如果要定制化渲染这个信息窗口，需要重载getInfoWindow(Marker)方法。
     *  如果只是需要替换信息窗口的内容，则需要重载getInfoContents(Marker)方法。
     */
    AMap.InfoWindowAdapter infoWindowAdapter = new AMap.InfoWindowAdapter(){

        // 个性化Marker的InfoWindow 视图
        // 如果这个方法返回null，则将会使用默认的信息窗口风格，内容将会调用getInfoContents(Marker)方法获取
        @Override
        public View getInfoWindow(Marker marker) {

            return getInfoWindowView(marker);
        }

        // 这个方法只有在getInfoWindow(Marker)返回null 时才会被调用
        // 定制化的view 做这个信息窗口的内容，如果返回null 将以默认内容渲染
        @Override
        public View getInfoContents(Marker marker) {

            return getInfoWindowView(marker);
        }
    };

    LinearLayout infoWindowLayout;
    TextView title;
    TextView snippet;

    /**
     * 自定义View并且绑定数据方法
     * @param marker 点击的Marker对象
     * @return  返回自定义窗口的视图
     */
    private View getInfoWindowView(Marker marker) {
        if (infoWindowLayout == null) {
            infoWindowLayout = new LinearLayout(getContext());
            infoWindowLayout.setOrientation(LinearLayout.VERTICAL);
            title = new TextView(getContext());
            snippet = new TextView(getContext());
            title.setTextColor(Color.BLACK);
            snippet.setTextColor(Color.BLACK);
            infoWindowLayout.addView(title);
            infoWindowLayout.addView(snippet);
        }

        return infoWindowLayout;
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
