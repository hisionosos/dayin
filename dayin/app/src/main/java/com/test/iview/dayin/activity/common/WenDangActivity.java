package com.test.iview.dayin.activity.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.ResourceUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class WenDangActivity extends BaseActivity {
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
//    private String[] tab_title = {getString(R.string.dy_daoru), getString(R.string.dy_ziti),  getString(R.string.dy_chunwenben),};
    private int[] tab_imgs = { R.drawable.tab_daoru, R.drawable.tab_ziti, R.drawable.tab_chunwenben};

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText(R.string.dy_wendang);

    }

    @Override
    public void initData() {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);

        mainTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_tab1:

                        break;
                    case R.id.main_tab2:

                        break;
                    case R.id.main_tab3:

                        break;
                }

            }
        });


    }


    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }

    @Override
    public int initLayout() {
        return R.layout.wendang_lay;
    }
}