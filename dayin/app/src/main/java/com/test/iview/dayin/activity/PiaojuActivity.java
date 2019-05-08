package com.test.iview.dayin.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.common.CaptureActivity;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.utils.ResourceUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class PiaojuActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.main_tab)
    TabLayout mainTab;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.add_image)
    TextView addImage;
    @BindView(R.id.add_code)
    TextView addCode;
    @BindView(R.id.add_biaoqing)
    TextView addBiaoqing;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.canv)
    RelativeLayout canv;

    private String[] tab_title = {"小字", "小票", "收银票据", "不干胶", "标签纸"};
    private int[] tab_imgs = {R.drawable.tab_xiaozi, R.drawable.tab_piao, R.drawable.tab_fapiao, R.drawable.tab_jiao, R.drawable.tab_biaoqian};

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        homeAdd.setVisibility(View.INVISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText(getString(R.string.dy_piaojudayin));

//        sing.setImageBitamp(BitmapFactory.decodeResource(getResources(), R.drawable.qwer));
        //初始化tab
        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = tab.getCustomView().findViewById(R.id.tab_txt);
                textView.setTextColor((ResourceUtils.getColor(R.color.main_tab_color)));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = tab.getCustomView().findViewById(R.id.tab_txt);
                textView.setTextColor((ResourceUtils.getColor(R.color.t32)));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < tab_title.length; i++) {
            TabLayout.Tab tab = mainTab.newTab();
            View view = LayoutInflater.from(this).inflate(R.layout.main_tab_item, null);
            ImageView imageView = view.findViewById(R.id.tab_img);
            TextView textView = view.findViewById(R.id.tab_txt);
            imageView.setBackgroundResource(tab_imgs[i]);
            textView.setText(tab_title[i]);
            tab.setCustomView(view);
            mainTab.addTab(tab,false);
        }

    }

    @Override
    public void initData() {
//        singleTouchView.setEditable(false);
//        singleTouchView.setImageBitamp(Text2BitmapUtils.getBitmap(this,"共四个字",500,5,3,0xff999585,0x00121234));



    }

    @Override
    public int initLayout() {
        return R.layout.piaoju_lay;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == CameraUtils.CODE_ALBUM_CHOOSE && null != data.getData()) {
//              int s = BitmapUtil.getBitmapBytes(MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData()));
                addImage(data.getData());
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private ArrayList<SingleTouchView> arrs = new ArrayList<>();

    private void addImage(Uri uri){
        showLoadingDialog();
        BitmapUtil.createScaledBitmap(this,uri,350, new BitmapUtil.MyCallback() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onSucceed(final Object object) {
                hideLaodingDialog();
                PiaojuActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SingleTouchView singleTouchView = new SingleTouchView(PiaojuActivity.this);
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

    @OnClick({R.id.back, R.id.add_image, R.id.add_code, R.id.add_biaoqing,R.id.home_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_image:
                Log.e("add_image","add_image");
                CameraUtils.albumChoose(this,null);
                break;
            case R.id.add_code:
                startActivity(new Intent(this, CaptureActivity.class));
                break;
            case R.id.add_biaoqing:

                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView){
                        arrs.get(i).setEditable(false);
                    }
                }
                BitmapUtil.getInstance().getCutImage(canv);
                break;
        }
    }
}
