package com.test.iview.dayin.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.WeiXinBean;
import com.test.iview.dayin.entity.ZhiFuBaoBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.PayResult;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class ChongZhiActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.chongzhi_money)
    EditText chongzhiMoney;
    @BindView(R.id.chongzhi_weixin_iv)
    ImageView chongzhiWeixinIv;
    @BindView(R.id.chongzhi_zhifubao_iv)
    ImageView chongzhiZhifubaoIv;

    private int flag = 0; //0:微信   1:支付宝
    private static final int SDK_PAY_FLAG = 1001;
    private WeiXinBean weiXinBean;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("ChongZhiActivity_Code", -1) == 0) {
//                Intent intent1 = new Intent(ChongZhiActivity.this, PayActivity.class);
//                intent1.putExtra("Pay_Flag", 1);
//                startActivity(intent1);
                ToastUtils.showToast("充值成功");
                finish();
            } else if (intent.getIntExtra("ChongZhiActivity_Code", -1) == -1) {
//                Intent intent2 = new Intent(ChongZhiActivity.this, PayActivity.class);
//                intent2.putExtra("Pay_Flag", 0);
//                startActivity(intent2);
//                finish();

            } else {
                ToastUtils.showToast("取消支付");
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        registerReceiver(broadcastReceiver, new IntentFilter("ChongZhiActivity"));
        title.setText("充值");
    }


    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_chongzhi;
    }

    @OnClick({R.id.fanhui, R.id.chongzhi_weixin, R.id.chongzhi_zhifubao, R.id.chongzhi_chongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.chongzhi_weixin:
                if (flag != 0) {
                    chongzhiWeixinIv.setBackgroundResource(R.drawable.chongzhi_yuan);
                    chongzhiZhifubaoIv.setBackgroundResource(R.drawable.chongzhi_yuan_no);
                    flag = 0;
                }
                break;
            case R.id.chongzhi_zhifubao:
                if (flag != 1) {
                    chongzhiZhifubaoIv.setBackgroundResource(R.drawable.chongzhi_yuan);
                    chongzhiWeixinIv.setBackgroundResource(R.drawable.chongzhi_yuan_no);
                    flag = 1;
                }
                break;
            case R.id.chongzhi_chongzhi:
                if (!chongzhiMoney.getText().toString().equals("")) {
                    if (flag == 0) {
                        OkGo.post(IURL.WeiXin)
                                .tag(this)
                                .params("user_id", MyApplication.userToken)
                                .params("money_num", chongzhiMoney.getText().toString())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        weiXinBean = JsonUtil.parseJsonToBean(s, WeiXinBean.class);
                                        if (weiXinBean != null) {
                                            if (weiXinBean.getStatus() == 0) {
                                                weiXinPay();
                                            } else {
                                                ToastUtils.showToast("请正确输入金额");
                                            }
                                        } else {
                                            ToastUtils.showToast("");
                                        }
                                    }
                                });
                    } else {
                        OkGo.post(IURL.ZhiFuBao)
                                .tag(this)
                                .params("user_id", MyApplication.userToken)
                                .params("money_num", chongzhiMoney.getText().toString())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        ZhiFuBaoBean baoBean = JsonUtil.parseJsonToBean(s, ZhiFuBaoBean.class);
                                        if (baoBean != null) {
                                            if (baoBean.getStatus() == 0) {
                                                zhiFuBaoPay(baoBean.getBody());
                                            } else {
                                                ToastUtils.showToast("请正确输入金额");
                                            }
                                        } else {
                                            ToastUtils.showToast("");
                                        }
                                    }
                                });

                    }
                } else {
                    ToastUtils.showToast("请正确输入金额");
                }
                break;
        }
    }

    private void weiXinPay() {
        PayReq req = new PayReq();
        req.appId = weiXinBean.getAppid();
        req.partnerId = weiXinBean.getPartnerid();
        req.prepayId = weiXinBean.getPrepayid();
        req.packageValue = weiXinBean.getPackageX();
        req.nonceStr = weiXinBean.getNoncestr();
        req.timeStamp = weiXinBean.getTimestamp() + "";
        req.sign = weiXinBean.getSign();
//        MyApplication.mWxApi.sendReq(req);
    }

    private void zhiFuBaoPay(final String data) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(ChongZhiActivity.this);
                // 调用支付接口，获取支付结果
                Map<String, String> result = alipay.payV2(data, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

// 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.showToast("充值成功");
                        finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.showToast("支付结果确认中");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            ToastUtils.showToast("充值失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
}
