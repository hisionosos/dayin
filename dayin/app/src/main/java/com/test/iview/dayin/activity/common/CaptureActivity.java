package com.test.iview.dayin.activity.common;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class CaptureActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.get_rcode)
    Button getRcode;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.txt_url)
    EditText txtUrl;

    String flag = "";

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        if (flag.equals("1")){
            getRcode.setText(getString(R.string.to_qcode));
            commonTitle.setText(getString(R.string.dy_toqcode));
        }else{
            getRcode.setText(R.string.to_tiaoxingma);
            commonTitle.setText(R.string.dy_tiaoxingmadayin);
            txtUrl.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        }

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);

    }

    @Override
    public void initData() {
        canv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtil.getInstance().cannelEdit(arrs,null,false);
            }
        });

    }

    @Override
    public int initLayout() {
        return R.layout.capture_lay;
    }

    private ArrayList<SingleTouchView> arrs = new ArrayList<>();

    @OnClick({R.id.back, R.id.home_add, R.id.get_rcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.home_add:

                BitmapUtil.getInstance().cannelEdit(arrs,null,false);
                BitmapUtil.getInstance().showBitmap(canv,false,0,false);
//                BitmapUtil.getInstance().cannelEdit(arrs,null,true);

                break;
            case R.id.get_rcode:
                String str = txtUrl.getText().toString();
                Bitmap bitmap = null;
                if (str.length() > 0){
                    if (flag.equals("1")){
                        bitmap = QRCodeEncoder.syncEncodeQRCode(str,350, Color.parseColor("#000000"));//二维码
                    }else if (flag.equals("2")){
                        bitmap = QRCodeEncoder.syncEncodeBarcode(str,350,200,20);//条形码
                    }

                    if (bitmap != null){
                        SingleTouchView singleTouchView = new SingleTouchView(CaptureActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp(bitmap);
                        canv.addView(singleTouchView);
                        arrs.add(singleTouchView);
                    }else{
                        ToastUtils.showShort("error");
                    }


                }else{
                    ToastUtils.showShort("请输入文字");
                }

                break;
        }
    }


}
