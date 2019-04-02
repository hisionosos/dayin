package com.test.iview.dayin.global;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.test.iview.dayin.utils.SharedPreferencesUtils;
import com.test.iview.dayin.wbapi.Constants1;

import java.util.logging.Level;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static MyApplication application = null;
    public static String userToken = null;
    public static String userUuid = null;

    public static MyApplication getApplication(){
        return application;
    }
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        application = this;
        init();
    }


    private void init(){
        Fresco.initialize(this);
        CrashReport.initCrashReport(getApplicationContext(), "aef05c4e0a", false);

        BroadcastReceiver receiver = new NetWorkChangeBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);

        //极光推送
        JPushInterface.setDebugMode(false);                // Log
        JPushInterface.init(this);              //初始化
        String registrationID = JPushInterface.getRegistrationID(this);
        //极光统计
        JAnalyticsInterface.setDebugMode(false);           // Log
        JAnalyticsInterface.init(this);         //初始化
        JAnalyticsInterface.stopCrashHandler(this); //关闭Crash 集成了Bugly

        OkGo.init(this);

        try {
            WbSdk.install(this, new AuthInfo(this, Constants1.APP_KEY, Constants1.REDIRECT_URL,
                    Constants1.SCOPE));
            OkGo.getInstance().debug("OkGo", Level.INFO, true);
        } catch (Exception e) {
            e.getStackTrace();
        }

        getUserInfos();
    }

    public void getUserInfos() {
        userToken = (String)SharedPreferencesUtils.getParam("userToken",null);
        userUuid = (String)SharedPreferencesUtils.getParam("uuid",null);
    }

}
