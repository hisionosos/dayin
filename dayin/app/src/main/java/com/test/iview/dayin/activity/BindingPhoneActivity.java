package com.test.iview.dayin.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.entity.BindingBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.utils.ValidateUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class BindingPhoneActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.binding_phone)
    EditText bindingPhone;
    @BindView(R.id.binding_yanzhengma)
    EditText bindingYanzhengma;
    @BindView(R.id.binding_huoqv)
    Button bindingHuoqv;
    @BindView(R.id.binding_binding)
    TextView bindingBinding;




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("绑定手机号");
    }


    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_bindingphone;
    }

    @OnClick({R.id.fanhui, R.id.binding_huoqv, R.id.binding_binding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.binding_huoqv:
                if (isHuoQv) {
                    showYanZhengMa();
                }
                break;
            case R.id.binding_binding:
                binding();
                break;

        }
    }

    private void binding() {
        if (ValidateUtils.isPhone(bindingPhone.getText().toString())) {
            if (!bindingYanzhengma.getText().toString().equals("")) {

                OkGo.post(IURL.BinDingPhone)
                        .tag(this)
                        .params("user_id", MyApplication.userToken)
                        .params("code", bindingYanzhengma.getText().toString())
                        .params("mobile", bindingPhone.getText().toString())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                BindingBean bindingBean = JsonUtil.parseJsonToBean(s, BindingBean.class);
                                if (bindingBean != null) {
                                    if (bindingBean.getStatus() == 0) {
                                        if (bindingBean.getStep() == 1) {
                                            MyApplication.userToken = bindingBean.getUser_token();
                                            SharedPreferences settings = getSharedPreferences("userToken", 0);
                                            SharedPreferences.Editor editor = settings.edit();
                                            editor.putString("userToken", bindingBean.getUser_token());
                                            editor.commit();
                                            finish();
                                        }
                                        ToastUtils.showToast(bindingBean.getMsg());
                                    } else {
                                        ToastUtils.showToast(bindingBean.getMsg());
                                    }
                                } else {
                                    ToastUtils.showToast("");
                                }

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);

                            }
                        });
            } else {
                ToastUtils.showToast("请核对手机号或验证码");
            }
        } else {
            ToastUtils.showToast("请核对手机号或验证码");
        }
    }

    private void showYanZhengMa() {

        if (ValidateUtils.isPhone(bindingPhone.getText().toString())) {
            OkGo.get(IURL.Login_YanZhengMa)
                    .tag(this)
                    .params("mobile", bindingPhone.getText().toString())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                            if (baseBean != null) {
                                if (baseBean.getStatus() == 0) {
                                    isHuoQv = false;
                                    time = 60;
                                    handler.postDelayed(runnable, 1000);
                                }
                                ToastUtils.showToast(baseBean.getMsg());
                            } else {
                                ToastUtils.showToast("");
                            }


                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);

                        }
                    });
        } else {
            ToastUtils.showToast("请输入正确的手机号");

        }
    }

    boolean isHuoQv = true;
    Handler handler = new Handler();
    private int time;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            //UI
            if (time < 0) {
                isHuoQv = true;
                bindingHuoqv.setText("获取验证码");
            } else {
                bindingHuoqv.setText(time + "秒");
                handler.postDelayed(this, 1000);
            }
        }
    };
}
