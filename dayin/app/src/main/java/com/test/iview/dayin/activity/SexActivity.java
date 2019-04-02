package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SexActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.man)
    ImageView man;
    @BindView(R.id.item2)
    RelativeLayout item2;
    @BindView(R.id.woman)
    ImageView woman;
    @BindView(R.id.item3)
    RelativeLayout item3;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.GONE);
        commonTxt.setVisibility(View.VISIBLE);
        commonTxt.setText("保存");
        commonTitle.setText("性别");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.sex_lay;
    }


    @OnClick({R.id.man, R.id.woman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.man:
                break;
            case R.id.woman:
                break;
        }
    }
}
