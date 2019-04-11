package com.test.iview.dayin.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.iview.dayin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SuCaiAdapter extends RecyclerView.Adapter<SuCaiAdapter.OssViewHolder> {

    Context context;
    private int mSelectedPosition = 0;
    public OnOssSelectListener listener = null;
    private int[] data;

    public SuCaiAdapter(Context context,int[] data){
        this.context = context;
        this.data = data;
    }

    public void setLister(OnOssSelectListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public OssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.sucai_item_lay, parent, false);
        return new OssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OssViewHolder holder, final int position) {

        ImageView thumbnailImage  = holder.itemView.findViewById(R.id.sucai_item);

        Glide.with(context)
                .load(data[position])
                .thumbnail(0.2f)
                .into(thumbnailImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOssSelect(v,data[position]);

            }
        });




    }


    @Override
    public int getItemCount() {
        return data.length;
    }


    class OssViewHolder extends RecyclerView.ViewHolder{

        public OssViewHolder(View itemView) {
            super(itemView);
        }
    }

    /** 自定义的本地视频选择监听器 */
    public interface OnOssSelectListener{
        void onOssSelect(View view, int position);
    }

}