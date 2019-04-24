package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.utils.FileUtils;
import com.test.iview.dayin.view.CustomPopWindow;
import com.test.iview.dayin.view.SingleTouchView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class TuYaActivity extends BaseActivity {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.canv)
    FrameLayout canv;
    @BindView(R.id.main_tab1)
    Button mainTab1;
    @BindView(R.id.main_tab2)
    Button mainTab2;
    @BindView(R.id.main_tab3)
    Button mainTab3;


    private String[] tab_title = {"表格", "图片", "调整", "表情包", "二维码"};
    private int[] tab_imgs = {R.drawable.tab_biaoge, R.drawable.tab_tupian, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_ercode};

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);
        commonTitle.setText("涂鸦打印");
    }

    @Override
    public void initData() {
        File file = FileUtils.copyFilesFassets(this,"copy_img.png","dayin");
        Log.e("filepath",file.getAbsolutePath());

    }

    @Override
    public int initLayout() {
        return R.layout.tuya_lay;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000 && null != data) {
            int id = data.getIntExtra("img",0);
            addCusView(id);
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

    @OnClick({R.id.back,R.id.main_tab1, R.id.main_tab2, R.id.main_tab3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.main_tab1:
//                Intent intent = new Intent(this,SuCaiKuActivity.class);
//                intent.putExtra("sucai","tuya");
//                startActivityForResult(intent,1000);

                showPopBottom();
                break;
            case R.id.main_tab2:

                break;
            case R.id.main_tab3:

                break;
        }
    }


    private void showPopBottom(){
        final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(R.layout.pop_layout2)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();

        popWindow.showAtLocation(mainTab1, Gravity.BOTTOM,DimensUtils.dp2px(this,-137),DimensUtils.dp2px(this,100));

        LinearLayout pain_1 = popWindow.getmContentView().findViewById(R.id.pain_1);
        LinearLayout pain_2 = popWindow.getmContentView().findViewById(R.id.pain_2);
        LinearLayout pain_3 = popWindow.getmContentView().findViewById(R.id.pain_3);
        LinearLayout pain_4 = popWindow.getmContentView().findViewById(R.id.pain_4);
        LinearLayout pain_5 = popWindow.getmContentView().findViewById(R.id.pain_5);

        pain_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();
            }
        });

        pain_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();

            }
        });

        pain_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();

            }
        });

        pain_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();

            }
        });

        pain_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();

            }
        });
    }


}
