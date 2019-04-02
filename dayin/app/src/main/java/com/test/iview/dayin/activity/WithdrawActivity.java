package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.entity.TiXianBean;
import com.test.iview.dayin.entity.WithdrawBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class WithdrawActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.withdraw_money)
    EditText withdrawMoney;
    @BindView(R.id.withdraw_quanbu)
    TextView withdrawQuanbu;
    @BindView(R.id.withdraw_zhifubao)
    EditText withdrawZhifubao;
    @BindView(R.id.withdraw_name)
    EditText withdrawName;
    @BindView(R.id.withdraw_phone)
    EditText withdraw_phone;
    @BindView(R.id.withdraw_yanzhnegma)
    EditText withdraw_yanzhnegma;
    @BindView(R.id.withdraw_huoqv)
    TextView withdraw_huoqv;
    @BindView(R.id.title11)
    TextView title11;


    @BindView(R.id.withdraw_tv)
    TextView withdraw_tv;
    private WithdrawBean bean;
    private boolean isHuoQv = true;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("提现");
        title11.setText("提现记录");
        title11.setBackgroundResource(R.drawable.button_eee);
        request();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_withdraw;
    }

    private void request() {

        OkGo.post(IURL.Withdraw)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, WithdrawBean.class);
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
                                showView();
                            } else {
                                ToastUtils.showToast(bean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }

                    }
                });
    }

    private void showView() {
        withdrawQuanbu.setText("全部" + bean.getMoney() + "元");
        withdraw_tv.setText(bean.getCashrule());
        withdraw_phone.setText(bean.getUtel());
    }

    Handler handler = new Handler();
    private int time;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            //UI
            if (time < 0) {
                isHuoQv = true;
                withdraw_huoqv.setText("获取验证码");
            } else {
                withdraw_huoqv.setText(time + "秒");
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }



    boolean b = true;

    @OnClick({R.id.fanhui, R.id.withdraw_quanbu, R.id.withdraw_huoqv, R.id.withdraw_tixian, R.id.title11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.withdraw_huoqv:
                if ("".equals(withdraw_phone.getText().toString())) {
                    ToastUtils.showToast("请输入正确的手机号");
                } else {
                    ToastUtils.showToast("提现金额不能低于" + bean.getCashnum() + "元");
                    b = false;

                    if (b) {
                        if (isHuoQv) {
                            isHuoQv = false;
                            OkGo.post(IURL.TiXian_YanZhengMa)
                                    .tag(this)
                                    .params("mobile", withdraw_phone.getText().toString())
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                                            if (baseBean != null) {
                                                if (baseBean.getStatus() == 0) {
                                                    if (baseBean.getStep() == 1) {
                                                        handler.postDelayed(runnable, 1000);
                                                    }
                                                } else {
                                                    isHuoQv = true;
                                                }
                                                ToastUtils.showToast(baseBean.getMsg());
                                            } else {
                                                ToastUtils.showToast("");
                                                isHuoQv = true;
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
            case R.id.title11:
                startActivity(new Intent(this, WithdrawRecordActivity.class));
                break;
            case R.id.withdraw_quanbu:
                quanbu();
                break;
            case R.id.withdraw_tixian:


                OkGo.post(IURL.Withdraw_Pay)
                        .tag(this)
                        .params("user_id", MyApplication.userToken)
                        .params("money", withdrawMoney.getText().toString())
                        .params("access_zfb", withdrawZhifubao.getText().toString())
                        .params("relname", withdrawName.getText().toString())
                        .params("mobile", withdraw_phone.getText().toString())
                        .params("code", withdraw_yanzhnegma.getText().toString())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                TiXianBean bean = JsonUtil.parseJsonToBean(s, TiXianBean.class);
                                if (bean != null) {
                                    if (bean.getStatus() == 0) {
                                        ToastUtils.showToast(bean.getMsg());
                                        finish();
                                    } else {
                                        ToastUtils.showToast(bean.getMsg());
                                    }
                                } else {
                                    ToastUtils.showToast("");
                                }

                            }
                        });

                break;

        }
    }

    private void quanbu() {
        withdrawMoney.setText(bean.getMoney());
        withdrawMoney.setSelection(bean.getMoney().length());


    }
}
