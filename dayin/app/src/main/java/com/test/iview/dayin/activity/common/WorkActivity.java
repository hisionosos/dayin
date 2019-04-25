package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkActivity extends BaseActivity {

    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.work1)
    ImageView work1;
    @BindView(R.id.work2)
    ImageView work2;
    @BindView(R.id.work3)
    ImageView work3;
    @BindView(R.id.work4)
    ImageView work4;
    @BindView(R.id.work5)
    ImageView work5;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText(R.string.dy_work);

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.work_lay;
    }


    @OnClick({R.id.back,R.id.work1, R.id.work2, R.id.work3, R.id.work4, R.id.work5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.work1:
                startActivity(new Intent(this,XiaoZiActivity.class));
                break;
            case R.id.work2:
                startActivity(new Intent(this,ZhiTiaoActivity.class));
                break;
            case R.id.work3:
                startActivity(new Intent(this,HengFuActivity.class));
                break;
            case R.id.work4:
                startActivity(new Intent(this,DaiBanActivity.class));
                break;
            case R.id.work5:
                startActivity(new Intent(this,WenDangActivity.class));
                break;
        }
    }
}
