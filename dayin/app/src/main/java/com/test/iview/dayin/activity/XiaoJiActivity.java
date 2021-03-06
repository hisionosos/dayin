package com.test.iview.dayin.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.common.SuCaiKuActivity;
import com.test.iview.dayin.activity.common.TuWenActivity;
import com.test.iview.dayin.fragment.MyFragment;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.utils.DateUtils;
import com.test.iview.dayin.view.SingleTouchView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiaoJiActivity extends BaseActivity {
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
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edit_txt)
    EditText editTxt;
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
    @BindView(R.id.xiaoji_date)
    TextView xiaojiDate;
    private int mScreenWidth;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        commonTitle.setText(R.string.dy_xiaojidayin);

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.xiaoji_lay;
    }


    @OnClick({R.id.back,R.id.main_tab1,R.id.home_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
//                Intent intent = new Intent(this, SuCaiKuActivity.class);
//                intent.putExtra("sucai", "xiaoji");
//                startActivityForResult(intent, 1000);

                CameraUtils.albumChoose(XiaoJiActivity.this, null);
                break;
            case R.id.home_add:
                    editTxt.setCursorVisible(false);
                    BitmapUtil.getInstance().showBitmap(canv,true,0,false);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            int id = data.getIntExtra("img", 0);
            imgBack.setVisibility(View.VISIBLE);
            xiaojiDate.setVisibility(View.VISIBLE);

            xiaojiDate.setText(DateUtils.getStringDate() + "");
            imgBack.setBackgroundResource(id);
        }

        if (requestCode == CameraUtils.CODE_ALBUM_CHOOSE && resultCode == RESULT_OK && data != null) {
            addImage(data.getData());

        }

    }

    private void addImage(Uri uri){

        BitmapUtil.createScaledBitmap(this,uri,350, new BitmapUtil.MyCallback() {
            @Override
            public void onPrepare() {
                showLoadingDialog();
            }

            @Override
            public void onSucceed(final Object object) {
                hideLaodingDialog();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgBack.setVisibility(View.VISIBLE);
                        xiaojiDate.setVisibility(View.VISIBLE);
                        xiaojiDate.setText(DateUtils.getStringDate() + "");
                        imgBack.setImageBitmap((Bitmap)object);
                    }
                });

            }

            @Override
            public void onError() {
                hideLaodingDialog();
            }
        });
    }


}
