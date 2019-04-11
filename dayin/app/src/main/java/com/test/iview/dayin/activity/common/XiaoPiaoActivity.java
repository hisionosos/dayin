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

public class XiaoPiaoActivity extends BaseActivity {
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

    private String[] tab_title = {"模板", "大小", "调整", "表情包", "粗细", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_muban, R.drawable.tab_daxiao, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_cuxi,R.drawable.tab_ercode};



    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        if (flag.equals("1")){

            commonTitle.setText("小票打印");
        }else if (flag.equals("2")){
            commonTitle.setText("标签打印");
        }else{
            commonTitle.setText("不干胶打印");
        }


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

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.xiaopiao_lay;
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
