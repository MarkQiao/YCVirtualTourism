package com.e_trans.virtualtourism.ui.meijing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.e_trans.virtualtourism.R;


/**
 * Created by wujingchao92@gmail.com on 2016/6/19.
 */
class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView tv_video_title,tv_location,tv_time;
    public MyViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.iv);
        tv_video_title = (TextView) itemView.findViewById(R.id.tv_video_title);
        tv_location = (TextView) itemView.findViewById(R.id.tv_location);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
    }

}
