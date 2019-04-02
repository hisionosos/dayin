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

public class MyInfosActivity extends BaseActivity {

    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.item1)
    RelativeLayout item1;
    @BindView(R.id.item2)
    RelativeLayout item2;
    @BindView(R.id.item3)
    RelativeLayout item3;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.GONE);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText("个人资料");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.my_infos_lay;
    }


    @OnClick({R.id.item1, R.id.item2, R.id.item3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item1:
                break;
            case R.id.item2:
                break;
            case R.id.item3:
                break;
        }
    }
}
