package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.activity.PiaojuActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.view.SingleTouchView;
import com.test.iview.dayin.view.common.SettingPopuWindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuKuangActivity extends BaseActivity implements SettingPopuWindow.callBack{
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.main_tab)
    RadioGroup mainTab;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edit_txt)
    EditText editTxt;
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
    @BindView(R.id.main_tab2)
    RadioButton mainTab2;
    @BindView(R.id.main_tab3)
    RadioButton mainTab3;
    @BindView(R.id.main_tab4)
    RadioButton mainTab4;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        commonTitle.setText("图框打印");

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        editTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxt.setCursorVisible(true);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.tukuang_lay;
    }


    @OnClick({R.id.back,R.id.main_tab1, R.id.main_tab2, R.id.main_tab3, R.id.main_tab4,R.id.home_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
                Intent intent = new Intent(this,SuCaiKuActivity.class);
                intent.putExtra("sucai","tukuang");
                startActivityForResult(intent,1000);
                break;
            case R.id.main_tab2:
                CameraUtils.albumChoose(this);
                break;
            case R.id.main_tab3:
                setting();
                break;
            case R.id.main_tab4:
                Intent intent4 = new Intent(this,SuCaiKuActivity.class);
                intent4.putExtra("sucai","biaoqing");
                startActivityForResult(intent4,1000);
                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    arrs.get(i).setEditable(false);

                }
                editTxt.setCursorVisible(false);
                BitmapUtil.getInstance().getCutImage(canv);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            int id = data.getIntExtra("img",0);
            addCusView(id);
        }
        if (requestCode == CameraUtils.CODE_ALBUM_CHOOSE && null != data.getData()) {
//              int s = BitmapUtil.getBitmapBytes(MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData()));
            addImage(data.getData());
        }

    }

    private ArrayList<SingleTouchView> arrs = new ArrayList<>();
    private void addCusView(int id){
        if (id != 0){
            SingleTouchView singleTouchView = new SingleTouchView(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            singleTouchView.setLayoutParams(layoutParams);
            singleTouchView.setImageResource(id);
            canv.addView(singleTouchView);
            arrs.add(singleTouchView);
        }

    }

    private void addImage(Uri uri){
        showLoadingDialog();
        BitmapUtil.createScaledBitmap(this,uri,350, new BitmapUtil.MyCallback() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onSucceed(final Object object) {
                hideLaodingDialog();
                TuKuangActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SingleTouchView singleTouchView = new SingleTouchView(TuKuangActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp((Bitmap) object);
                        canv.addView(singleTouchView);
                        arrs.add(singleTouchView);
                    }
                });

            }

            @Override
            public void onError() {
                hideLaodingDialog();
            }
        });
    }

    private int mScreenWidth;
    private void setting() {

        SettingPopuWindow popupWindow = new SettingPopuWindow(this,
                mScreenWidth - 100,
                WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.showAtLocation(mainTab2, Gravity.BOTTOM | Gravity.CENTER,
                0, 80);
        popupWindow.setCallback(this);


    }

    @Override
    public void setFontSize(float font, int index) {
        editTxt.setTextSize(font);
    }
}
