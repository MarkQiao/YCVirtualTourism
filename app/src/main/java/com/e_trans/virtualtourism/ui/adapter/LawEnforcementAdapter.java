package com.e_trans.virtualtourism.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.e_trans.virtualtourism.widget.BaseAdapter;

import java.util.List;


public class LawEnforcementAdapter extends BaseAdapter {
    private Context context;
    private List<String> mList;

    public LawEnforcementAdapter(int layoutResId, List<String> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.mList = data;
    }
    @Override
    protected void bindTheData(RecyclerView.ViewHolder holder, Object data, int position) {
        String d = (String) data;
/*        TextView case_number_tv = holder.itemView.findViewById(R.id.case_number_tv);
        TextView license_plate_number_tv = holder.itemView.findViewById(R.id.license_plate_number_tv);
        TextView case_personnel_tv = holder.itemView.findViewById(R.id.case_personnel_tv);
        TextView case_data_tv = holder.itemView.findViewById(R.id.case_data_tv);
        TextView case_type_tv = holder.itemView.findViewById(R.id.case_type_tv);*/


    }
}
