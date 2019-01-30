package com.e_trans.virtualtourism.ui.meijing;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e_trans.virtualtourism.Bean.MeiListBean;
import com.e_trans.virtualtourism.R;
import com.e_trans.virtualtourism.utils.DensityUtil;
import com.e_trans.virtualtourism.utils.WindowUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wujingchao92@gmail.com on 2016/6/19.
 */
class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<MeiListBean.MeisBean> resIds;
    Context mContext;
    public MyAdapter(List<MeiListBean.MeisBean> resIds, Context mContext){
        this.resIds = resIds;
        this.mContext=mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new MyViewHolder(item);
    }
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd  HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//         holder.imageView.setImageResource(resIds.get(position));//TODO  ANR....
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = (WindowUtil.getScreenWidth(mContext.getApplicationContext()) - DensityUtil.dip2px(mContext.getApplicationContext(), 2)) / 2;
        params.height = (params.width) * 8 / 5;
        holder.imageView.setLayoutParams(params);
        if (isNotEqualsUriPath(holder.imageView, resIds.get(position).getPath())) {
            Glide.with(mContext)
                    .load(resIds.get(position).getPath())
                    .apply(new RequestOptions().placeholder(R.drawable.welcome).error(R.drawable.welcome))
                    .into( holder.imageView);
        }
        holder.tv_video_title.setText(resIds.get(position).getLocation());
        holder.tv_location.setText(resIds.get(position).getLocation());
        holder.tv_time.setText(stampToDate(resIds.get(position).getCreate_time())+"拍摄");
         holder.imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(mOnItemClickListener != null) {
                     mOnItemClickListener.onClick(holder,position);
                 }
             }
         });

         holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {
                 if(mOnItemLongClickListener != null) {
                     mOnItemLongClickListener.onLongClick(holder,position);
                     return true;
                 }
                 return false;
             }
         });
    }

    @Override
    public int getItemCount() {
        return resIds.size();
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }


    interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder VH, int position);
    }

    interface OnItemLongClickListener {
        void onLongClick(RecyclerView.ViewHolder VH, int position);
    }
    /**
     * 解决fresco 闪屏
     *
     * @param mNearbyImg
     * @param imgUrl
     * @return
     */
    public boolean isNotEqualsUriPath(ImageView mNearbyImg, String imgUrl) {
        if (TextUtils.isEmpty(imgUrl) || TextUtils.isEmpty(mNearbyImg.getTag(R.id.nearby_img) + "")) {
            return false;
        }
        return !(mNearbyImg.getTag(R.id.nearby_img) + "").equals(imgUrl);
    }
}
