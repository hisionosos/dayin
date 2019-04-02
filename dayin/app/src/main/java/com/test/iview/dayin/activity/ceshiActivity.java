package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.test.iview.dayin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class ceshiActivity extends BaseActivity {
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_ceshi;
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                break;
            case R.id.text2:
                break;
            case R.id.text3:
                break;
        }
    }
}
