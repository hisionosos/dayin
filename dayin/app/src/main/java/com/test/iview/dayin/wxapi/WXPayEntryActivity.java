package com.test.iview.dayin.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.global.MyApplication;

/**
 * Created by Administrator on 2018/1/3.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyApplication.mWxApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        int errCode = baseReq.hashCode();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Intent intent = new Intent();
            intent.setAction("ChongZhiActivity");
            intent.putExtra("ChongZhiActivity_Code", baseResp.errCode);
            sendBroadcast(intent);
        }
        finish();
    }
}
