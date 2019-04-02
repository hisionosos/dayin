package com.test.iview.dayin.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.lzy.okgo.OkGo;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.AppManager;
import com.test.iview.dayin.view.common.HttpProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity extends Activity {

    /***是否显示标题栏*/
    private  boolean isshowtitle = false;
    /***是否显示状态栏*/
    private  boolean isshowstate = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isshowtitle){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if(!isshowstate){
            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN);
        }
        setContentView(initLayout());
        ButterKnife.bind(this);



        initView(savedInstanceState);
        initData();
        AppManager.getAppManager().addActivity(this);

    }


    public void setTitleBar(boolean ishow) {
        isshowtitle = ishow;
    }

    public void setTopBar(boolean ishow) {
        isshowstate=ishow;
    }


    @Override
    protected void onDestroy() {
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], right = left + v.getWidth(), bottom = top + v.getHeight();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;// 忽略
            } else
                return true;
        }
        return false;
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private HttpProgressDialog dialog = null;

    public void showLoadingDialog(){
        if (null == dialog){
            dialog = new HttpProgressDialog(this,"");
            dialog.setCanceledOnTouchOutside(false);
        }

        dialog.show();

    }

    public void hideLaodingDialog(){
        if (null != dialog){
            dialog.dismiss();
            dialog = null;
        }
    }


    public abstract void initView(@Nullable Bundle savedInstanceState);

    public abstract void initData();

    public abstract int initLayout();

}
