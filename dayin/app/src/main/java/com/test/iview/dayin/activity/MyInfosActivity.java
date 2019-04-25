package com.test.iview.dayin.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfosActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;

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
    @BindView(R.id.my_shezhi)
    CircleImageView myShezhi;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.GONE);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText(R.string.dy_myinfos);
    }

    @Override
    public void initData() {
        Bitmap bitmap = MyApplication.mCache.getAsBitmap("user_head");
        if (null != bitmap){
            myShezhi.setImageBitmap(bitmap);
        }
    }

    @Override
    public int initLayout() {
        return R.layout.my_infos_lay;
    }


    @OnClick({R.id.back, R.id.item1, R.id.item2, R.id.item3,R.id.my_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.item1:
                break;
            case R.id.item2:
                startActivity(new Intent(this, NickActivity.class));
                break;
            case R.id.item3:
                startActivity(new Intent(this, SexActivity.class));
                break;
            case R.id.my_shezhi:

                break;
        }
    }



}
