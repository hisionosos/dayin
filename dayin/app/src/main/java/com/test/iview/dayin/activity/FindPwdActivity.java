package com.test.iview.dayin.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.test.iview.dayin.utils.ResourceUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.utils.ValidateUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class FindPwdActivity extends BaseActivity implements HttpManager.HttpCallBackListener {

    private static final String TAG = "FindPwdActivity";


    @BindView(R.id.area_code)
    TextView areaCode;
    @BindView(R.id.phone_edt)
    EditText phoneEdt;
    @BindView(R.id.code_txt)
    TextView codeTxt;
    @BindView(R.id.code_edt)
    EditText codeEdt;
    @BindView(R.id.pwd_edt)
    EditText pwdEdt;
    @BindView(R.id.commit_btn)
    Button commitBtn;


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


    @OnClick({R.id.commit_btn,R.id.code_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit_btn:
                register();
                break;
            case R.id.code_txt:
                if (!isGettingCode){
                    showCode();
                }
                break;
        }
    }


    private void register() {
        String phone = phoneEdt.getText().toString();
        if (!ValidateUtils.isPhone(phone)) {
            ToastUtils.showToast("请输入正确的手机号");
            return;
        }

        String code = codeEdt.getText().toString();
        if (null == code ||code.length() < 6){
            ToastUtils.showShort("请输入正确的验证码");
            return;
        }

        String pwd = pwdEdt.getText().toString();
        if (null == pwd || pwd.length() < 6){
            ToastUtils.showShort("请设置规范的密码");
            return;
        }

        Map map = new HashMap();
        map.put("telephone",phone);
        map.put("code",code);
        map.put("pwd",pwd);

        HttpManager.postRequest(IURL.URL_LOGIN,TAG,map, LoginBean.class,this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        pwdEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Drawable checkOk = ResourceUtils.getDrawable(R.drawable.pwd_check_ok);
                Drawable checkError = ResourceUtils.getDrawable(R.drawable.pwd_check_error);
                checkOk.setBounds( 0, 0, checkOk.getMinimumWidth(),checkOk.getMinimumHeight());
                checkError.setBounds( 0, 0, checkError.getMinimumWidth(),checkError.getMinimumHeight());

                if (s.length() < 6){
                    pwdEdt.setCompoundDrawables(null,null,checkError,null);
                }else{
                    pwdEdt.setCompoundDrawables(null,null,checkOk,null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.find_pwd_lay;
    }

    @Override
    public void onBefore(BaseRequest request) {
        showLoadingDialog();
    }

    @Override
    public void onSuccess(BaseResponse baseResponse, String s, Call call, Response response) {
        switch (response.request().url().toString()){
            case IURL.URL_LOGIN_CODE:
                ToastUtils.showShort("发送验证码成功");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

}
