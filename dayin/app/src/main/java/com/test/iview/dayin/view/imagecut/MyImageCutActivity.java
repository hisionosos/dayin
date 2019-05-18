package com.test.iview.dayin.view.imagecut;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ViewSwitcher;

import com.lzy.okgo.OkGo;
import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.AppManager;
import com.test.iview.dayin.utils.AppUtils;
import com.test.iview.dayin.view.common.HttpProgressDialog;
import com.test.iview.dayin.view.imagecut.core.IMGMode;
import com.test.iview.dayin.view.imagecut.core.IMGText;
import com.test.iview.dayin.view.imagecut.view.IMGColorGroup;
import com.test.iview.dayin.view.imagecut.view.IMGView;

import java.util.Locale;

import butterknife.ButterKnife;

public abstract class MyImageCutActivity extends Activity implements View.OnClickListener,
        IMGTextEditDialog.Callback, RadioGroup.OnCheckedChangeListener,
        DialogInterface.OnShowListener, DialogInterface.OnDismissListener {

    protected IMGView mImgView;

    private RadioGroup mModeGroup;

    private IMGColorGroup mColorGroup;

    private IMGTextEditDialog mTextDialog;

    private View mLayoutOpSub;

    private ViewSwitcher mOpSwitcher, mOpSubSwitcher;

    public static final int OP_HIDE = -1;

    public static final int OP_NORMAL = 0;

    public static final int OP_CLIP = 1;

    public static final int OP_SUB_DOODLE = 0;

    public static final int OP_SUB_MOSAIC = 1;

    /***是否显示标题栏*/
    private  boolean isshowtitle = false;
    /***是否显示状态栏*/
    private  boolean isshowstate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isshowtitle){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if(!isshowstate){
            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        setContentView(R.layout.tupian_lay);
        initViews();
        ButterKnife.bind(this);
//        String local = MyApplication.mCache.getAsString("local") == null ? "zh" : MyApplication.mCache.getAsString("local");
//        updateActivity(local);
        AppManager.getAppManager().addActivity(this);
        onCreated();
//        Bitmap bitmap = getBitmap();
//        if (bitmap != null) {
//            setContentView(R.layout.image_edit_activity);



//        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(AppUtils.updateLanguage(newBase));
    }


    public void updateActivity(String sta) {
        // 本地语言设置
        Locale myLocale = new Locale(sta);
        Resources res = getResources();// 获得res资源对象
        DisplayMetrics dm = res.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
        Configuration conf = res.getConfiguration();// 获得设置对象
        conf.locale = myLocale;// 简体中文
        res.updateConfiguration(conf, dm);

    }

    public void onCreated() {

    }

    private void initViews() {
        mImgView = findViewById(R.id.image_canvas);
        mModeGroup = findViewById(R.id.rg_modes);

        mOpSwitcher = findViewById(R.id.vs_op);
        mOpSubSwitcher = findViewById(R.id.vs_op_sub);

        mColorGroup = findViewById(R.id.cg_colors);
        mColorGroup.setOnCheckedChangeListener(this);

        mLayoutOpSub = findViewById(R.id.layout_op_sub);
    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid ==  R.id.main_tab3){
            onRotateClipClick();
        }else if (vid ==R.id.main_tab4){
            onModeClick(IMGMode.CLIP);
        }else if (vid ==R.id.main_tab22){
//            onModeClick(IMGMode.DOODLE);
            setPainSize();
        } else if (vid == R.id.rb_doodle) {
            onModeClick(IMGMode.DOODLE);
        } else if (vid == R.id.btn_text) {
            onTextModeClick();
        } else if (vid == R.id.rb_mosaic) {
            onModeClick(IMGMode.MOSAIC);
        } else if (vid == R.id.btn_clip) {
            onModeClick(IMGMode.CLIP);
        } else if (vid == R.id.undo_btn) {
            onUndoClick();
        } else if (vid == R.id.btn_undo) {
            onUndoClick();
        } else if (vid == R.id.tv_done) {
            onDoneClick();
        } else if (vid == R.id.tv_cancel) {
            onCancelClick();
        } else if (vid == R.id.ib_clip_cancel) {
            onCancelClipClick();
        } else if (vid == R.id.ib_clip_done) {
            onDoneClipClick();
        } else if (vid == R.id.tv_clip_reset) {
            onResetClipClick();
        } else if (vid == R.id.ib_clip_rotate) {
            onRotateClipClick();
        }
    }

    public void updateModeUI() {
        IMGMode mode = mImgView.getMode();
        switch (mode) {
            case DOODLE:
                mModeGroup.check(R.id.rb_doodle);
                setOpSubDisplay(OP_SUB_DOODLE);
                break;
            case MOSAIC:
                mModeGroup.check(R.id.rb_mosaic);
                setOpSubDisplay(OP_SUB_MOSAIC);
                break;
            case NONE:
                mModeGroup.clearCheck();
                setOpSubDisplay(OP_HIDE);
                break;
        }
    }

    public void onTextModeClick() {
        if (mTextDialog == null) {
            mTextDialog = new IMGTextEditDialog(this, this);
            mTextDialog.setOnShowListener(this);
            mTextDialog.setOnDismissListener(this);
        }
        mTextDialog.show();
    }

    @Override
    public final void onCheckedChanged(RadioGroup group, int checkedId) {
        onColorChanged(mColorGroup.getCheckColor());
    }

    public void setOpDisplay(int op) {
        if (op >= 0) {
            mOpSwitcher.setDisplayedChild(op);
        }
    }

    public void setOpSubDisplay(int opSub) {
        if (opSub < 0) {
            mLayoutOpSub.setVisibility(View.GONE);
        } else {
            mOpSubSwitcher.setDisplayedChild(opSub);
            mLayoutOpSub.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        mOpSwitcher.setVisibility(View.GONE);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mOpSwitcher.setVisibility(View.VISIBLE);
    }

//    public abstract Bitmap getBitmap();

    public abstract void onModeClick(IMGMode mode);

    public abstract void onUndoClick();

    public abstract void onCancelClick();

    public abstract void onDoneClick();

    public abstract void onCancelClipClick();

    public abstract void onDoneClipClick();

    public abstract void onResetClipClick();

    public abstract void onRotateClipClick();

    public abstract void onColorChanged(int checkedColor);
    public abstract void setPainSize();

    @Override
    public abstract void onText(IMGText text);



    @Override
    protected void onDestroy() {
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
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


}
