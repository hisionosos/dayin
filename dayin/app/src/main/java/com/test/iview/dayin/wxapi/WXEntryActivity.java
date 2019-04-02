package com.test.iview.dayin.wxapi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.test.iview.dayin.entity.WXBean;
import com.test.iview.dayin.entity.WXUeerBean;
import com.test.iview.dayin.entity.WeiXinDengLuBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/21.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyApplication.mWxApi.handleIntent(getIntent(), this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == baseResp.getType()) ToastUtils.showToast("分享失败");
                else ToastUtils.showToast("登录失败");
                finish();
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) baseResp).code;
                        String country = ((SendAuth.Resp) baseResp).country;
                        OkGo.get("https://api.weixin.qq.com/sns/oauth2/access_token")
                                .tag(this)
                                .params("appid", IURL.WXAppId)
                                .params("secret", IURL.WXAppSecret)
                                .params("code", code) //{"errcode":40002,"errmsg":"invalid grant_type, hints: [ req_id: 0490th40 ]"}
                                .params("grant_type", "authorization_code")
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        final WXBean wxBean = JsonUtil.parseJsonToBean(s, WXBean.class);
                                        if (wxBean != null) {
                                            OkGo.get("https://api.weixin.qq.com/sns/userinfo")
                                                    .tag(this)
                                                    .params("access_token", wxBean.getAccess_token())
                                                    .params("openid", wxBean.getOpenid())
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onSuccess(String s, Call call, Response response) {
                                                            WXUeerBean bean = JsonUtil.parseJsonToBean(s, WXUeerBean.class);
                                                            if (bean != null) {
                                                                OkGo.post(IURL.Login_WX)
                                                                        .tag(this)
                                                                        .params("openid", wxBean.getOpenid())
                                                                        .params("unionid", bean.getUnionid())
                                                                        .params("nickname", bean.getNickname())
                                                                        .params("headimgurl", bean.getHeadimgurl())
                                                                        .execute(new StringCallback() {
                                                                            @Override
                                                                            public void onSuccess(String s, Call call, Response response) {
                                                                                WeiXinDengLuBean bean = JsonUtil.parseJsonToBean(s, WeiXinDengLuBean.class);
                                                                                if (bean != null) {
                                                                                    if (bean.getStatus() == 0) {
                                                                                        MyApplication.userToken = bean.getUsertoken();
                                                                                        SharedPreferences settings = getSharedPreferences("userToken", 0);
                                                                                        SharedPreferences.Editor editor = settings.edit();
                                                                                        editor.putString("userToken", bean.getUsertoken());
                                                                                        editor.commit();
                                                                                        LoginEvent lEvent = new LoginEvent("手机号登陆",true);
                                                                                        JAnalyticsInterface.onEvent(WXEntryActivity.this,lEvent);
                                                                                    } else {
//                                                                                        ToastUtils.showToast();
                                                                                    }
                                                                                } else {
                                                                                    ToastUtils.showToast("");
                                                                                }
                                                                                finish();
                                                                            }
                                                                        });
                                                            } else {
                                                                ToastUtils.showToast("");
                                                            }
                                                        }
                                                    });
                                        } else {
                                            ToastUtils.showToast("");
                                        }
                                    }
                                });
                        break;

                    case RETURN_MSG_TYPE_SHARE:
                        ToastUtils.showToast("微信分享成功");
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_BAN:
                        ToastUtils.showToast("-6");
                        finish();
                        break;
                }
                break;
        }
    }
}
