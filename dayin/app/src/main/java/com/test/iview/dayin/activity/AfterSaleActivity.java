package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\6\12 0012.
 * 申请售后
 */

public class AfterSaleActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.aftersale_pic)
    ImageView aftersalePic;
    @BindView(R.id.aftersale_title)
    TextView aftersaleTitle;
    @BindView(R.id.aftersale_money)
    TextView aftersaleMoney;
    @BindView(R.id.aftersale_num)
    TextView aftersaleNum;
    @BindView(R.id.aftersale_huan_iv)
    ImageView aftersaleHuanIv;
    @BindView(R.id.aftersale_tui_iv)
    ImageView aftersaleTuiIv;
    @BindView(R.id.aftersale_et)
    EditText aftersaleEt;



    private int type = 0;// 0 : 退换货  1 : 退款

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        GlideImage.loaderImage(getIntent().getStringExtra("AfterSaleActivity_Pic"), aftersalePic);
        aftersaleMoney.setText(getIntent().getStringExtra("AfterSaleActivity_Money")+"元");
        aftersaleTitle.setText(getIntent().getStringExtra("AfterSaleActivity_Title"));
        aftersaleNum.setText(getIntent().getStringExtra("AfterSaleActivity_num"));
        title.setText("申请售后");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_aftersale;
    }

    private void tijiao() {

        OkGo.post(IURL.AfterSale)
                .params("order_id",getIntent().getStringExtra("AfterSaleActivity_ID"))
                .params("user_id", MyApplication.userToken)
                .params("server_type",type)
                .params("server_content",aftersaleEt.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        BaseBean baseBean = JsonUtil.parseJsonToBean(s,BaseBean.class);
                        if (baseBean!=null){
                            ToastUtils.showToast(baseBean.getMsg());
                            if (baseBean.getStatus()==0){
                                Intent intent = new Intent(AfterSaleActivity.this, AfterSaleRecordActivity.class);
                                intent.putExtra("AfterSaleRecordActivity_ID", getIntent().getStringExtra("AfterSaleActivity_ID"));
                                startActivity(intent);
                                finish();
                            }else{

                            }
                        }else{
                            ToastUtils.showToast("");
                        }

                    }
                });
    }

    @OnClick({R.id.fanhui, R.id.aftersale_huan, R.id.aftersale_tui, R.id.aftersale_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.aftersale_huan:
                if (type!=0){
                    type=0;
                    aftersaleHuanIv.setBackgroundResource(R.drawable.gou);
                    aftersaleTuiIv.setBackgroundResource(R.drawable.gou_no);
                }
                break;
            case R.id.aftersale_tui:
                if (type!=1){
                    type=1;
                    aftersaleHuanIv.setBackgroundResource(R.drawable.gou_no);
                    aftersaleTuiIv.setBackgroundResource(R.drawable.gou);
                }
                break;
            case R.id.aftersale_tijiao:
                tijiao();
                break;
        }
    }
}
