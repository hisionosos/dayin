package com.test.iview.dayin.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.bean.EventMessage;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.utils.AppUtils;
import com.test.iview.dayin.utils.Constant;
import com.test.iview.dayin.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LanguageActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;

    @BindView(R.id.item1)
    RelativeLayout item1;
    @BindView(R.id.item2)
    RelativeLayout item2;
    @BindView(R.id.item3)
    RelativeLayout item3;
    @BindView(R.id.item4)
    RelativeLayout item4;
    @BindView(R.id.item5)
    RelativeLayout item5;
    @BindView(R.id.item6)
    RelativeLayout item6;
    @BindView(R.id.item7)
    RelativeLayout item7;
    @BindView(R.id.choose0)
    RadioButton choose0;
    @BindView(R.id.choose1)
    RadioButton choose1;
    @BindView(R.id.choose2)
    RadioButton choose2;
    @BindView(R.id.choose3)
    RadioButton choose3;
    @BindView(R.id.choose4)
    RadioButton choose4;
    @BindView(R.id.choose5)
    RadioButton choose5;
    @BindView(R.id.choose6)
    RadioButton choose6;
    @BindView(R.id.rgSex)
    RadioGroup rgSex;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.GONE);
        commonTxt.setVisibility(View.VISIBLE);
        commonTxt.setText(R.string.dy_save);
        commonTitle.setText(R.string.dy_yuyans);
    }

    String local = "zh";
    String cur_local = "zh";
    @Override
    public void initData() {
        cur_local = (String) SharedPreferencesUtils.getParam(Constant.APP_LANGUAGE,"zh");
        if (null == cur_local ||cur_local.equals("zh")){
            choose0.setChecked(true);
            choose1.setChecked(false);
        }else{
            choose0.setChecked(false);
            choose1.setChecked(true);
        }

    }

    @Override
    public int initLayout() {
        return R.layout.language_lay;
    }


    @OnClick({R.id.back,R.id.choose0, R.id.choose1, R.id.choose2, R.id.choose3, R.id.choose4, R.id.choose5, R.id.choose6, R.id.common_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
               finish();
                break;
            case R.id.choose0:
                Log.e("check","choose0");
                local = "zh";
                break;
            case R.id.choose1:
                local = "en";
                Log.e("check","choose1");
                break;
            case R.id.choose2:
                local = "es";
                Log.e("check","choose2");
                break;
            case R.id.choose3:
                local = "pt";
                Log.e("check","choose3");
                break;
            case R.id.choose4:
                local = "ja";
                Log.e("check","choose4");
                break;
            case R.id.choose5:
                local = "zh";
                Log.e("check","choose5");
                break;
            case R.id.choose6:
                local = "fr";
                Log.e("check","choose6");
                break;
            case R.id.common_txt:
//                MyApplication.mCache.put("local",local);
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);

                if (!cur_local.equals(local)){
                    showLoadingDialog();
                    AppUtils.switchLanguage(this,local);
                    EventBus.getDefault().post(new EventMessage("local"));
                }

                finish();

                // 杀掉进程
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(0);
                break;
        }
    }

}
