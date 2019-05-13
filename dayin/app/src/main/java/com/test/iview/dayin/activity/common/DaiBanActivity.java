package com.test.iview.dayin.activity.common;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaiBanActivity extends BaseActivity {
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
    String flag = "";
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
    @BindView(R.id.main_tab2)
    RadioButton mainTab2;
    @BindView(R.id.main_tab3)
    RadioButton mainTab3;
    @BindView(R.id.edit_txt)
    TextView editTxt;

    @BindView(R.id.head_lay)
    ImageView headLay;
    @BindView(R.id.mid_lay)
    LinearLayout midLay;
    @BindView(R.id.bottom_lay)
    ImageView bottomLay;
    @BindView(R.id.scoll_lay)
    ScrollView scollLay;
    //    private String[] tab_title = {getString(R.string.dy_muban), getString(R.string.dy_shijian), getString(R.string.dy_biaoqing),};
    private int[] tab_imgs = {R.drawable.tab_muban, R.drawable.tab_shijian, R.drawable.tab_biaoqing};

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText(R.string.dy_daiban);
    }

    @Override
    public void initData() {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        midLay.setVisibility(View.GONE);

    }

    @Override
    public int initLayout() {
        return R.layout.daiban_lay;
    }

    private boolean isImg = true;

    @OnClick({R.id.back, R.id.main_tab1, R.id.main_tab2, R.id.main_tab3, R.id.main_tab4, R.id.home_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
                Intent intent = new Intent(this, SuCaiKuActivity.class);
                intent.putExtra("sucai", "daiban");
                startActivityForResult(intent, 1000);
                break;
            case R.id.main_tab2:
                Calendar calendar = Calendar.getInstance();
                showDatePickerDialog(3, editTxt, calendar);
                break;
            case R.id.main_tab3:
                Intent intent1 = new Intent(this, SuCaiKuActivity.class);
                intent1.putExtra("sucai", "biaoqing");
                startActivityForResult(intent1, 1000);
                break;
            case R.id.main_tab4:
                addLay();
                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView) {
                        arrs.get(i).setEditable(false);
                    }
                }
//                editTxt.setCursorVisible(false);
//                BitmapUtil.getInstance().getCutImage(canv, isImg, 0, false);
                BitmapUtil.getInstance().getBitmapScrollView(scollLay, isImg, 0);

                break;
        }
    }


    private int muban = 0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000 && null != data) {
            int id = data.getIntExtra("img", 0);
            editTxt.setVisibility(View.GONE);
            midLay.setVisibility(View.VISIBLE);
            midLay.removeAllViews();

            switch (id){
                case R.mipmap.biaoqian_24:
                    muban = 1;
                    headLay.setBackgroundResource(R.drawable.daiban1_h);
                    bottomLay.setBackgroundResource(R.drawable.daiban1_b);
                    midLay.setBackgroundResource(R.drawable.daiban1_m);
                    addLay();
                    break;
                case R.mipmap.biaoqian_26:
                    muban = 2;
                    headLay.setBackgroundResource(R.drawable.daiban2_h);
                    bottomLay.setBackgroundResource(R.drawable.daiban2_b);
                    midLay.setBackgroundResource(R.drawable.daiban2_m);
                    addLay();
                    break;
                case R.mipmap.biaoqian_27:
                    muban = 3;
                    headLay.setBackgroundResource(R.drawable.daiban3_h);
                    bottomLay.setBackgroundResource(R.drawable.daiban3_b);
                    midLay.setBackgroundResource(R.drawable.daiban3_m);
                    addLay();
                    break;
                case R.mipmap.biaoqian_29:
                    muban = 4;
                    headLay.setBackgroundResource(R.drawable.daiban4_h);
                    bottomLay.setBackgroundResource(R.drawable.daiban4_b);
                    midLay.setBackgroundResource(R.drawable.daiban4_m);
                    addLay();
                    break;
                case R.mipmap.biaoqian_30:
                    muban = 5;
                    headLay.setBackgroundResource(R.drawable.daiban5_h);
                    bottomLay.setBackgroundResource(R.drawable.daiban5_b);
                    midLay.setBackgroundResource(R.drawable.daiban5_m);
                    addLay();
                    break;
                case R.mipmap.biaoqian_31:
                    muban = 6;
                    headLay.setBackgroundResource(R.drawable.daiban6_h);
                    bottomLay.setBackgroundResource(R.drawable.daiban6_b);
                    midLay.setBackgroundResource(R.drawable.daiban6_m);
                    addLay();
                    break;
            }

//            addCusView(id);
        }


    }


    private void addLay(){

        int realSize = DimensUtils.dp2px(this, 40);
        int dimen = getResources().getDimensionPixelSize(R.dimen.dp_40);
        LinearLayout.LayoutParams headLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout headlinearLayout = new LinearLayout(this);
        headlinearLayout.setLayoutParams(headLayParams);
        headlinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        if (muban == 1){
            headlinearLayout.setBackgroundResource(R.drawable.daiban1_m);
        }else if (muban == 2){
            headlinearLayout.setBackgroundResource(R.drawable.daiban2_m);
        }else if (muban == 3){
            headlinearLayout.setBackgroundResource(R.drawable.daiban3_m);
        }else if (muban == 4){
            headlinearLayout.setBackgroundResource(R.drawable.daiban4_m);
        }else if (muban == 5){
            headlinearLayout.setBackgroundResource(R.drawable.daiban5_m);
        }else if (muban == 6){
            headlinearLayout.setBackgroundResource(R.drawable.daiban6_m);
        }



        LinearLayout.LayoutParams cLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cLayParams.setMargins(dimen,0,0,0);
        CheckBox checkBox = new CheckBox(this);
        checkBox.setLayoutParams(cLayParams);
        headlinearLayout.addView(checkBox);

        LinearLayout.LayoutParams editLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editLayParams.setMargins(0,0,dimen,0);
        EditText edit = new EditText(this);
        edit.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        edit.setLayoutParams(editLayParams);
        headlinearLayout.addView(edit);

        midLay.addView(headlinearLayout);


    }
    private ArrayList<SingleTouchView> arrs = new ArrayList<>();

    private void addCusView(int id) {
        if (id != 0) {
            SingleTouchView singleTouchView = new SingleTouchView(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            singleTouchView.setLayoutParams(layoutParams);
            singleTouchView.setImageResource(id);
            canv.addView(singleTouchView);
            arrs.add(singleTouchView);
        }

    }


    public void showDatePickerDialog(int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this
                , themeResId
                // 绑定监听器(How the parent is notified that the date is set.)
                , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                editTxt.setVisibility(View.VISIBLE);
                tv.setText(year + getString(R.string.dy_year) + monthOfYear
                        + getString(R.string.dy_month) + dayOfMonth + getString(R.string.dy_day));
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}
