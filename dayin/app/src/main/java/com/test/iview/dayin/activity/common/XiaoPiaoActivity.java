package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;
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
import com.test.iview.dayin.utils.DateUtils;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.text.DecimalFormat;
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
    TextView xiaopiao5;
    @BindView(R.id.xiaopiao_6)
    EditText xiaopiao6;
    @BindView(R.id.biaoqian1_lay)
    LinearLayout biaoqian1Lay;
    @BindView(R.id.biaoqian2_lay)
    LinearLayout biaoqian2Lay;
    @BindView(R.id.main_tab0)
    RadioButton mainTab0;
    @BindView(R.id.min_txt)
    TextView minTxt;
    @BindView(R.id.max_txt)
    TextView maxTxt;
    @BindView(R.id.canv_soll)
    ScrollView canvSoll;
    @BindView(R.id.canv_soll2)
    ScrollView canvSoll2;
    @BindView(R.id.two_add_item_lay)
    LinearLayout twoAddItemLay;
    @BindView(R.id.xiaopiaotwo_1)
    TextView xiaopiaotwo1;
    @BindView(R.id.xiaopiaotwo_2)
    TextView xiaopiaotwo2;
    @BindView(R.id.xiaopiaotwo_3)
    EditText xiaopiaotwo3;
    @BindView(R.id.xiaopiaotwo_4)
    EditText xiaopiaotwo4;

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
        xiaopiaotwo2.setText("日期:" + DateUtils.getNowTime());
        xiaopiaotwo3.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        xiaopiaotwo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (null == s || s.length() == 0){
                    xiaopiaotwo4.setText("");
                }else{
                    double tot = Double.parseDouble(s + "");
                    String ss = xiaopiaotwo1.getText().toString();
                    if (null == ss || ss.length() == 0){
                        ss = "0";
                    }

                    double parseDouble = Double.parseDouble(ss);
                    if (tot - parseDouble >= 0){
                        DecimalFormat df = new DecimalFormat("#.00");
                        xiaopiaotwo4.setText((df.format(tot - parseDouble)) + "");
                    }else{
                        xiaopiaotwo4.setText("");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                if (moban == 1){
                    addGoodView();
                }else if (moban == 2){
                    addTwoView();
                }

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
                canvSoll.setVisibility(View.VISIBLE);
                canvSoll2.setVisibility(View.GONE);
                if (rowCount == 0) {
                    addTitleView();
                }

                if (rowCount == 1) {
                    addGoodView();
                }
            } else if (id == R.mipmap.xiaopiao_2) {
                moban = 2;
                canvSoll2.setVisibility(View.VISIBLE);
                canvSoll.setVisibility(View.GONE);
                if (tworowCount == 0){
                    addTwoView();
                }
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

    private int tworowCount = 0;
    private ArrayList<EditText> tList1 = new ArrayList<>();
    private ArrayList<EditText> tList2 = new ArrayList<>();
    private void addTwoView(){
        tworowCount ++;
        int txtSize = 6;
        int realSize = DimensUtils.dp2px(this, 50);
        int secSize = DimensUtils.dp2px(this, 100);

        LinearLayout.LayoutParams linerLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams editLayParams = new LinearLayout.LayoutParams(
                DimensUtils.dp2px(this, 60), ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);

        //总布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(linerLayParams);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

        EditText edit1 = new EditText(this);
        edit1.setTextColor(Color.parseColor("#000000"));
        edit1.setGravity(Gravity.CENTER);
        edit1.setTextSize(DimensUtils.sp2px(this, txtSize));
        edit1.setBackground(null);
        edit1.setLayoutParams(editLayParams);
        linearLayout.addView(edit1);


        EditText edit2 = new EditText(this);
        final EditText edit3 = new EditText(this);
        edit2.setTextColor(Color.parseColor("#000000"));
        edit2.setGravity(Gravity.CENTER);
        edit2.setTextSize(DimensUtils.sp2px(this, txtSize));
        edit2.setBackground(null);
        edit2.setText("0");
        edit2.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = s + "";
                if (s == null || s.length() == 0) {
                    ss = "0";
                }

                double tot = 0.00;
                for (int i = 0; i < tList1.size(); i++) {
                    String count1 =  tList1.get(i).getText().toString();//数量
                    String price1 =  tList2.get(i).getText().toString();//单价
                    if (null == price1 || price1.length() == 0) {
                        price1 = "0.00";
                    }

                    if (null == count1 || count1.length() == 0) {
                        count1 = "0";
                    }

                    int rowcount = Integer.parseInt(count1);
                    double rowdanjia = Double.parseDouble(price1);


                    tot += rowcount * rowdanjia;
                }
                DecimalFormat df = new DecimalFormat("#.00");
                xiaopiaotwo1.setText(df.format(tot) + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edit2.setLayoutParams(editLayParams);
        linearLayout.addView(edit2);
        tList1.add(edit2);

        edit3.setTextColor(Color.parseColor("#000000"));
        edit3.setGravity(Gravity.CENTER);
        edit3.setTextSize(DimensUtils.sp2px(this, txtSize));
        edit3.setBackground(null);
        edit3.setText("1");
        edit3.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = s + "";
                if (s == null || s.length() == 0) {
                    ss = "0";
                }
                double tot = 0.00;
                for (int i = 0; i < tList1.size(); i++) {
                    String count1 =  tList1.get(i).getText().toString();//数量
                    String price1 =  tList2.get(i).getText().toString();//单价
                    if (null == price1 || price1.length() == 0) {
                        price1 = "0.00";
                    }

                    if (null == count1 || count1.length() == 0) {
                        count1 = "0";
                    }

                    int rowcount = Integer.parseInt(count1);
                    double rowdanjia = Double.parseDouble(price1);


                    tot += rowcount * rowdanjia;
                }
                DecimalFormat df = new DecimalFormat("#.00");
                xiaopiaotwo1.setText(df.format(tot) + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit3.setLayoutParams(editLayParams);
        linearLayout.addView(edit3);
        tList2.add(edit3);
        twoAddItemLay.addView(linearLayout);
    }


    private void addTitleView() {
        int dimen = getResources().getDimensionPixelSize(R.dimen.dp_40);
        int txtSize = 6;
        int realSize = DimensUtils.dp2px(this, 50);
        int secSize = DimensUtils.dp2px(this, 100);
        LinearLayout.LayoutParams linerLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensUtils.dp2px(this, 40));

        LinearLayout.LayoutParams viewLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensUtils.dp2px(this, 0.5f));


        //1
        LinearLayout.LayoutParams headLayParams1 = new LinearLayout.LayoutParams(
                DimensUtils.dp2px(this, 40), ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout headlinearLayout1 = new LinearLayout(this);
        headlinearLayout1.setLayoutParams(headLayParams1);
        headlinearLayout1.setGravity(Gravity.CENTER);
        headlinearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        headlinearLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));


        LinearLayout.LayoutParams txtLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView title = new TextView(this);
        title.setTextColor(Color.parseColor("#000000"));
        title.setGravity(Gravity.CENTER);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        title.setText("序号");
        title.setLayoutParams(txtLayParams);
        headlinearLayout1.addView(title);
        list1.add(title);
        //总布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(linerLayParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        linearLayout.addView(headlinearLayout1);


        //第二个框布局
        LinearLayout.LayoutParams headLayParams = new LinearLayout.LayoutParams(
                secSize, ViewGroup.LayoutParams.MATCH_PARENT);

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

        TextView title1 = new TextView(this);
        title1.setTextColor(Color.parseColor("#000000"));
        title1.setGravity(Gravity.CENTER);
        title1.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        title1.setText("商品名称");
        title1.setLayoutParams(txtLayParams);
        headlinearLayout.addView(title1);
        linearLayout.addView(headlinearLayout);
        list2.add(title1);

        //第三个框布局
        LinearLayout.LayoutParams headLayParams3 = new LinearLayout.LayoutParams(
                realSize, ViewGroup.LayoutParams.MATCH_PARENT);

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

        TextView title2 = new TextView(this);
        title2.setTextColor(Color.parseColor("#000000"));
        title2.setGravity(Gravity.CENTER);
        title2.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        title2.setText("数量");
        title2.setLayoutParams(txtLayParams);
        headlinearLayout3.addView(title2);
        linearLayout.addView(headlinearLayout3);
        list3.add(title2);

        //第四个框布局
        LinearLayout.LayoutParams headLayParams4 = new LinearLayout.LayoutParams(
                realSize, ViewGroup.LayoutParams.MATCH_PARENT);

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

        TextView title3 = new TextView(this);
        title3.setTextColor(Color.parseColor("#000000"));
        title3.setGravity(Gravity.CENTER);
        title3.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        title3.setText("单价");
        title3.setLayoutParams(txtLayParams);
        headlinearLayout4.addView(title3);
        linearLayout.addView(headlinearLayout4);
        list4.add(title3);

        //第五个框布局
        LinearLayout.LayoutParams headLayParams5 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

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

        TextView title4 = new TextView(this);
        title4.setTextColor(Color.parseColor("#000000"));
        title4.setGravity(Gravity.CENTER);
        title4.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        title4.setText("金额");
        title4.setLayoutParams(txtLayParams);
        headlinearLayout5.addView(title4);
        linearLayout.addView(headlinearLayout5);
        list5.add(title4);


        View view = new View(this);
        view.setBackgroundColor(Color.parseColor("#000000"));
        view.setLayoutParams(viewLayParams);
        addItemLay.addView(linearLayout);
        addItemLay.addView(view);

        rowCount++;
    }


    private int rowCount = 0;
    private ArrayList<TextView> list1 = new ArrayList();
    private ArrayList list2 = new ArrayList();
    private ArrayList list3 = new ArrayList();
    private ArrayList list4 = new ArrayList();
    private ArrayList list5 = new ArrayList();

    private void addGoodView() {
        int txtSize = 6;
        int dimen = getResources().getDimensionPixelSize(R.dimen.dp_40);
        int realSize = DimensUtils.dp2px(this, 50);
        int secSize = DimensUtils.dp2px(this, 100);

        LinearLayout.LayoutParams txtLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams editLayParams = new LinearLayout.LayoutParams(
                DimensUtils.dp2px(this, 60), ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams linerLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//DimensUtils.dp2px(this, 40));

        LinearLayout.LayoutParams viewLayParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DimensUtils.dp2px(this, 0.5f));

        LinearLayout.LayoutParams headLayParams1 = new LinearLayout.LayoutParams(
                DimensUtils.dp2px(this, 40), ViewGroup.LayoutParams.MATCH_PARENT);


        LinearLayout headlinearLayout1 = new LinearLayout(this);
        headlinearLayout1.setLayoutParams(headLayParams1);
        headlinearLayout1.setGravity(Gravity.CENTER);
        headlinearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        headlinearLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));


        TextView title = new TextView(this);
        title.setTextColor(Color.parseColor("#000000"));
        title.setGravity(Gravity.CENTER);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        title.setText("1");
        title.setLayoutParams(txtLayParams);
        headlinearLayout1.addView(title);

        list1.add(title);
        //总布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(linerLayParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        linearLayout.addView(headlinearLayout1);


        //第二个框布局
        LinearLayout.LayoutParams headLayParams = new LinearLayout.LayoutParams(
                secSize, ViewGroup.LayoutParams.MATCH_PARENT);

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
        edit.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        edit.setBackground(null);
        edit.setLayoutParams(editLayParams);
        headlinearLayout.addView(edit);
        linearLayout.addView(headlinearLayout);
        list2.add(edit);

        //第三个框布局
        LinearLayout.LayoutParams headLayParams3 = new LinearLayout.LayoutParams(
                realSize, ViewGroup.LayoutParams.MATCH_PARENT);

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

        final EditText edit3 = new EditText(this);
        final EditText edit4 = new EditText(this);
        final EditText edit5 = new EditText(this);
        edit3.setTextColor(Color.parseColor("#000000"));
        edit3.setGravity(Gravity.CENTER);
        edit3.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        edit3.setText("0");
        edit3.setBackground(null);
        edit3.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = s + "";
                if (s == null || s.length() == 0) {
                    ss = "0";
                }
                int rowcount = Integer.parseInt(ss);
                double rowdanjia = Double.parseDouble(edit4.getText().toString());
                DecimalFormat df = new DecimalFormat("#.00");
                edit5.setText(df.format(rowcount * rowdanjia) + "");
                double tot = 0.00;
                for (int i = 1; i < list1.size(); i++) {
                    String total = ((EditText) list5.get(i)).getText().toString();
                    if (null == total || total.length() == 0) {
                        total = "0.00";
                    }
                    tot += Double.parseDouble(total);
                }
                xiaopiao5.setText(df.format(tot) + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit3.setLayoutParams(editLayParams);
        headlinearLayout3.addView(edit3);
        linearLayout.addView(headlinearLayout3);
        list3.add(edit3);

        //第四个框布局
        LinearLayout.LayoutParams headLayParams4 = new LinearLayout.LayoutParams(
                realSize, ViewGroup.LayoutParams.MATCH_PARENT);

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


        edit4.setTextColor(Color.parseColor("#000000"));
        edit4.setGravity(Gravity.CENTER);
        edit4.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        edit4.setBackground(null);
        edit4.setText("1");
        edit4.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = s + "";
                if (s == null || s.length() == 0) {
                    ss = "0";
                }
                double rowdanjia = Double.parseDouble(ss);
                int rowcount = Integer.parseInt(edit3.getText().toString());
                DecimalFormat df = new DecimalFormat("#.00");
                edit5.setText(df.format(rowcount * rowdanjia) + "");

                double tot = 0.00;
                for (int i = 1; i < list1.size(); i++) {
                    String total = ((EditText) list5.get(i)).getText().toString();
                    if (null == total || total.length() == 0) {
                        total = "0.00";
                    }
                    tot += Double.parseDouble(total);
                }
                xiaopiao5.setText(df.format(tot) + "");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit4.setLayoutParams(editLayParams);
        headlinearLayout4.addView(edit4);
        linearLayout.addView(headlinearLayout4);
        list4.add(edit4);

        //第五个框布局
        LinearLayout.LayoutParams headLayParams5 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

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


        edit5.setTextColor(Color.parseColor("#000000"));
        edit5.setGravity(Gravity.CENTER);
        edit5.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimen);
        edit5.setBackground(null);
        edit5.setEnabled(false);
        edit5.setText("1.00");
        edit5.setLayoutParams(editLayParams);
        headlinearLayout5.addView(edit5);
        linearLayout.addView(headlinearLayout5);
        list5.add(edit5);


        View view = new View(this);
        view.setBackgroundColor(Color.parseColor("#000000"));
        view.setLayoutParams(viewLayParams);
        addItemLay.addView(linearLayout);
        addItemLay.addView(view);

        //序号显示
        list1.get(rowCount).setText(rowCount + "");
        rowCount++;
    }

}
