package com.test.iview.dayin.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.BlueSAPI;
import com.test.iview.dayin.utils.PrinterImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowBitmapActivity extends BaseActivity {
    @BindView(R.id.show_img)
    ImageView showImg;
    @BindView(R.id.print_btn)
    Button printBtn;


    String path;
    boolean isImg = false;
    boolean isBiaoqian = false;
    int h = 0;
    Bitmap bitmap = null;
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
         path = getIntent().getStringExtra("path");
         isImg = getIntent().getBooleanExtra("isImg", false);
         isBiaoqian = getIntent().getBooleanExtra("isBiaoqian", false);
         h = getIntent().getIntExtra("h", 0);

        if (null != path && path.length() > 0){
//            if (h > 0){
//                Bitmap bit = BitmapUtil.choosePath(path);
//                bitmap = PrinterImageUtils.resizeImage(bit,384,h);
//                showImg.setImageBitmap(bitmap);
//            }else{
                BitmapUtil.createScaledBitmap(this, path, 384, new BitmapUtil.MyCallback() {
                    @Override
                    public void onPrepare() {
                        showLoadingDialog();
                    }

                    @Override
                    public void onSucceed(Object object) {
//                    bitmap = PrinterImageUtils.convertGreyImgByFloyd(PrinterImageUtils.toGrayScale((Bitmap)object));
                        if (h > 0){
//                            bitmap = (Bitmap)object;
                            bitmap = PrinterImageUtils.convertToBlackWhitebaioge((Bitmap)object,384);
                            Log.e("saize1",h+ "");
                            BitmapUtil.getInstance().savePicture(bitmap,System.currentTimeMillis() + "_logo.png");
                        }else{
                            if (isImg){
                                bitmap = PrinterImageUtils.imageFloydSteinberg(PrinterImageUtils.convertToBlackWhite((Bitmap)object,384));
                            }else{
                                bitmap = PrinterImageUtils.convertToBlackWhite((Bitmap)object,384);
                            }
                        }



//                    bitmap = PrinterImageUtils.imageFloydSteinberg(PrinterImageUtils.toGrayScale((Bitmap)object));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLaodingDialog();
                                showImg.setImageBitmap(bitmap);
                            }
                        });


                    }

                    @Override
                    public void onError() {
                        hideLaodingDialog();
                    }
                });
//            }

        }
    }

    @Override
    public int initLayout() {
        return R.layout.show_bitmap;
    }


    @OnClick({ R.id.print_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.print_btn:
                boolean b = BlueSAPI.getInstance().isConnect();
                if (!b){
                    startActivity(new Intent(this, PrintActivity.class));
                    return;
                }

                BlueSAPI.getInstance().printContent(bitmap,5,isImg,h,isBiaoqian);
                break;
        }
    }
}
