package com.test.iview.dayin.activity.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuYaActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.main_tab)
    RadioGroup mainTab;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
    @BindView(R.id.main_tab2)
    RadioButton mainTab2;

    private String[] tab_title = {"表格", "图片", "调整", "表情包", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_biaoge, R.drawable.tab_tupian, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_ercode};

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText("涂鸦打印");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.tuya_lay;
    }


    @OnClick({R.id.back,R.id.main_tab1, R.id.main_tab2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
                break;
            case R.id.main_tab2:
                break;
        }
    }
}
