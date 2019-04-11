package com.test.iview.dayin.activity.common;

import android.content.Intent;
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
import com.test.iview.dayin.utils.DimensUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiaoZiActivity extends BaseActivity {
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

    String flag = "";
    @BindView(R.id.canv)
    RelativeLayout canv;

    private String[] tab_title = {"大小", "调整", "表情包", "粗细", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_daxiao, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_cuxi, R.drawable.tab_ercode};


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText("小字打印");


        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        mainTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_tab1:
                        startActivity(new Intent(XiaoZiActivity.this,SuCaiKuActivity.class));
                        break;
                    case R.id.main_tab2:

                        break;
                    case R.id.main_tab3:

                        break;
                    case R.id.main_tab4:

                        break;
                    case R.id.main_tab5:

                        break;
                }

            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.xiaozi_lay;
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }

}
