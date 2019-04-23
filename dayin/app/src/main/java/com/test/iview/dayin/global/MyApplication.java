package com.test.iview.dayin.global;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.QbSdk;
import com.test.iview.dayin.utils.SharedPreferencesUtils;
import com.test.iview.dayin.utils.cache.AppACache;
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
    public static AppACache mCache;
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
        mCache = AppACache.get(this);
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

        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。

            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("@@","加载内核是否成功:"+b);
            }
        });

        getUserInfos();
    }

    public void getUserInfos() {
        userToken = (String)SharedPreferencesUtils.getParam("userToken",null);
        userUuid = (String)SharedPreferencesUtils.getParam("uuid",null);
    }

}
