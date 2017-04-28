package com.example.lenovo_g50_70.recyclerview.DrawerDelete;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashSet;

/**
 * Created by lenovo-G50-70 on 2017/4/19.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> viewSparseArray;
    private final HashSet<Integer> childClickViewIds;
    private final HashSet<Integer> itemChildLongClickViewIds;

    public View convertView;
    public Object associatedObject;

    public BaseViewHolder(View itemView) {
        super(itemView);
        //初始化操作
        viewSparseArray =new SparseArray<View>();
        childClickViewIds =new HashSet<>();
        itemChildLongClickViewIds=new HashSet<>();
        convertView =itemView;
    }

    public HashSet<Integer> getItemChildLongClickViewIds(){
        return itemChildLongClickViewIds;
    }

    public HashSet<Integer> getChildClickViewIds(){
        return childClickViewIds;
    }

    public View getConvertView(){
        return convertView;
    }

    /**
     * TextView
     * @param viewId
     * @param value
     * @return
     */
    public BaseViewHolder setText(int viewId,CharSequence value){
        TextView view =getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * TextView
     * @param viewId
     * @param strId
     * @return
     */
    public BaseViewHolder setText(int viewId, @StringRes int strId){
        TextView view =getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * ImageView
     * @param viewId
     * @param imageResId
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, @DrawableRes int imageResId){
        ImageView view =getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * View
     * @param viewId
     * @param color
     * @return
     */
    public BaseViewHolder setBackgroundColor(int viewId,int color){
        View view =getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * View
     * @param viewId
     * @param backgroundRes
     * @return
     */
    public BaseViewHolder setBackgroundRes(int viewId,@DrawableRes int backgroundRes){
        View view =getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * TextView
     * @param viewId
     * @param textColor
     * @return
     */
    public BaseViewHolder setTextColor(int viewId,int textColor){
        TextView view =getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * ImageView
     * @param viewId
     * @param drawable
     * @return
     */
    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable){
        ImageView view =getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * ImageView
     * @param viewId
     * @param bitmap
     * @return
     */
    public BaseViewHolder setImageBitmap(int viewId,Bitmap bitmap){
        ImageView view =getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * View
     * @param viewId
     * @param value
     * @return
     */
    public BaseViewHolder setAlpha(int viewId,float value){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            getView(viewId).setAlpha(value);
        }else {
            AlphaAnimation animation =new AlphaAnimation(value,value);
            animation.setDuration(0);
            animation.setFillAfter(true);
            getView(viewId).startAnimation(animation);
        }
        return this;
    }

    /**
     * View
     * @param viewId
     * @param visible
     * @return
     */
    public BaseViewHolder setVisible(int viewId,boolean visible){
        View view =getView(viewId);
        view.setVisibility(visible ? View.VISIBLE:View.GONE);
        return this;
    }

    /**
     * TextView
     * @param viewId
     * @return
     */
    public BaseViewHolder linkify(int viewId){
        TextView view =getView(viewId);
        Linkify.addLinks(view,Linkify.ALL);
        return this;
    }

    /**
     * TextView
     * @param viewId
     * @param typeface
     * @return
     */
    public BaseViewHolder setTypeface(int viewId, Typeface typeface){
        TextView view=getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * TextView
     * @param typeface
     * @param viewIds
     * @return
     */
    public BaseViewHolder setTypeface(Typeface typeface,int... viewIds){
        for(int viewId : viewIds){
            TextView view =getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * ProgressBar
     * @param viewId
     * @param progress
     * @return
     */
    public BaseViewHolder setProgress(int viewId,int progress){
        ProgressBar view =getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * ProgressBar
     * @param viewId
     * @param progress
     * @param max
     * @return
     */
    public BaseViewHolder setProgress(int viewId,int progress,int max){
        ProgressBar view =getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * ProgressBar
     * @param viewId
     * @param max
     * @return
     */
    public BaseViewHolder setMax(int viewId,int max){
        ProgressBar view =getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * RatingBar
     * @param viewId
     * @param rating
     * @return
     */
    public BaseViewHolder setRating(int viewId,float rating){
        RatingBar view =getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * RatingBar
     * @param viewId
     * @param rating
     * @param max
     * @return
     */
    public BaseViewHolder setRating(int viewId,float rating,int max){
        RatingBar view =getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * View
     * @param viewId
     * @param listener
     * @return
     */
    @Deprecated
    public BaseViewHolder setOnClickListener(int viewId,View.OnClickListener listener){
        View view =getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * View
     * @param viewId
     * @return
     */
    public BaseViewHolder addOnClickListener(int viewId){
        View view =getView(viewId);
        childClickViewIds.add(viewId);
        return this;
    }

    /**
     * 长按View事件
     * @param viewId
     * @return
     */
    public BaseViewHolder addOnLongClickListener(int viewId){
        itemChildLongClickViewIds.add(viewId);
        return this;
    }

    /**
     * 触屏View事件
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnTouchListener(int viewId,View.OnTouchListener listener){
        View view =getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 长按View监听
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnLongClickListener(int viewId,View.OnLongClickListener listener){
        View view =getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * ItemClick监听
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener){
        AdapterView view =getView(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }

    /**
     * 长按Item监听
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener){
        AdapterView view =getView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }

    /**
     * ItemSelect监听
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener){
        AdapterView view =getView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    /**
     * CheckChange监听
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener){
        CompoundButton view =getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * View
     * @param viewId
     * @param tag
     * @return
     */
    public BaseViewHolder setTag(int viewId,Object tag){
        View view =getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * View
     * @param viewId
     * @param key
     * @param tag
     * @return
     */
    public BaseViewHolder setTag(int viewId,int key,Object tag){
        View view =getView(viewId);
        view.setTag(key,tag);
        return this;
    }

    /**
     * CompoundButton || CheckedTextView
     * @param viewId
     * @param checked
     * @return
     */
    public BaseViewHolder setChecked(int viewId,boolean checked){
        View view =getView(viewId);
        if(view instanceof CompoundButton){
            ((CompoundButton)view).setChecked(checked);
        }else if(view instanceof CheckedTextView){
            ((CheckedTextView)view).setChecked(checked);
        }
        return this;
    }

    /**
     * AdapterView
     * @param viewId
     * @param adapter
     * @return
     */
    public BaseViewHolder setAdapter(int viewId,Adapter adapter){
        AdapterView view =getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * T extends View
     * @param viewId
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId){
        View view =viewSparseArray.get(viewId);
        if(view == null){
            view =convertView.findViewById(viewId);
            viewSparseArray.put(viewId,view);
        }
        return (T) view;
    }

    public Object getAssociatedObject(){
        return associatedObject;
    }

    public void setAssociatedObject(Object associatedObject){
        this.associatedObject=associatedObject;
    }
}
