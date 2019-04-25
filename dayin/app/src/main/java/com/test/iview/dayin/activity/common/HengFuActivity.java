package com.test.iview.dayin.activity.common;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.utils.ResourceUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.SingleTouchView;
import com.test.iview.dayin.view.Text2BitmapUtils;
import com.test.iview.dayin.view.imagecut.core.IMGText;
import com.test.iview.dayin.view.imagecut.core.sticker.IMGSticker;
import com.test.iview.dayin.view.imagecut.core.sticker.IMGStickerPortrait;
import com.test.iview.dayin.view.imagecut.view.IMGStickerImageView;
import com.test.iview.dayin.view.imagecut.view.IMGStickerTextView;
import com.xiaopo.flying.sticker.BitmapStickerIcon;
import com.xiaopo.flying.sticker.DeleteIconEvent;
import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.FlipHorizontallyEvent;
import com.xiaopo.flying.sticker.Sticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;
import com.xiaopo.flying.sticker.ZoomIconEvent;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

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
    EditText editTxt;

    @BindView(R.id.get_txt)
    Button getTxt;
    @BindView(R.id.canv)
    StickerView canv;

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
    public int initLayout() {
        return R.layout.hengfu_lay;
    }

    @OnClick({R.id.back,R.id.get_txt,R.id.home_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.get_txt:
                addCusView(editTxt.getText().toString());
                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView){
                        arrs.get(i).setEditable(false);
                    }
                }
                editTxt.setCursorVisible(false);
                BitmapUtil.getInstance().getCutImage(canv);
                break;

        }
    }

    private ArrayList<SingleTouchView> arrs = new ArrayList<>();
    private void addCusView(String txt){
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
        canv.addSticker(
                new TextSticker(getApplicationContext())
                        .setText(txt)
                        .setMaxTextSize(14)
                        .resizeText()
                , Sticker.Position.CENTER);


    }



}
