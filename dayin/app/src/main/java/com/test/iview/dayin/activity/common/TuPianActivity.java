package com.test.iview.dayin.activity.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
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
import com.test.iview.dayin.activity.PrintActivity;
import com.test.iview.dayin.activity.ShowBitmapActivity;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.BlueSAPI;
import com.test.iview.dayin.view.SingleTouchView;
import com.test.iview.dayin.view.imagecut.IMGEditActivity;
import com.test.iview.dayin.view.imagecut.IMGGalleryActivity;
import com.test.iview.dayin.view.imagecut.MyImageCutActivity;
import com.test.iview.dayin.view.imagecut.core.IMGMode;
import com.test.iview.dayin.view.imagecut.core.IMGPath;
import com.test.iview.dayin.view.imagecut.core.IMGText;
import com.test.iview.dayin.view.imagecut.core.file.IMGAssetFileDecoder;
import com.test.iview.dayin.view.imagecut.core.file.IMGDecoder;
import com.test.iview.dayin.view.imagecut.core.file.IMGFileDecoder;
import com.test.iview.dayin.view.imagecut.core.util.IMGUtils;
import com.test.iview.dayin.view.imagecut.gallery.model.IMGChooseMode;
import com.test.iview.dayin.view.imagecut.gallery.model.IMGImageInfo;
import com.test.iview.dayin.view.imagecut.view.IMGView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class TuPianActivity extends MyImageCutActivity {
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
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
//    @BindView(R.id.main_tab2)
//    RadioButton mainTab2;
    @BindView(R.id.main_tab3)
    RadioButton mainTab3;
    @BindView(R.id.main_tab4)
    RadioButton mainTab4;
    @BindView(R.id.wangge_lay)
    LinearLayout wanggeLay;

    @BindView(R.id.wangge_lay1)
    LinearLayout wanggeLay1;

    @BindView(R.id.wangge_lay2)
    LinearLayout wanggeLay2;
    @BindView(R.id.undo_lay)
    LinearLayout undoLay;
    @BindView(R.id.size_seek)
    SeekBar sizeSeek;
    @BindView(R.id.size_seek1)
    SeekBar sizeSeek1;
    @BindView(R.id.size_seek2)
    SeekBar sizeSeek2;
    @BindView(R.id.pain_1)
    LinearLayout pain1;
    @BindView(R.id.pain_2)
    LinearLayout pain2;
    @BindView(R.id.pain_3)
    LinearLayout pain3;
    @BindView(R.id.pain_4)
    LinearLayout pain4;
    @BindView(R.id.pain_5)
    LinearLayout pain5;
    @BindView(R.id.image_canvas)
    IMGView imageCanvas;


//    @Override
//    public int initLayout() {
//        return R.layout.tupian_lay;
//    }

    private float painSize = 10f;
    @OnClick({R.id.back,R.id.main_tab1, R.id.home_add,R.id.main_tab5,R.id.pain_1,R.id.pain_2,R.id.pain_3,R.id.pain_4,R.id.pain_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
                startActivityForResult(IMGGalleryActivity.newIntent(this, new IMGChooseMode.Builder()
                                .setSingleChoose(true)
                                .build()),
                        4000);

                break;

            case R.id.main_tab5:
                setting();
                break;
//            case R.id.main_tab4:
//                Intent intent4 = new Intent(this,SuCaiKuActivity.class);
//                intent4.putExtra("sucai","biaoqing");
//                startActivityForResult(intent4,1000);
//                break;
            case R.id.home_add:
                BitmapUtil.getInstance().cannelEdit(arrs,null,false);
//                BitmapUtil.getInstance().getCutImage(imageCanvas,true,0,false);

                final Bitmap bitmap = mImgView.saveBitmap();
                final ProgressDialog pd = new ProgressDialog(this);
                pd.setTitle(this.getString(R.string.dy_tips));
                pd.setMessage("请稍候……");
                pd.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        final String path = BitmapUtil.getInstance().savePicture(bitmap,System.currentTimeMillis() + "_logo.png");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(TuPianActivity.this, ShowBitmapActivity.class);
                                intent.putExtra("path",path);
                                intent.putExtra("isImg",true);
                                intent.putExtra("h",0);
                                intent.putExtra("isBiaoqian",false);
                                startActivity(intent);
                                if (null != pd){
                                    pd.dismiss();
                                }

                            }
                        });
                    }
                }).start();

