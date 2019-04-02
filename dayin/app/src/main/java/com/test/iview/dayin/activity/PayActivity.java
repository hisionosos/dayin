package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.util.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class PayActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pay_pic)
    ImageView payPic;
    @BindView(R.id.pay_tv1)
    TextView payTv1;
    @BindView(R.id.pay_tv2)
    TextView payTv2;
    @BindView(R.id.pay_tv3)
    TextView payTv3;


    private int time = 5;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (time == 0) {
                finish();
            } else {
                time--;
                payTv2.setText(time + "秒");
            }
            if (time >= 0) {
                handler.postDelayed(this, 1000);
            }
        }
    };

    /**
     * 倒计时
     *
     * @param l
     * @return
     */
    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }

        if (minute >= 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;
    }


    @OnClick(R.id.fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        if (getIntent().getIntExtra("Pay_Flag", 0) == 0) {//失败
            title.setText("充值失败");
            payPic.setBackgroundResource(R.drawable.chongzhishibai);
            payTv1.setText("充值失败!");
            payTv1.setTextColor(getResources().getColor(R.color.t66));
            payTv3.setText("充值结果返回失败,请检查您的网络!");
        } else {
            title.setText("充值成功");
            payPic.setBackgroundResource(R.drawable.chongzhichenggong);
            payTv1.setText("充值成功!");
            payTv1.setTextColor(getResources().getColor(R.color.main_tab_color));
            payTv2.setText("5秒");
            payTv3.setText("后返回我的账户!");
        }
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_pay;
    }
}
