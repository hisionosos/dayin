package com.test.iview.dayin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.iview.dayin.entity.bean.DeviceBean;

import java.util.List;

public class DeviceAdapter extends BaseQuickAdapter<DeviceBean, BaseViewHolder> {

    public DeviceAdapter(int layoutResId, @Nullable List<DeviceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceBean item) {

    }
}
