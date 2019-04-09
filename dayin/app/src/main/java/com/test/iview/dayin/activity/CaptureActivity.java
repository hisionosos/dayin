package com.test.iview.dayin.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.test.iview.dayin.R;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.Hashtable;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText("二维码打印");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.capture_lay;
    }



    @OnClick({R.id.back, R.id.home_add, R.id.get_rcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.home_add:

                break;
            case R.id.get_rcode:
                String str = txtUrl.getText().toString();
                if (str.length() > 0){

//                    Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(str,350,R.color.black);//二维码
                        Bitmap bitmap = QRCodeEncoder.syncEncodeBarcode(str,350,200,20);//条形码
                    SingleTouchView singleTouchView = new SingleTouchView(CaptureActivity.this);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    singleTouchView.setLayoutParams(layoutParams);
                    singleTouchView.setImageBitamp(bitmap);
                    canv.addView(singleTouchView);


                    QRCodeEncoder.syncEncodeBarcode(str,350,200,20);
                }else{
                    ToastUtils.showShort("请输入文字");
                }

                break;
        }
    }


}
