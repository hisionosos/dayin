package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class TuKuangActivity extends BaseActivity{
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
    @BindView(R.id.canv_img)
    ImageView canvImg;
    @BindView(R.id.edit_layout)
    LinearLayout editLayout;

    @BindView(R.id.wangge_lay)
    LinearLayout wanggeLay;
    @BindView(R.id.size_seek)
    SeekBar sizeSeek;
    LinearLayout.LayoutParams layoutParams;

    EditText edit1;
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        commonTitle.setText(R.string.dy_tukuangdayin);

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        edit1 = new EditText(this);
        edit1.setTextColor(Color.parseColor("#000000"));
        edit1.setGravity(Gravity.LEFT);
        edit1.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.dp_80));
        edit1.setBackground(null);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != edit1)
                    edit1.setCursorVisible(true);
            }
        });
        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtil.getInstance().cannelEdit(arrs,eds,false);
                editTxt.setCursorVisible(true);
            }
        });

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtil.getInstance().cannelEdit(arrs,eds,false);
                editTxt.setCursorVisible(true);
            }
        });

        sizeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (null != edit1)
                edit1.setTextSize(progress/5 + 15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
                startActivityForResult(intent,6000);
                break;
            case R.id.main_tab2:
                CameraUtils.albumChoose(this,null);
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
                if (null != edit1){
                    eds.add(editTxt);
                }

                BitmapUtil.getInstance().cannelEdit(arrs,eds,false);
                BitmapUtil.getInstance().getCutImage(canv,true,0,false);
                BitmapUtil.getInstance().cannelEdit(arrs,eds,true);


                break;
        }
    }

    private ArrayList<EditText> eds = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            if (requestCode == 6000) {
                int id = data.getIntExtra("img",0);
                canvImg.setBackgroundResource(id);
                getEditSize(id);
            }else{
                int id = data.getIntExtra("img",0);
                addCusView(id);
            }

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
            if (wanggeLay.getVisibility() == View.VISIBLE){
                wanggeLay.setVisibility(View.INVISIBLE);
            }else{
                wanggeLay.setVisibility(View.VISIBLE);
            }

    }

    private int g(int d){
        return  DimensUtils.dp2px(this,d);
    }


    private void getEditSize(int id){

        switch (id){
            case R.mipmap.tukuang_1:
                layoutParams.setMargins(g(10),g(20), g(10), g(10));
                break;
            case R.mipmap.tukuang_2:
                layoutParams.setMargins(g(65),g(80), g(65), g(10));
                break;
            case R.mipmap.tukuang_3:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_4:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_5:
                layoutParams.setMargins(g(60),g(60), g(30), g(10));
                break;
            case R.mipmap.tukuang_6:
                layoutParams.setMargins(g(30),g(60), g(30), g(10));
                break;
            case R.mipmap.tukuang_7:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_8:
                layoutParams.setMargins(g(60),g(60), g(60), g(10));
                break;
            case R.mipmap.tukuang_9:
                layoutParams.setMargins(g(30),g(250), g(30), g(10));
                break;
            case R.mipmap.tukuang_10:
                layoutParams.setMargins(g(30),g(40), g(30), g(10));
                break;
            case R.mipmap.tukuang_11:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_12:
                layoutParams.setMargins(g(10),g(10), g(10), g(10));
                break;
            case R.mipmap.tukuang_13:
                layoutParams.setMargins(g(20),g(60), g(20), g(10));
                break;
            case R.mipmap.tukuang_14:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_15:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_16:
                layoutParams.setMargins(g(60),g(210), g(60), g(10));
                break;
            case R.mipmap.tukuang_17:
                layoutParams.setMargins(g(20),g(120), g(20), g(10));
                break;
            case R.mipmap.tukuang_18:
                layoutParams.setMargins(g(30),g(60), g(30), g(10));
                break;
            case R.mipmap.tukuang_19:
                layoutParams.setMargins(g(100),g(210), g(100), g(10));
                break;
            case R.mipmap.tukuang_20:
                layoutParams.setMargins(g(100),g(240), g(100), g(10));
                break;
            case R.mipmap.tukuang_21:
                layoutParams.setMargins(g(30),g(20), g(30), g(10));
                break;
            case R.mipmap.tukuang_22:
                layoutParams.setMargins(g(100),g(240), g(80), g(10));
                break;
            case R.mipmap.tukuang_23:
                layoutParams.setMargins(g(100),g(240), g(100), g(10));
                break;
            case R.mipmap.tukuang_24:
                layoutParams.setMargins(g(30),g(40), g(60), g(10));
                break;
            case R.mipmap.tukuang_25:
                layoutParams.setMargins(g(20),g(20), g(65), g(10));
                break;
            case R.mipmap.tukuang_26:
                layoutParams.setMargins(g(20),g(60), g(160), g(10));
                break;
            case R.mipmap.tukuang_27:
                layoutParams.setMargins(g(20),g(20), g(20), g(10));
                break;
            case R.mipmap.tukuang_28:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_29:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_30:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_31:
                layoutParams.setMargins(g(110),g(165), g(110), g(10));
                break;
            case R.mipmap.tukuang_32:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_33:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_34:
                layoutParams.setMargins(g(40),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_35:
                layoutParams.setMargins(g(20),g(20), g(100), g(10));
                break;
            case R.mipmap.tukuang_36:
                layoutParams.setMargins(g(20),g(20), g(20), g(10));
                break;
            case R.mipmap.tukuang_37:
                layoutParams.setMargins(g(20),g(20), g(20), g(10));
                break;
            case R.mipmap.tukuang_38:
                layoutParams.setMargins(g(60),g(310), g(40), g(10));
                break;
            case R.mipmap.tukuang_39:
                layoutParams.setMargins(g(20),g(20), g(20), g(10));
                break;
            case R.mipmap.tukuang_40:
                layoutParams.setMargins(g(20),g(20), g(20), g(10));
                break;
            case R.mipmap.tukuang_41:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_42:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_43:
                layoutParams.setMargins(g(80),g(20), g(10), g(10));
                break;
            case R.mipmap.tukuang_44:
                layoutParams.setMargins(g(60),g(115), g(60), g(10));
                break;
            case R.mipmap.tukuang_45:
                layoutParams.setMargins(g(60),g(125), g(60), g(10));
                break;
            case R.mipmap.tukuang_46:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_47:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_48:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_49:
                layoutParams.setMargins(g(20),g(20), g(20), g(10));
                break;
            case R.mipmap.tukuang_50:
                layoutParams.setMargins(g(20),g(80), g(20), g(10));
                break;
            case R.mipmap.tukuang_51:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_52:
                layoutParams.setMargins(g(20),g(180), g(40), g(10));
                break;
            case R.mipmap.tukuang_53:
                layoutParams.setMargins(g(90),g(140), g(80), g(10));
                break;
            case R.mipmap.tukuang_54:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_55:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_56:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_57:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_58:
                layoutParams.setMargins(g(60),g(80), g(90), g(10));
                break;
            case R.mipmap.tukuang_59:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_60:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;
            case R.mipmap.tukuang_61:
                layoutParams.setMargins(g(60),g(80), g(60), g(10));
                break;

        }


        edit1.setLayoutParams(layoutParams);
        editLayout.removeAllViews();
        editLayout.addView(edit1);
    }

    public static void main(String args[]) {
        for (int i = 1; i < 62; i++) {
            System.out.println("case R.mipmap.tukuang_" + i + ":\n" +
                    "                layoutParams.setMargins(200, g(220), 200, 220);\n" +
                    "                break;");
        }
    }
}
