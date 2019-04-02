package com.test.iview.dayin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.lzy.okgo.request.BaseRequest;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.bean.LoginBean;
import com.test.iview.dayin.global.HttpManager;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.utils.BaseResponse;
import com.test.iview.dayin.utils.SettingUtils;
import com.test.iview.dayin.utils.SharedPreferencesUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.utils.ValidateUtils;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class LoginActivity extends BaseActivity implements HttpManager.HttpCallBackListener {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.phone_edt)
    EditText phoneEdt;
    @BindView(R.id.pwd_edt)
    EditText pwdEdt;
    @BindView(R.id.logo_rule)
    TextView logoRule;
    @BindView(R.id.forget_pwd)
    TextView forgetPwd;
    @BindView(R.id.regiter_txt)
    TextView regiterTxt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.area_code)
    TextView areaCode;
    @BindView(R.id.code_txt)
    TextView codeTxt;
    @BindView(R.id.code_edt)
    EditText codeEdt;

    private int loginMethod = 0;//0表示验证码登录，1表示密码登录
    private boolean isGettingCode = false;
    private Handler handler = new Handler();
    private int time = 60;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if (time < 0) {
                codeTxt.setText("获取验证码");
                isGettingCode = false;
            } else {
                codeTxt.setText(time + "秒");
                isGettingCode = true;
                handler.postDelayed(this, 1000);
            }
        }
    };


    @SuppressLint("ResourceAsColor")
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String hintStr = "请输入手机号码";
        SpannableString ss = new SpannableString(hintStr);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(24, true);
        phoneEdt.setHintTextColor(R.color.colorPrimary);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        phoneEdt.setHint(new SpannedString(ss));
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_login;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick({R.id.login_btn, R.id.forget_pwd, R.id.regiter_txt,R.id.code_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (loginMethod == 0){
                    loginCode();
                }else{
                    login();
                }

                break;
            case R.id.forget_pwd:
                startActivity(new Intent(LoginActivity.this, FindPwdActivity.class));
                break;
            case R.id.regiter_txt:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.code_txt:
                showCode();
                break;
        }
    }

    //验证码登录
    private void loginCode() {
        if (!ValidateUtils.isPhone(phoneEdt.getText().toString())) {
            ToastUtils.showToast("请输入正确的手机号");
            return;
        }

        String phone = phoneEdt.getText().toString().trim();
        String code = codeEdt.getText().toString().trim();

        if (null == code || code.length() <= 0) {
            ToastUtils.showShort("请输入验证码");
            return;
        }

        Map map = new HashMap();
        map.put("telephone", phone);
        map.put("code", code);
        HttpManager.postRequest(IURL.URL_LOGIN, TAG, map, LoginBean.class, this);
    }


    private void showCode() {
        if (ValidateUtils.isPhone(phoneEdt.getText().toString())) {
            String phone = phoneEdt.getText().toString().trim();
            Map map = new HashMap();
            map.put("telephone", phone);
            handler.post(runnable);
            HttpManager.postRequest(IURL.URL_LOGIN_CODE, TAG, map, String.class, this);
        } else {
            ToastUtils.showToast("请输入正确的手机号");
        }
    }


    private void login() {
        if (!ValidateUtils.isPhone(phoneEdt.getText().toString())) {
            ToastUtils.showToast("请输入正确的手机号");
            return;
        }

        String phone = phoneEdt.getText().toString().trim();
        String pwd = pwdEdt.getText().toString().trim();

        if (null == pwd || pwd.length() <= 0) {
            ToastUtils.showShort("请输入密码");
            return;
        }

        Map map = new HashMap();
        map.put("telephone", phone);
        map.put("code", pwd);
        HttpManager.postRequest(IURL.URL_LOGIN, TAG, map, LoginBean.class, this);

    }


    @Override
    public void onBefore(BaseRequest request) {
        showLoadingDialog();
    }


    @Override
    public void onSuccess(BaseResponse baseResponse, String s, Call call, Response response) {
        switch (response.request().url().toString()) {
            case IURL.URL_LOGIN_CODE:
                ToastUtils.showShort("发送验证码成功");
                break;
            case IURL.URL_LOGIN:
                LoginBean loginBean = (LoginBean) baseResponse.getContent();
                SharedPreferencesUtils.setParam("userToken", loginBean.getApiKey());
                SharedPreferencesUtils.setParam("uuid", loginBean.getUuid());
                SettingUtils.getInstance().setIsLogin(true);
                finish();
                break;
        }

    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        ToastUtils.showToast(response.message());
    }

    @Override
    public void onAfter(String s, Exception e) {
        hideLaodingDialog();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }


}
