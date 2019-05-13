package com.test.iview.dayin.activity.common;

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
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class XiaoZiActivity extends BaseActivity {
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
    String flag = "";
    @BindView(R.id.canv)
    RelativeLayout canv;

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
    private String[] tab_title = {"大小", "调整", "表情包", "粗细", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_daxiao, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_cuxi, R.drawable.tab_ercode};


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText(R.string.dy_xiaozidayin);


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
        return R.layout.xiaozi_lay;
    }
    private boolean isImg = true;
    private boolean isBlod = false;
    private int editGrave = 0;
    @OnClick({R.id.back,R.id.home_add,R.id.main_tab1,R.id.main_tab2,R.id.main_tab3,R.id.main_tab4,R.id.main_tab5,R.id.get_rcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView){
                        arrs.get(i).setEditable(false);
                    }
                }
                editTxt.setCursorVisible(false);
                BitmapUtil.getInstance().getCutImage(canv,isImg,0,false);

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
                Intent intent = new Intent(this,SuCaiKuActivity.class);
                intent.putExtra("sucai","biaoqing");
                startActivityForResult(intent,1000);
                break;
            case R.id.main_tab4:
                if (isBlod){
                    editTxt.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                    isBlod = false;
                }else{
                    editTxt.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                    isBlod = true;
                }
                break;
            case R.id.main_tab5:
                if (codeBar.getVisibility() == View.VISIBLE){
                    codeBar.setVisibility(View.GONE);
                }else{
                    codeBar.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.get_rcode:
                String str = txtUrl.getText().toString();
                Bitmap bitmap = null;
                if (str.length() > 0){
                    bitmap = QRCodeEncoder.syncEncodeQRCode(str,350,R.color.black);//二维码
                    if (bitmap != null){
                        SingleTouchView singleTouchView = new SingleTouchView(XiaoZiActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp(bitmap);
                        canv.addView(singleTouchView);
                        isImg = true;
                        arrs.add(singleTouchView);
                        if (codeBar.getVisibility() == View.VISIBLE){
                            codeBar.setVisibility(View.INVISIBLE);
                        }else{
                            codeBar.setVisibility(View.VISIBLE);
                        }
                    }else{
                        ToastUtils.showShort("error");
                    }


                }else{
                    ToastUtils.showShort(R.string.input_text);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000 && null != data) {
            int id = data.getIntExtra("img",0);
            isImg = true;
            addCusView(id);
        }

    }
    private ArrayList<SingleTouchView> arrs = new ArrayList<>();
    private ArrayList<String> arr = new ArrayList<>();
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
