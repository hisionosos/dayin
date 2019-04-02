package com.test.iview.dayin.utils;

import android.content.Intent;

import com.test.iview.dayin.activity.LoginActivity;
import com.test.iview.dayin.global.MyApplication;

/**
 * @author
 * @version 1.0
 * @date 2018/3/5
 */

public class SettingUtils {
    private static SettingUtils instance;
    private SharedPreferencesUtils preferenceUtils;
    private static final String IS_LOGIN = "is_login";//用户是否登录

    /**
     * SettingUtils.getInstance().setIsLogin(true);//设置登录配置项
     SettingUtils.getInstance().getIsLogin();//获取登录配置项，false为默认值
     * @param isLogin
     */
    public void setIsLogin(boolean isLogin){
        preferenceUtils.setParam(IS_LOGIN,isLogin);
    }

    public boolean getIsLogin(){
        boolean isLogin = (Boolean) preferenceUtils.getParam(IS_LOGIN,false);
        if (!isLogin){
            loginView();
        }
        return isLogin;
    }


    /**
     * 创建一个新的实例 SettingsUtils.
     */
    private SettingUtils() {
        preferenceUtils = SharedPreferencesUtils.getInstance();
    }

    public static synchronized SettingUtils getInstance(){
        if (instance == null) {
            instance = new SettingUtils();
        }
        return instance;
    }

    public void clearSettings() {
        preferenceUtils.clear(MyApplication.getContext());
    }

    public void loginView(){
        Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }

}
