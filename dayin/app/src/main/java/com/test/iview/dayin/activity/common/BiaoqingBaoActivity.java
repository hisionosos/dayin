package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class BiaoqingBaoActivity extends BaseActivity{
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
    @BindView(R.id.main_tab2)
    RadioButton mainTab2;
    @BindView(R.id.edit_txt)
    EditText editTxt;
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
    @BindView(R.id.wangge_lay)
    LinearLayout wanggeLay;
    @BindView(R.id.size_seek)
    SeekBar sizeSeek;



    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<SingleTouchView> arrs = new ArrayList<>();

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;
        mScreenWidth = metrics.widthPixels;

        commonTitle.setText(R.string.dy_baioqingdayin);

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        editTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtil.getInstance().cannelEdit(arrs,eds,false);
                editTxt.setCursorVisible(true);
            }
        });

    }

    @Override
    public void initData() {
        sizeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editTxt.setTextSize(progress/5 + 15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void setting() {
        if (wanggeLay.getVisibility() == View.VISIBLE){
            wanggeLay.setVisibility(View.INVISIBLE);
        }else{
            wanggeLay.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int initLayout() {
        return R.layout.biaoqingbao_lay;
    }





    @OnClick({R.id.back,R.id.main_tab1, R.id.main_tab2,R.id.home_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
                editTxt.setCursorVisible(false);
                Intent intent = new Intent(this,SuCaiKuActivity.class);
                intent.putExtra("sucai","biaoqing");
                startActivityForResult(intent,1000);
                break;
            case R.id.main_tab2:
                setting();
                break;
            case R.id.home_add:

                eds.add(editTxt);
                BitmapUtil.getInstance().cannelEdit(arrs,eds,false);
                BitmapUtil.getInstance().getCutImage(canv,false,0,false);
                BitmapUtil.getInstance().cannelEdit(arrs,eds,true);
                break;
        }
    }
    private ArrayList<EditText> eds = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            int id = data.getIntExtra("img",0);
            addCusView(id);
        }
    }


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
}
