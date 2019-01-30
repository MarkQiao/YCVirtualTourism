package com.e_trans.virtualtourism.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author MarkQiao
 * @create 2019/1/26
 * @Describe
 */
public class CacheImageView extends android.support.v7.widget.AppCompatImageView
{

    public CacheImageView(Context context)
    {
        super(context);
    }

    public CacheImageView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CacheImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }
}

