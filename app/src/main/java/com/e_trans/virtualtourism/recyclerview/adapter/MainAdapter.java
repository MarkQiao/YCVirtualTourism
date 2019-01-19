package com.e_trans.virtualtourism.recyclerview.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.bean.LevideoData;
import com.e_trans.virtualtourism.recyclerview.CygView;


/**
 * Created by jack on 2017/6/14
 */

public class MainAdapter extends CygBaseRecyclerAdapter<LevideoData, MainViewHolder> {

    public MainAdapter(Context context, OnItemClickListener<MainViewHolder> listener) {
        super(context, listener);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(CygView.inflateLayout(getContext(), R.layout.item_nearby, parent, false));
    }
}
