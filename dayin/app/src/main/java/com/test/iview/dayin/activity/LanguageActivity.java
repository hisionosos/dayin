package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LanguageActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.man)
    ImageView man;
    @BindView(R.id.item1)
    RelativeLayout item1;
    @BindView(R.id.item2)
    RelativeLayout item2;
    @BindView(R.id.item3)
    RelativeLayout item3;
    @BindView(R.id.item4)
    RelativeLayout item4;
    @BindView(R.id.item5)
    RelativeLayout item5;
    @BindView(R.id.item6)
    RelativeLayout item6;
    @BindView(R.id.item7)
    RelativeLayout item7;
    @BindView(R.id.choose0)
    RadioButton choose0;
    @BindView(R.id.choose1)
    RadioButton choose1;
    @BindView(R.id.choose2)
    RadioButton choose2;
    @BindView(R.id.choose3)
    RadioButton choose3;
    @BindView(R.id.choose4)
    RadioButton choose4;
    @BindView(R.id.choose5)
    RadioButton choose5;
    @BindView(R.id.choose6)
    RadioButton choose6;
    @BindView(R.id.rgSex)
    RadioGroup rgSex;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.GONE);
        commonTxt.setVisibility(View.VISIBLE);
        commonTxt.setText("保存");
        commonTitle.setText("语言选择");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.language_lay;
    }


    @OnClick({R.id.choose0, R.id.choose1, R.id.choose2, R.id.choose3, R.id.choose4, R.id.choose5, R.id.choose6, R.id.rgSex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.choose0:
                break;
            case R.id.choose1:
                break;
            case R.id.choose2:
                break;
            case R.id.choose3:
                break;
            case R.id.choose4:
                break;
            case R.id.choose5:
                break;
            case R.id.choose6:
                break;
            case R.id.rgSex:
                break;
        }
    }
}
