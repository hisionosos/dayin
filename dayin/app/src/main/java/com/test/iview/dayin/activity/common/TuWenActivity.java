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

public class TuWenActivity extends BaseActivity {
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

    private String[] tab_title = {"表格", "图片", "调整", "表情包", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_biaoge, R.drawable.tab_tupian, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_ercode};



    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        commonTitle.setText("图文打印");

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);





    }

    @Override
    public void initData() {
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
                    case R.id.main_tab4:

                        break;
                    case R.id.main_tab5:

                        break;
                }

            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.tuwen_lay;
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
