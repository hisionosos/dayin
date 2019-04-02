package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.adapter.DeviceAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.device_list)
    RecyclerView deviceList;

    private DeviceAdapter adapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.add_device);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText("设备");
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deviceList.setLayoutManager(layoutManager);

        adapter = new DeviceAdapter(R.layout.device_item,null);
        deviceList.setAdapter(adapter);

    }

    @Override
    public int initLayout() {
        return R.layout.device_lay;
    }


    @OnClick(R.id.home_add)
    public void onViewClicked() {
    }
}
