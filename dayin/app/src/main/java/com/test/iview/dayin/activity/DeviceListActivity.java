package com.test.iview.dayin.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.common.SuCaiKuActivity;
import com.test.iview.dayin.activity.common.TuWenActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.utils.FileUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DeviceListActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.back)
    ImageView back;

    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        commonTitle.setText(R.string.dy_shebei);

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        ListView pairedListView = (ListView) findViewById(R.id.ListView0);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);
        ArrayList arr = FileUtils.getStorageEntities(Environment.getExternalStorageDirectory() + "/dayin.txt");
        for (int j = 0; j <arr.size() ; j++) {
            mPairedDevicesArrayAdapter.add(arr.get(j).toString());
        }

    }

    @Override
    public int initLayout() {
        return R.layout.device_list_lay;
    }



    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            String info = ((TextView) v).getText().toString();//20:13:08:12:32:16
            String address = info.substring(info.length() - 17);
//			connectDevice(address);
            Intent intent = new Intent();
            intent.putExtra("address", address);
            startActivity(new Intent(DeviceListActivity.this,PrintActivity.class));
            finish();

        }
    };
}
