package com.test.iview.dayin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.iview.dayin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WanggeAdapter extends RecyclerView.Adapter<WanggeAdapter.OssViewHolder> {

    private Context context;
    private int mSelectedPosition = 0;
    private Map<String,Integer> data;
    /** 存储选中的图片 */
    private HashMap<Integer, String> chosenImages  = new HashMap();
    /** 存储选中的状态 */
    private HashMap<Integer, Boolean> checkStates  = new HashMap();

    List selectedImages = new ArrayList<>();

    public WanggeAdapter(Context context, Map<String,Integer> data){
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public OssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_oss_item_lay, parent, false);
        return new OssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OssViewHolder holder, final int position) {

        EditText txt  = holder.itemView.findViewById(R.id.edit_txt);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class OssViewHolder extends RecyclerView.ViewHolder{

        public OssViewHolder(View itemView) {
            super(itemView);
        }
    }

}