package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class XiaoPiaoActivity extends BaseActivity {
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

    @BindView(R.id.add_item_lay)
    LinearLayout addItemLay;
    @BindView(R.id.xiaopiao_1)
    EditText xiaopiao1;
    @BindView(R.id.xiaopiao_2)
    EditText xiaopiao2;
    @BindView(R.id.xiaopiao_3)
    EditText xiaopiao3;
    @BindView(R.id.good_id)
    TextView goodId;
    @BindView(R.id.xiaopiao_5)
    EditText xiaopiao5;
    @BindView(R.id.xiaopiao_6)
    EditText xiaopiao6;
    @BindView(R.id.biaoqian1_lay)
    LinearLayout biaoqian1Lay;
    @BindView(R.id.main_tab0)
    RadioButton mainTab0;
    @BindView(R.id.min_txt)
    TextView minTxt;
    @BindView(R.id.max_txt)
    TextView maxTxt;
    @BindView(R.id.canv_soll)
    ScrollView canvSoll;


    private int moban = 0;
    private String[] tab_title = {"模板", "大小", "调整", "表情包", "粗细", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_muban, R.drawable.tab_daxiao, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_cuxi, R.drawable.tab_ercode};


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText(getString(R.string.dy_xiaopiaodayin));

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);


    }

    @Override
    public void initData() {
        sizeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

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
        return R.layout.xiaopiao_lay;
    }


    private boolean isBlod = false;
    private int editGrave = 0;

    @OnClick({R.id.back, R.id.main_tab1, R.id.main_tab2, R.id.main_tab3, R.id.main_tab4, R.id.main_tab5, R.id.home_add, R.id.get_rcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1://模板
                Intent intent = new Intent(this, SuCaiKuActivity.class);
                intent.putExtra("sucai", "xiaopiao");
                startActivityForResult(intent, 1000);
                break;
            case R.id.main_tab2://大小
//                setting();
                addGoodView();
                break;
            case R.id.main_tab3://调整
                if (editGrave == 0) {
                    if (flag.equals("2")) {
                        xiaopiao1.setGravity(Gravity.CENTER);
                        xiaopiao2.setGravity(Gravity.CENTER);
                        xiaopiao3.setGravity(Gravity.CENTER);
                        xiaopiao5.setGravity(Gravity.CENTER);
                        xiaopiao6.setGravity(Gravity.CENTER);
                    }
                    editGrave++;
                } else if (editGrave == 1) {
                    if (flag.equals("2")) {
                        xiaopiao1.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        xiaopiao2.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        xiaopiao3.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        xiaopiao5.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                        xiaopiao6.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                    }

                    editGrave++;
                } else if (editGrave == 2) {
                    if (flag.equals("2")) {
                        xiaopiao1.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        xiaopiao2.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        xiaopiao3.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        xiaopiao5.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                        xiaopiao6.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                    }
                    editGrave--;
                    editGrave--;
                }
                break;
            case R.id.main_tab4://粗细
                if (flag.equals("2")) {
                    if (isBlod) {

                        xiaopiao1.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        xiaopiao2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        xiaopiao3.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        xiaopiao5.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        xiaopiao6.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                        isBlod = false;
                    } else {
                        xiaopiao1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        xiaopiao2.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        xiaopiao3.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        xiaopiao5.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        xiaopiao6.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                        isBlod = true;
                    }
                }
                break;
            case R.id.main_tab5://二维码
                if (codeBar.getVisibility() == View.VISIBLE) {
                    codeBar.setVisibility(View.GONE);
                } else {
                    codeBar.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView) {
                        arrs.get(i).setEditable(false);
                    }
                }
                BitmapUtil.getInstance().getBitmapScrollView(canvSoll);
                break;
            case R.id.get_rcode:
                String str = txtUrl.getText().toString();
                Bitmap bitmap = null;
                if (str.length() > 0) {
                    bitmap = QRCodeEncoder.syncEncodeQRCode(str, 350, R.color.black);//二维码
                    if (bitmap != null) {
                        SingleTouchView singleTouchView = new SingleTouchView(XiaoPiaoActivity.this);
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
                    ToastUtils.showShort(R.string.input_text);
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
            if (id == R.mipmap.xiaopiao_1) {
                moban = 1;
            } else if (id == R.mipmap.xiaopiao_1) {
                moban = 2;
            } else {
                addCusView(id);
            }
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


    private void addGoodView() {

//        for (int i = 0; i < size; i++) {
//            TextView title = new TextView(mContext);
//            LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, DimensUtils.dp2px(mContext,20));
//            etParam.setMargins(0,20,0,10);
//            title.setLayoutParams(etParam);
//            // 设置属性
//            title.setBackgroundColor(Color.argb(255, 255, 255, 255));   // #FFFFFFFF
//            title.setGravity(Gravity.LEFT);
//            title.setPadding(DimensUtils.dp2px(mContext,30), 0, DimensUtils.dp2px(mContext,30), 0);
//            title.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
//            title.setTextSize(14);
//            listTxt.add(title);
//            uploadPar.addView(title);
//
//            ProgressBar progressBar = new ProgressBar(mContext,null,android.R.attr.progressBarStyleHorizontal);
//            progressBar.setLayoutParams(etParam);
//            progressBar.setBackground(null);
//            progressBar.setPadding(DimensUtils.dp2px(mContext,30), 0, DimensUtils.dp2px(mContext,30), 0);
//            listpro.add(progressBar);
//            uploadPar.addView(progressBar);
//        }

        LinearLayout.LayoutParams txtLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams editLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams linerLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensUtils.dp2px(this, 40));

        LinearLayout.LayoutParams viewLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensUtils.dp2px(this, 0.5f));

        LinearLayout.LayoutParams headLayParams1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);

        LinearLayout headlinearLayout1 = new LinearLayout(this);
        headlinearLayout1.setLayoutParams(headLayParams1);
        headlinearLayout1.setGravity(Gravity.CENTER);
        headlinearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        headlinearLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));

