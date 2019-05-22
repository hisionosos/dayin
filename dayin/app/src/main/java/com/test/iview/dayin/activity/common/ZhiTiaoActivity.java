package com.test.iview.dayin.activity.common;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.ResourceUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ZhiTiaoActivity extends BaseActivity {
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
    @BindView(R.id.wangge_lay)
    LinearLayout wanggeLay;
    @BindView(R.id.size_seek)
    SeekBar sizeSeek;
    @BindView(R.id.edit_txt)
    EditText editTxt;
    @BindView(R.id.canv)
    RelativeLayout canv;
    String flag = "";
    private String[] tab_title = {"大小", "调整",  "粗细",};
    private int[] tab_imgs = { R.drawable.tab_daxiao, R.drawable.tab_tiaozheng, R.drawable.tab_cuxi};

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText(R.string.dy_zhitiao);

    }

    @Override
    public void initData() {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);

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

    @Override
    public int initLayout() {
        return R.layout.zhitiao_lay;
    }

    
    private boolean isBlod = false;
    private int editGrave = 0;


    @OnClick({R.id.back,R.id.home_add,R.id.main_tab1,R.id.main_tab2,R.id.main_tab3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.home_add:
                editTxt.setCursorVisible(false);
                BitmapUtil.getInstance().showBitmap(canv,false,0,false);

                break;
            case R.id.main_tab1:
                setting();
                break;
            case R.id.main_tab2:
                if (editGrave == 0){
                    editTxt.setGravity(Gravity.CENTER_HORIZONTAL);
                    editGrave ++;
                }else if (editGrave == 1){
                    editTxt.setGravity(Gravity.RIGHT);
                    editGrave ++;
                }else if (editGrave == 2){
                    editTxt.setGravity(Gravity.LEFT);
                    editGrave --;
                    editGrave --;
                }
                break;
            case R.id.main_tab3:
                if (isBlod){
                    editTxt.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                    isBlod = false;
                }else{
                    editTxt.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                    isBlod = true;
                }
                break;
        }
    }

    private void setting() {
        if (wanggeLay.getVisibility() == View.VISIBLE){
            wanggeLay.setVisibility(View.INVISIBLE);
        }else{
            wanggeLay.setVisibility(View.VISIBLE);
        }
    }

}
