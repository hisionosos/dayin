package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SexActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
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
        commonTxt.setText(getString(R.string.dy_save));
        commonTitle.setText(getString(R.string.dy_sex));
    }

    @Override
    public void initData() {
        String isman = MyApplication.mCache.getAsString("sex_user");
        if ("true".equals(isman) || null == isman){
            man.setBackgroundResource(R.drawable.choose_in);
            woman.setBackgroundResource(R.drawable.choose_out);
        }else{
            man.setBackgroundResource(R.drawable.choose_out);
            woman.setBackgroundResource(R.drawable.choose_in);
        }
    }

    @Override
    public int initLayout() {
        return R.layout.sex_lay;
    }


    private boolean isman = true;
    @OnClick({R.id.back, R.id.man, R.id.woman,R.id.common_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.man:
                man.setBackgroundResource(R.drawable.choose_in);
                woman.setBackgroundResource(R.drawable.choose_out);
                isman = true;
                break;
            case R.id.woman:
                woman.setBackgroundResource(R.drawable.choose_in);
                man.setBackgroundResource(R.drawable.choose_out);
                isman = false;
                break;
            case R.id.common_txt:
                MyApplication.mCache.put("sex_user", isman + "");
                finish();
                break;

        }
    }


}