//                try {
//                    new Handler().postDelayed(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            BlueSAPI.getInstance().printContent(null,TuPianActivity.this,bitmap,5,true,0,false);
//                        }
//                    },500);
//
//                } catch (Exception e) {
//
//                }


//                BitmapUtil.getInstance().cannelEdit(arrs,null,true);
                break;

            case R.id.pain_1:
                painSize = 3f;
                wanggeLay2.setVisibility(View.GONE);
                IMGPath.BASE_DOODLE_WIDTH = painSize;
                mImgView.getmDoodlePaint().setStrokeWidth(painSize);
                onModeClick(IMGMode.DOODLE);

                break;

            case R.id.pain_2:
                painSize = 5f;
                wanggeLay2.setVisibility(View.GONE);
                IMGPath.BASE_DOODLE_WIDTH = painSize;
                mImgView.getmDoodlePaint().setStrokeWidth(painSize);
                onModeClick(IMGMode.DOODLE);
                break;

            case R.id.pain_3:
                painSize = 10f;
                wanggeLay2.setVisibility(View.GONE);
                IMGPath.BASE_DOODLE_WIDTH = painSize;
                mImgView.getmDoodlePaint().setStrokeWidth(painSize);
                onModeClick(IMGMode.DOODLE);
                break;

            case R.id.pain_4:
                painSize = 15f;
                wanggeLay2.setVisibility(View.GONE);
                IMGPath.BASE_DOODLE_WIDTH = painSize;
                mImgView.getmDoodlePaint().setStrokeWidth(painSize);
                onModeClick(IMGMode.DOODLE);
                break;

            case R.id.pain_5:
                painSize = 20f;
                wanggeLay2.setVisibility(View.GONE);
                IMGPath.BASE_DOODLE_WIDTH = painSize;
                mImgView.getmDoodlePaint().setStrokeWidth(painSize);
                onModeClick(IMGMode.DOODLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            int id = data.getIntExtra("img",0);
            addCusView(id);
        }
        if (requestCode == 4000) {
            onChooseImages(IMGGalleryActivity.getImageInfos(data));
        }

        if (resultCode == RESULT_OK && requestCode == 3000){
            Bitmap bitmap = MyApplication.mCache.getAsBitmap("cut_img");
            if (null != bitmap){
                SingleTouchView singleTouchView = new SingleTouchView(this);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                singleTouchView.setLayoutParams(layoutParams);
                singleTouchView.setImageBitamp(bitmap);
                canv.addView(singleTouchView);
                arrs.add(singleTouchView);
            }
        }
    }

    private Uri uri;
    private String imgPath;
    private void onChooseImages(List<IMGImageInfo> images) {
        if (null != images && images.size() > 0){
            IMGImageInfo image = images.get(0);

            if (image != null) {
                final String fileName = System.currentTimeMillis() + "_screen.png";
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName;

                uri = image.getUri();
                imgPath = filePath;

                mImgView.setImageBitmap(getBit());
                curBit = getBit();
//                File mImageFile = new File(getCacheDir(), UUID.randomUUID().toString() + ".jpg");
//                Intent intent = new Intent(this, IMGEditActivity.class);
//                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_URI, image.getUri());
//                intent.putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH, filePath);
//                startActivityForResult(intent,3000);

            }
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

    private void addImage(Uri uri){
        showLoadingDialog();
        BitmapUtil.createScaledBitmap(this,uri,350, new BitmapUtil.MyCallback() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onSucceed(final Object object) {
                hideLaodingDialog();
                TuPianActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SingleTouchView singleTouchView = new SingleTouchView(TuPianActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp((Bitmap) object);
                        canv.addView(singleTouchView);
                        arrs.add(singleTouchView);
                    }
                });

            }

            @Override
            public void onError() {
                hideLaodingDialog();
            }
        });
    }

    private int mScreenWidth;
    private void setting() {
        if (wanggeLay1.getVisibility() == View.VISIBLE){
            wanggeLay1.setVisibility(View.INVISIBLE);
        }else{
            wanggeLay1.setVisibility(View.VISIBLE);
        }
    }








    private Bitmap curBit;
    private static final int MAX_WIDTH = 1024;

    private static final int MAX_HEIGHT = 1024;

    public static final String EXTRA_IMAGE_URI = "IMAGE_URI";

    public static final String EXTRA_IMAGE_SAVE_PATH = "IMAGE_SAVE_PATH";


    private float mhue = 0;
    private float msaturation = 0;
    @Override
    public void onCreated() {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        commonTitle.setText(R.string.dy_photodayin);

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);


        sizeSeek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mhue = (progress-127)*1.0f/255*180;

                if (null != curBit){
                    Bitmap b = BitmapUtil.imageoperation(curBit,mhue,msaturation,0);
                    mImgView.setImageBitmap(b);
//                    mImgView.doliangdu();

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sizeSeek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                msaturation = progress*1.0f/127;
                if (null != curBit){
                    Bitmap b = BitmapUtil.imageoperation(curBit,mhue,msaturation,0);
                    mImgView.setImageBitmap(b);
//                    mImgView.doliangdu();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private Bitmap getBit(){
        IMGDecoder decoder = null;

        String path = uri.getPath();
        if (!TextUtils.isEmpty(path)) {
            switch (uri.getScheme()) {
                case "asset":
                    decoder = new IMGAssetFileDecoder(this, uri);
                    break;
                case "file":
                    decoder = new IMGFileDecoder(uri);
                    break;
            }
        }

        if (decoder == null) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        options.inJustDecodeBounds = true;

        decoder.decode(options);

        if (options.outWidth > MAX_WIDTH) {
            options.inSampleSize = IMGUtils.inSampleSize(Math.round(1f * options.outWidth / MAX_WIDTH));
        }

        if (options.outHeight > MAX_HEIGHT) {
            options.inSampleSize = Math.max(options.inSampleSize,
                    IMGUtils.inSampleSize(Math.round(1f * options.outHeight / MAX_HEIGHT)));
        }

        options.inJustDecodeBounds = false;

        Bitmap bitmap = decoder.decode(options);
        if (bitmap == null) {
            return null;
        }

        return bitmap;
    }



    @Override
    public void onText(IMGText text) {
        mImgView.addStickerText(text);
    }


    @Override
    public void setPainSize() {
        wanggeLay2.setVisibility(View.VISIBLE);
    }


    @Override
    public void onModeClick(IMGMode mode) {
        IMGMode cm = mImgView.getMode();
        if (mode == IMGMode.DOODLE){
            undoLay.setVisibility(View.VISIBLE);
        }else{
            undoLay.setVisibility(View.GONE);
        }

        if (cm == mode && mode != IMGMode.DOODLE) {
            mode = IMGMode.NONE;
            undoLay.setVisibility(View.GONE);
        }

        mImgView.setMode(mode);
        updateModeUI();

        if (mode == IMGMode.CLIP) {
            setOpDisplay(OP_CLIP);
        }
    }

    @Override
    public void onUndoClick() {
        IMGMode mode = mImgView.getMode();
        if (mode == IMGMode.DOODLE) {
            mImgView.undoDoodle();
        } else if (mode == IMGMode.MOSAIC) {
            mImgView.undoMosaic();
        }
    }

    @Override
    public void onCancelClick() {
        finish();
    }



    @Override
    public void onDoneClick() {
        String path = getIntent().getStringExtra(EXTRA_IMAGE_SAVE_PATH);
        if (!TextUtils.isEmpty(path)) {
            Bitmap bitmap = mImgView.saveBitmap();
            if (bitmap != null) {
                MyApplication.mCache.put("cut_img",bitmap);
//                BlueSAPI.getInstance().printContent(getApplicationContext(),bitmap,5);
//                FileOutputStream fout = null;
//                try {
//                    fout = new FileOutputStream(path);
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fout);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (fout != null) {
//                        try {
//                            fout.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
                setResult(RESULT_OK);
                finish();
                return;
            }
        }
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onCancelClipClick() {
        mImgView.cancelClip();
        setOpDisplay(mImgView.getMode() == IMGMode.CLIP ? OP_CLIP : OP_NORMAL);
    }

    @Override
    public void onDoneClipClick() {
        mImgView.doClip();
        setOpDisplay(mImgView.getMode() == IMGMode.CLIP ? OP_CLIP : OP_NORMAL);
    }

    @Override
    public void onResetClipClick() {
        mImgView.resetClip();
    }

    @Override
    public void onRotateClipClick() {
        mImgView.doRotate();
    }

    @Override
    public void onColorChanged(int checkedColor) {
        mImgView.setPenColor(checkedColor);
    }


}
