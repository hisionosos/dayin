package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;
import com.xiaopo.flying.sticker.BitmapStickerIcon;
import com.xiaopo.flying.sticker.DeleteIconEvent;
import com.xiaopo.flying.sticker.FlipHorizontallyEvent;
import com.xiaopo.flying.sticker.Sticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;
import com.xiaopo.flying.sticker.ZoomIconEvent;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class HengFuActivity extends BaseActivity{
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
    EditText editTxt;@BindView(R.id.canv_soll3)
    ScrollView canvSoll3;

    @BindView(R.id.get_txt)
    Button getTxt;
    @BindView(R.id.canv)
    StickerView canv;
    @BindView(R.id.canv_lay)
    LinearLayout canvLay;
    @BindView(R.id.code_bar)
    RelativeLayout codeBar;
    @BindView(R.id.get_rcode)
    Button getRcode;
    @BindView(R.id.txt_url)
    EditText txtUrl;
    String flag = "";
//    private String[] tab_title = {getString(R.string.dy_xuanzhuan),getString(R.string.dy_ziti),getString(R.string.dy_photo), getString(R.string.dy_biaoqing),  getString(R.string.dy_erweima),};
    private int[] tab_imgs = { R.drawable.tab_xuanzhuan, R.drawable.tab_ziti, R.drawable.tab_tupian, R.drawable.tab_biaoqing, R.drawable.tab_ercode};

    private String TAG = "HengFuActivity";
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText(R.string.dy_hengfu);
    }

    @Override
    public void initData() {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);

        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_close_white_18dp),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());

        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_scale_white_18dp),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());

        BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_flip_white_18dp),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());


        canv.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon));

        canv.setBackgroundColor(Color.WHITE);
        canv.setLocked(false);
        canv.setConstrained(true);
        canv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canv.setShowIcon(false);
                canv.setShowBorder(false);
                Log.e("canvvv","ok");
            }
        });
        canv.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                //stickerView.removeAllSticker();
                Log.d(TAG, "onStickerClicked");
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDeleted");
                isexsit = false;
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDragFinished");
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerTouchedDown");
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerZoomFinished");
            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerFlipped");
            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                Log.d(TAG, "onDoubleTapped: double tap will be with two click");
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000 && null != data) {
            int id = data.getIntExtra("img",0);
            addCusView(id);
        }
        if (requestCode == CameraUtils.CODE_ALBUM_CHOOSE && null != data) {
//              int s = BitmapUtil.getBitmapBytes(MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData()));
            addImage(data.getData());
        }

    }


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
                HengFuActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SingleTouchView singleTouchView = new SingleTouchView(HengFuActivity.this);
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

    @Override
    public int initLayout() {
        return R.layout.hengfu_lay;
    }
    TextSticker textSticker;
    String txt;
    private boolean isBold = false;
    private boolean isheng = false;

    @OnClick({R.id.back,R.id.get_txt,R.id.home_add,R.id.main_tab2,R.id.main_tab3,R.id.main_tab4,R.id.main_tab5,R.id.get_rcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab3:

                CameraUtils.albumChoose(this,null);
                break;
            case R.id.main_tab4:
                Intent intent = new Intent(this,SuCaiKuActivity.class);
                intent.putExtra("sucai","biaoqing");
                startActivityForResult(intent,1000);
                break;
            case R.id.main_tab5:
                if (codeBar.getVisibility() == View.VISIBLE){
                    codeBar.setVisibility(View.GONE);
                }else{
                    codeBar.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.main_tab2:
                txt = editTxt.getText().toString();
                if (isexsit){
                    return;
                }

                if (txt != null && txt.length() > 0) {
                    if (isheng){
                        isheng = false;
                        canvLay.removeAllViews();
                        LinearLayout.LayoutParams txtLayParams = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        for (int i = 0; i < txt.length(); i++) {
                            TextView title1 = new TextView(this);
                            title1.setTextColor(Color.parseColor("#000000"));
                            title1.setLayoutParams(txtLayParams);
                            title1.setLineSpacing(10,1);
                            title1.setLetterSpacing(0.05f);
                            title1.setText(txt.substring(i, i + 1));
                            title1.setGravity(Gravity.CENTER);
                            title1.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.dp_500));
//                            title1.setRotation(90);
                            canvLay.addView(title1);
                        }
                    }else{
                        isheng = true;
                        canvLay.removeAllViews();
                        LinearLayout.LayoutParams txtLayParams = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        for (int i = 0; i < txt.length(); i++) {
                            TextView title1 = new TextView(this);
                            title1.setTextColor(Color.parseColor("#000000"));
                            title1.setLayoutParams(txtLayParams);
                            title1.setText(txt.substring(i, i + 1));
                            title1.setGravity(Gravity.CENTER);
                            title1.setLineSpacing(10,1);
                            title1.setLetterSpacing(0.05f);
                            title1.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.dp_500));
                            title1.setRotation(90);
                            canvLay.addView(title1);
                        }
                    }

                }