//        EditText edit1 = new EditText(this);
//        edit1.setTextColor(Color.parseColor("#000000"));
//        edit1.setGravity(Gravity.CENTER);
//        edit1.setTextSize(DimensUtils.sp2px(this, 7));
//        edit1.setBackground(null);
//        edit1.setLayoutParams(txtLayParams);
//        headlinearLayout1.addView(edit1);

        TextView title = new TextView(this);
        title.setTextColor(Color.parseColor("#000000"));
        title.setGravity(Gravity.CENTER);
        title.setTextSize(DimensUtils.sp2px(this, 7));
        title.setText("1");
        title.setLayoutParams(txtLayParams);
        headlinearLayout1.addView(title);

        //总布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(linerLayParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        linearLayout.addView(headlinearLayout1);


        //第二个框布局
        LinearLayout.LayoutParams headLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,2.0f);

        LinearLayout headlinearLayout = new LinearLayout(this);
        headlinearLayout.setLayoutParams(headLayParams);
        headlinearLayout.setGravity(Gravity.CENTER);
        headlinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        headlinearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

        //划线LinearLayout
        LinearLayout.LayoutParams liLayParams = new LinearLayout.LayoutParams(
                DimensUtils.dp2px(this, 0.5f), ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout linLay = new LinearLayout(this);
        linLay.setLayoutParams(liLayParams);
        linLay.setGravity(Gravity.CENTER);
        linLay.setOrientation(LinearLayout.HORIZONTAL);
        linLay.setBackgroundColor(Color.parseColor("#000000"));
        linearLayout.addView(linLay);

        //再添加EditText


        EditText edit = new EditText(this);
        edit.setTextColor(Color.parseColor("#000000"));
        edit.setGravity(Gravity.CENTER);
        edit.setTextSize(DimensUtils.sp2px(this, 7));

        edit.setBackground(null);
        edit.setLayoutParams(editLayParams);
        headlinearLayout.addView(edit);
        linearLayout.addView(headlinearLayout);


        //第三个框布局
        LinearLayout.LayoutParams headLayParams3 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);

        LinearLayout headlinearLayout3 = new LinearLayout(this);
        headlinearLayout3.setLayoutParams(headLayParams3);
        headlinearLayout3.setGravity(Gravity.CENTER);
        headlinearLayout3.setOrientation(LinearLayout.HORIZONTAL);
        headlinearLayout3.setBackgroundColor(Color.parseColor("#FFFFFF"));

        LinearLayout linLay3 = new LinearLayout(this);
        linLay3.setLayoutParams(liLayParams);
        linLay3.setGravity(Gravity.CENTER);
        linLay3.setOrientation(LinearLayout.HORIZONTAL);
        linLay3.setBackgroundColor(Color.parseColor("#000000"));
        linearLayout.addView(linLay3);

        EditText edit3 = new EditText(this);
        edit3.setTextColor(Color.parseColor("#000000"));
        edit3.setGravity(Gravity.CENTER);
        edit3.setTextSize(DimensUtils.sp2px(this, 7));
        edit3.setBackground(null);
        edit3.setLayoutParams(editLayParams);
        headlinearLayout3.addView(edit3);
        linearLayout.addView(headlinearLayout3);


        //第四个框布局
        LinearLayout.LayoutParams headLayParams4 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);

        LinearLayout headlinearLayout4 = new LinearLayout(this);
        headlinearLayout4.setLayoutParams(headLayParams4);
        headlinearLayout4.setGravity(Gravity.CENTER);
        headlinearLayout4.setOrientation(LinearLayout.HORIZONTAL);
        headlinearLayout4.setBackgroundColor(Color.parseColor("#FFFFFF"));

        LinearLayout linLay4 = new LinearLayout(this);
        linLay4.setLayoutParams(liLayParams);
        linLay4.setGravity(Gravity.CENTER);
        linLay4.setOrientation(LinearLayout.HORIZONTAL);
        linLay4.setBackgroundColor(Color.parseColor("#000000"));
        linearLayout.addView(linLay4);

        EditText edit4 = new EditText(this);
        edit4.setTextColor(Color.parseColor("#000000"));
        edit4.setGravity(Gravity.CENTER);
        edit4.setTextSize(DimensUtils.sp2px(this, 7));
        edit4.setBackground(null);
        edit4.setLayoutParams(editLayParams);
        headlinearLayout4.addView(edit4);
        linearLayout.addView(headlinearLayout4);


        //第五个框布局
        LinearLayout.LayoutParams headLayParams5 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);

        LinearLayout headlinearLayout5 = new LinearLayout(this);
        headlinearLayout5.setLayoutParams(headLayParams5);
        headlinearLayout5.setGravity(Gravity.CENTER);
        headlinearLayout5.setOrientation(LinearLayout.HORIZONTAL);
        headlinearLayout5.setBackgroundColor(Color.parseColor("#FFFFFF"));

        LinearLayout linLay5 = new LinearLayout(this);
        linLay5.setLayoutParams(liLayParams);
        linLay5.setGravity(Gravity.CENTER);
        linLay5.setOrientation(LinearLayout.HORIZONTAL);
        linLay5.setBackgroundColor(Color.parseColor("#000000"));
        linearLayout.addView(linLay5);

        EditText edit5 = new EditText(this);
        edit5.setTextColor(Color.parseColor("#000000"));
        edit5.setGravity(Gravity.CENTER);
        edit5.setTextSize(DimensUtils.sp2px(this, 7));
        edit5.setBackground(null);
        edit5.setLayoutParams(editLayParams);
        headlinearLayout5.addView(edit5);
        linearLayout.addView(headlinearLayout5);




        View view = new View(this);
        view.setBackgroundColor(Color.parseColor("#000000"));
        view.setLayoutParams(viewLayParams);
        addItemLay.addView(linearLayout);
        addItemLay.addView(view);
    }

}
