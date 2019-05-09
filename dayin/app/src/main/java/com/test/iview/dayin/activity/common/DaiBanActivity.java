package com.test.iview.dayin.activity.common;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
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
    EditText editTxt;
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


    }

    @Override
    public int initLayout() {
        return R.layout.daiban_lay;
    }

    @OnClick({R.id.back, R.id.main_tab1, R.id.main_tab2, R.id.main_tab3,R.id.home_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
                Intent intent = new Intent(this, SuCaiKuActivity.class);
                intent.putExtra("sucai", "kechengbiao");
                startActivityForResult(intent, 1000);
                break;
            case R.id.main_tab2:
                Calendar calendar = Calendar.getInstance();
                showDatePickerDialog(3,editTxt,calendar);
                break;
            case R.id.main_tab3:
                Intent intent1 = new Intent(this, SuCaiKuActivity.class);
                intent1.putExtra("sucai", "biaoqing");
                startActivityForResult(intent1, 1000);
                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView){
                        arrs.get(i).setEditable(false);
                    }
                }
                editTxt.setCursorVisible(false);
                BitmapUtil.getInstance().getCutImage(canv,0);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000 && null != data) {
            int id = data.getIntExtra("img",0);
            addCusView(id);
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


    public void showDatePickerDialog(int themeResId, final EditText tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this
                ,  themeResId
                // 绑定监听器(How the parent is notified that the date is set.)
                ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText( year + getString(R.string.dy_year) + monthOfYear
                        + getString(R.string.dy_month) + dayOfMonth + getString(R.string.dy_day));
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}
