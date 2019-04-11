package com.test.iview.dayin.view.common;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.test.iview.dayin.R;


public class SettingPopuWindow extends PopupWindow implements View.OnTouchListener{
    private Activity mContext;
    private View mRoot;
    private callBack mCallBack;
    private SliderFont mFontSlider;
    private LinearLayout mFontP;
    private int mScrennWidth;
    private boolean mChangeFont = false;
    private float mLastX;
    private int mFontIndex;

    private int mNowPick;
    private static final String TAG = "SettingPopuWindow";

    public SettingPopuWindow(Activity context,
                             int width, int height) {
        super(width, height);
        mContext = context;
        mScrennWidth = width;
        mLastX = mScrennWidth/2;
        mFontIndex = 3;
        init();
        initView();
        setTouchInterceptor(this);
    }

    private void init(){
       mRoot =  LayoutInflater.from(mContext).inflate(R.layout.layout_richtext_setting,null,false);

       setContentView(mRoot);
       setFocusable(true);
       setOutsideTouchable(true);
       setTouchable(true);
       setAnimationStyle(R.style.setting_popu_anim);
    }

    private void initView(){

        mFontSlider = mRoot.findViewById(R.id.seekbar_font);
        mFontSlider.setScreemWidth(mScrennWidth);
        mFontP = mRoot.findViewById(R.id.ll_font);

    }


    private float getScreenBrightness() {

        Window window = mContext.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if(params.screenBrightness!=-1){
            return params.screenBrightness;
        }

        try {
            return Settings.System.getInt(mContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        }catch (Settings.SettingNotFoundException e){
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public void setTouchInterceptor(View.OnTouchListener l) {
        super.setTouchInterceptor(l);
    }

    public int getFontIndex(){
        return mFontIndex;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_MOVE){

            if(y>mFontP.getY() &&y<mFontP.getY()+mFontP.getHeight()+30 || mChangeFont){
                mChangeFont = true;
                float specX = x-mLastX;
                mFontSlider.move(specX);
                mLastX = x;
                mFontSlider.invalidate();
            }

        }else if(event.getAction() == MotionEvent.ACTION_DOWN){

            if(y>mFontP.getY() &&y<mFontP.getY()+mFontP.getHeight()){
                mChangeFont = true;
                mFontSlider.setCenter(x);
                mLastX = x;
                mFontSlider.invalidate();
            }

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            if(mChangeFont){
               mFontIndex =  mFontSlider.adJustCenter(x);
               float fontSize = mFontSlider.getFontSize(mFontIndex);
               mCallBack.setFontSize(fontSize, mFontIndex);
                mLastX = x;
            }
            mChangeFont = false;


        }
        return false;
    }


    public int getNowPick(){
        return  mNowPick;
    }


    public void setCallback(callBack callback){
        mCallBack = callback;
    }

    public interface callBack{
        void setFontSize(float font, int index);
    }
}
