package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.activity.BiaoQianActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PiaojuDetailActivity extends BaseActivity {

    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.piaoju1)
    ImageView piaoju1;
    @BindView(R.id.piaoju2)
    ImageView piaoju2;
    @BindView(R.id.piaoju3)
    ImageView piaoju3;
    @BindView(R.id.piaoju4)
    ImageView piaoju4;
    @BindView(R.id.piaoju5)
    ImageView piaoju5;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.INVISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText(getString(R.string.dy_piaojudayin));
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.piaoju_detail_lay;
    }



    @OnClick({R.id.back, R.id.piaoju1, R.id.piaoju2, R.id.piaoju3, R.id.piaoju4, R.id.piaoju5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.piaoju1:
                Intent intent1 = new Intent(this,XiaoPiaoActivity.class);
                startActivity(intent1);//小票打印

                break;
            case R.id.piaoju2:
                Intent intent2 = new Intent(this, BiaoQianActivity.class);
                intent2.putExtra("flag","2");
                startActivity(intent2);//标签打印
                break;
            case R.id.piaoju3:
                Intent intent3 = new Intent(this,CaptureActivity.class);
                intent3.putExtra("flag","2");
                startActivity(intent3);//不干胶打印
                break;
            case R.id.piaoju4:
                Intent intent4 = new Intent(this, CaptureActivity.class);
                intent4.putExtra("flag","1");
                startActivity(intent4);//二维码，条形码打印
                break;
            case R.id.piaoju5:
                Intent intent5 = new Intent(this, CaptureActivity.class);
                intent5.putExtra("flag","2");
                startActivity(intent5);//二维码，条形码打印
                break;
        }
    }
}
