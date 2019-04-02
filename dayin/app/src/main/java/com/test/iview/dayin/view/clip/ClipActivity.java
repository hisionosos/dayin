package com.test.iview.dayin.view.clip;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import com.test.iview.dayin.R;
import com.test.iview.dayin.utils.ToastUtils;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClipActivity extends Activity {
    private ClipImageLayout mClipImageLayout;
    private String path;
    private ProgressDialog loadingDialog;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipimage);
        ButterKnife.bind(this);
        //这步必须要加
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("请稍后...");
        path = getIntent().getStringExtra("path");
		if(TextUtils.isEmpty(path)||!(new File(path).exists())){
			ToastUtils.showToast("图片加载失败");
			return;
		}
        bitmap = ImageTools.convertToBitmap(path, 600, 600);
        if (bitmap == null) {
            ToastUtils.showToast("图片加载失败");
            return;
        }
        mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
        mClipImageLayout.setBitmap(bitmap);
        ((Button) findViewById(R.id.id_action_clip)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                loadingDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = mClipImageLayout.clip();
//                        String path = Environment.getExternalStorageDirectory() + "/ClipHeadPhoto/cache/" + System.currentTimeMillis() + ".png";
                        ImageTools.savePhotoToSDCard(bitmap, path);
                        loadingDialog.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("path", path);
                        setResult(5, intent);
                        finish();
                    }
                }).start();
            }
        });
    }


    private Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bm.getHeight();
            targetY = 0;
        } else {
            targetX = bm.getHeight();
            targetY = bm.getWidth();
        }

        final float[] values = new float[9];
        m.getValues(values);

        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];

        m.postTranslate(targetX - x1, targetY - y1);

        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bm1);
        canvas.drawBitmap(bm, m, paint);

        return bm1;
    }

    private int orientationDegree = 0;
    @OnClick({R.id.clipimage_fanhui, R.id.clipimage_xuanzhuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clipimage_fanhui:
                finish();
                break;
            case R.id.clipimage_xuanzhuan:
                if (bitmap != null) {
                    if (orientationDegree==180){
                        orientationDegree = 0;
                        bitmap = ImageTools.convertToBitmap(path, 600, 600);
                        mClipImageLayout.setBitmap(bitmap);
                    }else{
                        mClipImageLayout.setBitmap(adjustPhotoRotation(this.bitmap, orientationDegree+=90));
                    }

                }
                break;
        }
    }
}
