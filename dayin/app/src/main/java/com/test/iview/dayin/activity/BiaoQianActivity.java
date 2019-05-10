package com.test.iview.dayin.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.common.SuCaiKuActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class BiaoQianActivity extends BaseActivity {
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
    @BindView(R.id.main_tab4)
    RadioButton mainTab4;
    @BindView(R.id.main_tab5)
    RadioButton mainTab5;

    @BindView(R.id.wangge_lay)
    LinearLayout wanggeLay;
    @BindView(R.id.size_seek)
    SeekBar sizeSeek;
    @BindView(R.id.code_bar)
    RelativeLayout codeBar;
    @BindView(R.id.get_rcode)
    Button getRcode;
    @BindView(R.id.txt_url)
    EditText txtUrl;
    @BindView(R.id.biaoqian_1)
    EditText biaoqian1;
    @BindView(R.id.biaoqian_2)
    EditText biaoqian2;
    @BindView(R.id.biaoqian_3)
    EditText biaoqian3;
    @BindView(R.id.biaoqian_4)
    EditText biaoqian4;
    @BindView(R.id.biaoqian1_lay)
    LinearLayout biaoqian1Lay;
    @BindView(R.id.biaoqiantwo_0)
    EditText biaoqiantwo0;
    @BindView(R.id.biaoqiantwo_1)
    EditText biaoqiantwo1;
    @BindView(R.id.biaoqiantwo_2)
    EditText biaoqiantwo2;
    @BindView(R.id.biaoqiantwo_3)
    EditText biaoqiantwo3;
    @BindView(R.id.biaoqian2_lay)
    LinearLayout biaoqian2Lay;
    @BindView(R.id.biaoqianthree)
    EditText biaoqianthree;
    @BindView(R.id.biaoqian3_lay)
    LinearLayout biaoqian3Lay;
    @BindView(R.id.biaoqianfour_1)
    EditText biaoqianfour1;
    @BindView(R.id.biaoqianfour_2)
    EditText biaoqianfour2;
    @BindView(R.id.biaoqianfour_3)
    EditText biaoqianfour3;
    @BindView(R.id.biaoqianfour_4)
    EditText biaoqianfour4;
    @BindView(R.id.biaoqianfour_5)
    EditText biaoqianfour5;
    @BindView(R.id.biaoqianfour_6)
    EditText biaoqianfour6;
    @BindView(R.id.biaoqian4_lay)
    LinearLayout biaoqian4Lay;
    @BindView(R.id.main_tab0)
    RadioButton mainTab0;

    private String[] tab_title = {"模板", "大小", "调整", "表情包", "粗细", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_muban, R.drawable.tab_daxiao, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_cuxi, R.drawable.tab_ercode};

    private int moban = 0;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        if (flag.equals("2")) {
            commonTitle.setText(R.string.dy_biaoqiandayin);
        } else {
            commonTitle.setText(R.string.dy_buganjiaodayin);
        }


        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);


    }

    @Override
    public void initData() {
        sizeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (flag.equals("2")) {
                    if (moban == 1){
                        biaoqian1.setTextSize(progress/5 + 15);
                        biaoqian2.setTextSize(progress/5 + 15);
                        biaoqian3.setTextSize(progress/5 + 15);
                        biaoqian4.setTextSize(progress/5 + 15);
                    }else if (moban == 2){
                        biaoqiantwo0.setTextSize(progress/5 + 15);
                        biaoqiantwo1.setTextSize(progress/5 + 15);
                        biaoqiantwo2.setTextSize(progress/5 + 15);
                        biaoqiantwo3.setTextSize(progress/5 + 15);
                    }else if (moban == 3){
                        biaoqianthree.setTextSize(progress/5 + 15);
                    }else if (moban == 4){
                        biaoqianfour1.setTextSize(progress/5 + 15);
                        biaoqianfour2.setTextSize(progress/5 + 15);
                        biaoqianfour3.setTextSize(progress/5 + 15);
                        biaoqianfour4.setTextSize(progress/5 + 15);
                        biaoqianfour5.setTextSize(progress/5 + 15);
                        biaoqianfour6.setTextSize(progress/5 + 15);
                    }

//                }
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
        return R.layout.biaoqian_lay;
    }


    private boolean isBlod = false;
    private int editGrave = 0;

    @OnClick({R.id.back, R.id.main_tab1, R.id.main_tab2, R.id.main_tab3, R.id.main_tab4, R.id.main_tab5, R.id.main_tab6, R.id.home_add, R.id.get_rcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1://模板
                Intent intent = new Intent(this, SuCaiKuActivity.class);
                intent.putExtra("sucai", "biaoqianzhi");
                startActivityForResult(intent, 1000);
                break;
            case R.id.main_tab2://大小
                setting();
                break;
            case R.id.main_tab3://调整
                if (editGrave == 0) {
//                    if (flag.equals("2")) {
                        if (moban == 1){
                            biaoqian1.setGravity(Gravity.CENTER);
                            biaoqian2.setGravity(Gravity.CENTER);
                            biaoqian3.setGravity(Gravity.CENTER);
                            biaoqian4.setGravity(Gravity.CENTER);
                        }else if (moban == 2){
                            biaoqiantwo0.setGravity(Gravity.CENTER);
                            biaoqiantwo1.setGravity(Gravity.CENTER);
                            biaoqiantwo2.setGravity(Gravity.CENTER);
                            biaoqiantwo3.setGravity(Gravity.CENTER);
                        }else if (moban == 3){
                            biaoqianthree.setGravity(Gravity.CENTER);
                        }else if (moban == 4){
                            biaoqianfour1.setGravity(Gravity.CENTER);
                            biaoqianfour2.setGravity(Gravity.CENTER);
                            biaoqianfour3.setGravity(Gravity.CENTER);
                            biaoqianfour4.setGravity(Gravity.CENTER);
                            biaoqianfour5.setGravity(Gravity.CENTER);
                            biaoqianfour6.setGravity(Gravity.CENTER);
                        }

//                    }
                    editGrave++;
                } else if (editGrave == 1) {
//                    if (flag.equals("2")) {
                        if (moban == 1){
                            biaoqian1.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqian2.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqian3.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqian4.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        }else if (moban == 2){
                            biaoqiantwo0.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqiantwo1.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqiantwo2.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqiantwo3.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        }else if (moban == 3){
                            biaoqianthree.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        }else if (moban == 4){
                            biaoqianfour1.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqianfour2.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqianfour3.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqianfour4.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqianfour5.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                            biaoqianfour6.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        }

//                    }

                    editGrave++;
                } else if (editGrave == 2) {
//                    if (flag.equals("2")) {
                        if (moban == 1){
                            biaoqian1.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqian2.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqian3.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqian4.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        }else if (moban == 2){
                            biaoqiantwo0.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqiantwo1.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqiantwo2.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqiantwo3.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        }else if (moban == 3){
                            biaoqianthree.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        }else if (moban == 4){
                            biaoqianfour1.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqianfour2.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqianfour3.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqianfour4.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqianfour5.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                            biaoqianfour6.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        }
//                    }
                    editGrave--;
                    editGrave--;
                }
                break;
            case R.id.main_tab4://粗细
//                if (flag.equals("2")) {
                    if (isBlod) {

                        if (moban == 1){
                            biaoqian1.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqian2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqian3.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqian4.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        }else if (moban == 2){
                            biaoqiantwo0.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqiantwo1.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqiantwo2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqiantwo3.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        }else if (moban == 3){
                            biaoqianthree.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        }else if (moban == 4){
                            biaoqianfour1.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqianfour2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqianfour3.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqianfour4.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqianfour5.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            biaoqianfour6.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        }

                        isBlod = false;
                    } else {
                        if (moban == 1){
                            biaoqian1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqian2.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqian3.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqian4.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        }else if (moban == 2){
                            biaoqiantwo0.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqiantwo1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqiantwo2.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqiantwo3.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        }else if (moban == 3){
                            biaoqianthree.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        }else if (moban == 4){
                            biaoqianfour1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqianfour2.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqianfour3.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqianfour4.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqianfour5.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                            biaoqianfour6.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        }
                        isBlod = true;
//                    }


                }
                break;
            case R.id.main_tab5://二维码
                if (codeBar.getVisibility() == View.VISIBLE) {
                    codeBar.setVisibility(View.GONE);
                } else {
                    codeBar.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.main_tab6:
                Intent intent4 = new Intent(this,SuCaiKuActivity.class);
                intent4.putExtra("sucai","biaoqing");
                startActivityForResult(intent4,1000);
                break;
            case R.id.home_add:

                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView) {
                        arrs.get(i).setEditable(false);
                    }
                }
                BitmapUtil.getInstance().getCutImage(canv,330);//330  220  100
                break;
            case R.id.get_rcode:
                String str = txtUrl.getText().toString();
                Bitmap bitmap = null;
                if (str.length() > 0) {
                    bitmap = QRCodeEncoder.syncEncodeQRCode(str, 350, R.color.black);//二维码
                    if (bitmap != null) {
                        SingleTouchView singleTouchView = new SingleTouchView(BiaoQianActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp(bitmap);
                        canv.addView(singleTouchView);
                        arrs.add(singleTouchView);
                        if (codeBar.getVisibility() == View.VISIBLE) {
                            codeBar.setVisibility(View.INVISIBLE);
                        } else {
                            codeBar.setVisibility(View.VISIBLE);
                        }
                    } else {
                        ToastUtils.showShort("error");
                    }


                } else {
                    ToastUtils.showShort("请输入文字");
                }

                break;
        }
    }


    private void setting() {
        if (wanggeLay.getVisibility() == View.VISIBLE) {
            wanggeLay.setVisibility(View.INVISIBLE);
        } else {
            wanggeLay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000 && null != data) {
            int id = data.getIntExtra("img", 0);
            if (id == R.mipmap.biaoqianzhi_1) {
                moban = 1;
                hideView();
            } else if (id == R.mipmap.biaoqianzhi_2) {
                moban = 2;
                hideView();
            } else if (id == R.mipmap.biaoqianzhi_3) {
                moban = 3;
                hideView();
            } else if (id == R.mipmap.biaoqianzhi_4) {
                moban = 4;
                hideView();
            }else{
                addCusView(id);
            }
        }

    }

    private void  hideView(){
        if (moban == 1){
            biaoqian1Lay.setVisibility(View.VISIBLE);
            biaoqian2Lay.setVisibility(View.GONE);
            biaoqian3Lay.setVisibility(View.GONE);
            biaoqian4Lay.setVisibility(View.GONE);
        }else if (moban == 2){
            biaoqian1Lay.setVisibility(View.GONE);
            biaoqian2Lay.setVisibility(View.VISIBLE);
            biaoqian3Lay.setVisibility(View.GONE);
            biaoqian4Lay.setVisibility(View.GONE);
        }else if (moban == 3){
            biaoqian1Lay.setVisibility(View.GONE);
            biaoqian2Lay.setVisibility(View.GONE);
            biaoqian3Lay.setVisibility(View.VISIBLE);
            biaoqian4Lay.setVisibility(View.GONE);
        }else if (moban == 4){
            biaoqian1Lay.setVisibility(View.GONE);
            biaoqian2Lay.setVisibility(View.GONE);
            biaoqian3Lay.setVisibility(View.GONE);
            biaoqian4Lay.setVisibility(View.VISIBLE);
        }else{
            biaoqian1Lay.setVisibility(View.GONE);
            biaoqian2Lay.setVisibility(View.GONE);
            biaoqian3Lay.setVisibility(View.GONE);
            biaoqian4Lay.setVisibility(View.GONE);
        }

    }

    private ArrayList<SingleTouchView> arrs = new ArrayList<>();
    private ArrayList<String> arr = new ArrayList<>();

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

}