//                if (isexsit){
////                    canv.remove(textSticker);
//                    if (isBold){
//                        isBold = false;
//                        textSticker.setTypeface(Typeface.DEFAULT);
//                    }else{
//                        isBold = true;
//                        textSticker.setTypeface(Typeface.DEFAULT_BOLD);
//                    }
//
//                    canv.invalidate();
////                    textSticker =  new TextSticker(getApplicationContext())
////                            .setText(txt)
////                            .setTypeface(Typeface.create("宋体",Typeface.BOLD))
////                            .setMaxTextSize(60)
////                            .resizeText();
////                    addCusView();
//                }else{
//                    ToastUtils.showShort("请输入文字");
//                }

                break;
            case R.id.get_txt:
                txt = editTxt.getText().toString();
                if (isexsit){
                    return;
                }

                if (txt != null && txt.length() > 0){
                    canvLay.removeAllViews();
                    LinearLayout.LayoutParams txtLayParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    for (int i = 0; i < txt.length(); i++) {
                        TextView title1 = new TextView(this);
                        title1.setTextColor(Color.parseColor("#000000"));
                        title1.setLayoutParams(txtLayParams);
                        title1.setText(txt.substring(i, i+1));
                        title1.setGravity(Gravity.CENTER);
                        title1.setLineSpacing(10,1);
                        title1.setLetterSpacing(0.05f);
                        title1.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.dp_500));
//                        title1.setRotation(90);
                        canvLay.addView(title1);
                    }
//                    ttt.setText(txt);
//                    vTxt.setText(txt);
//                    ttt.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//                    ttt.setRotation(90);
//                    LinearLayout.LayoutParams txtLayParams = new LinearLayout.LayoutParams(
//                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                    TextView title1 = new TextView(this);
//                    title1.setTextColor(Color.parseColor("#000000"));
//                    title1.setGravity(Gravity.CENTER);
//                    title1.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.dp_40));
//                    title1.setText(txt);
//                    title1.setLayoutParams(txtLayParams);
//                    canvLay.addView();
//                    textSticker =  new TextSticker(getApplicationContext())
//                            .setText(txt)
//                            .setTypeface(Typeface.DEFAULT)
//                            .setMaxTextSize(60)
//                            .resizeText();
//                    addCusView();
                }else{
                    ToastUtils.showShort("请输入文字");
                }

                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView){
                        arrs.get(i).setEditable(false);
                    }
                }
                editTxt.setCursorVisible(false);
//                BitmapUtil.getInstance().getCutImage(canv,0);
                BitmapUtil.getInstance().getBitmapScrollView(canvSoll3,true,0);
                break;
            case R.id.get_rcode:
                String str = txtUrl.getText().toString();
                Bitmap bitmap = null;
                if (str.length() > 0){
                    bitmap = QRCodeEncoder.syncEncodeQRCode(str,350,Color.parseColor("#000000"));//二维码
                    if (bitmap != null){
                        SingleTouchView singleTouchView = new SingleTouchView(HengFuActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp(bitmap);
                        canv.addView(singleTouchView);
                        arrs.add(singleTouchView);
                        if (codeBar.getVisibility() == View.VISIBLE){
                            codeBar.setVisibility(View.GONE);
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

    private ArrayList<SingleTouchView> arrs = new ArrayList<>();
    private boolean isexsit = false;
    private void addCusView(){
//        if (null != txt && txt.length() > 0){
//            SingleTouchView singleTouchView = new SingleTouchView(this);
//            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT);
//            singleTouchView.setLayoutParams(layoutParams);
//            singleTouchView.setImageBitamp(Text2BitmapUtils.getBitmap(this,txt,500,txt.length(),1,0xFF8E8E8E,0x00121234));
//            canv.addView(singleTouchView);
//            arrs.add(singleTouchView);
//        }else{
//            ToastUtils.showShort("请输入文字");
//        }
//        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.tuya_1);
//
//        canv.addSticker(new DrawableSticker(drawable));
        if (isexsit){
            return;
        }
        canv.addSticker(textSticker, Sticker.Position.CENTER);
        isexsit = true;

    }



}
