package com.example.lenovo_g50_70.recyclerview.DrawerDelete;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lenovo_g50_70.recyclerview.R;

import java.util.ArrayList;

/**
 * Created by lenovo-G50-70 on 2017/4/19.
 */

public class DeleteAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> stringArrayList;

    /**
     * 初始化数据
     * @param context
     * @param stringArrayList
     */
    public DeleteAdapter(Context context,ArrayList<String> stringArrayList){
        this.context=context;
        this.stringArrayList=stringArrayList;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.delete_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        viewHolder.setText(R.id.item_content,stringArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringArrayList!=null ? stringArrayList.size() : 0;
    }

    /**
     * 删除Item,刷新列表
     * @param position
     */
    public void removeItem(int position){
        stringArrayList.remove(position);
        notifyItemRemoved(position);
    }
}
