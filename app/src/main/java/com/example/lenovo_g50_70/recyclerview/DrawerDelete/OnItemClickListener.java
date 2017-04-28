package com.example.lenovo_g50_70.recyclerview.DrawerDelete;

import android.view.View;

/**
 * Created by lenovo-G50-70 on 2017/4/19.
 */

public interface OnItemClickListener {
    /**
     * item 点击回调
     * @param view
     * @param position
     */
    void onItemClick(View view,int position);

    /**
     * 删除按钮回调
     * @param position
     */
    void onDeleteClick(int position);
}
