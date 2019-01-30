package com.e_trans.virtualtourism.Bean;

import java.util.List;

/**
 * Created by hackest on 2018/4/23.
 */

public class RefreshEvent {

    List<videoBean> mList;
    int position;
    long max_cursor;

    public RefreshEvent(List<videoBean> mList, int position, long max_cursor) {
        this.mList = mList;
        this.position = position;
        this.max_cursor = max_cursor;
    }

    public List<videoBean> getList() {
        return mList;
    }

    public int getPosition() {
        return position;
    }

    public long getMaxCursor() {
        return max_cursor;
    }
}
