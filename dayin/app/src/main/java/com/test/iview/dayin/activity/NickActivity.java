package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.iview.dayin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NickActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.nick_edt)
    EditText nickEdt;
    @BindView(R.id.clear_nick)
    ImageView clearNick;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.GONE);
        commonTxt.setVisibility(View.VISIBLE);
        commonTxt.setText("保存");
        commonTitle.setText("设置昵称");

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.nick_lay;
    }


    @OnClick({R.id.common_txt, R.id.clear_nick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_txt:

                break;
            case R.id.clear_nick:

                break;
        }
    }
}
