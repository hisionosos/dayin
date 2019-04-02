package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.request.BaseRequest;
import com.test.iview.dayin.R;
import com.test.iview.dayin.global.HttpManager;
import com.test.iview.dayin.utils.BaseResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RegisterFinishActivity extends BaseActivity implements HttpManager.HttpCallBackListener {
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.login_btn)
    Button loginBtn;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String action = getIntent().getStringExtra("action");
        if ("login".equals(action)){
            titleTxt.setText("登录成功！");
        }else if ("register".equals(action)){
            titleTxt.setText("注册成功！");
        }else if ("change".equals(action)){
            titleTxt.setText("修改成功！");
        }else{
            titleTxt.setText("");
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.register_finish_lay;
    }


    @OnClick({R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                startActivity(new Intent(RegisterFinishActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }


    @Override
    public void onBefore(BaseRequest request) {

    }

    @Override
    public void onSuccess(BaseResponse baseResponse, String s, Call call, Response response) {

    }

    @Override
    public void onError(Call call, Response response, Exception e) {

    }

    @Override
    public void onAfter(String s, Exception e) {

    }

}
