package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.adapter.SuCaiAdapter;
import com.test.iview.dayin.view.common.MediaItemDecoration;
import java.util.ArrayList;
import butterknife.BindView;

public class SuCaiKuActivity extends BaseActivity implements SuCaiAdapter.OnOssSelectListener{

    @BindView(R.id.sucaiku)
    RecyclerView sucaiku;

    SuCaiAdapter suCaiAdapter;

    private int[] biaoqian_data = {R.mipmap.biaoqian_1,R.mipmap.biaoqian_2,R.mipmap.biaoqian_3,R.mipmap.biaoqian_4,
            R.mipmap.biaoqian_5,R.mipmap.biaoqian_6,R.mipmap.biaoqian_7,R.mipmap.biaoqian_8,R.mipmap.biaoqian_9,
            R.mipmap.biaoqian_10,R.mipmap.biaoqian_11,R.mipmap.biaoqian_12,R.mipmap.biaoqian_13,R.mipmap.biaoqian_14,
            R.mipmap.biaoqian_15,R.mipmap.biaoqian_16,R.mipmap.biaoqian_17,R.mipmap.biaoqian_18,R.mipmap.biaoqian_19,
            R.mipmap.biaoqian_20,R.mipmap.biaoqian_21,R.mipmap.biaoqian_22,R.mipmap.biaoqian_23,R.mipmap.biaoqian_24,
            R.mipmap.biaoqian_25,R.mipmap.biaoqian_26,R.mipmap.biaoqian_27,R.mipmap.biaoqian_28,R.mipmap.biaoqian_29,
            R.mipmap.biaoqian_30,R.mipmap.biaoqian_31,R.mipmap.biaoqian_32,R.mipmap.biaoqian_33,R.mipmap.biaoqian_34,
            R.mipmap.biaoqian_35,R.mipmap.biaoqian_36,R.mipmap.biaoqian_37,R.mipmap.biaoqian_38};

    private int[] biaoqianzhi_data = {R.mipmap.biaoqianzhi_1,R.mipmap.biaoqianzhi_2,R.mipmap.biaoqianzhi_3,R.mipmap.biaoqianzhi_4};
    private int[] biaoqing_data = {R.mipmap.biaoqing_1,R.mipmap.biaoqing_2,R.mipmap.biaoqing_3,R.mipmap.biaoqing_4,R.mipmap.biaoqing_5,
            R.mipmap.biaoqing_6,R.mipmap.biaoqing_7,R.mipmap.biaoqing_8,R.mipmap.biaoqing_9,R.mipmap.biaoqing_10,
            R.mipmap.biaoqing_11,R.mipmap.biaoqing_12,R.mipmap.biaoqing_13,R.mipmap.biaoqing_14,R.mipmap.biaoqing_15,
            R.mipmap.biaoqing_16,R.mipmap.biaoqing_17,R.mipmap.biaoqing_18,R.mipmap.biaoqing_19,R.mipmap.biaoqing_20,
            R.mipmap.biaoqing_21,R.mipmap.biaoqing_22,R.mipmap.biaoqing_23,R.mipmap.biaoqing_24,R.mipmap.biaoqing_25,
            R.mipmap.biaoqing_26,R.mipmap.biaoqing_27,R.mipmap.biaoqing_28,R.mipmap.biaoqing_29,R.mipmap.biaoqing_30,
            R.mipmap.biaoqing_31,R.mipmap.biaoqing_32,R.mipmap.biaoqing_33,R.mipmap.biaoqing_34,R.mipmap.biaoqing_35,
            R.mipmap.biaoqing_36,R.mipmap.biaoqing_37,R.mipmap.biaoqing_38,R.mipmap.biaoqing_39,R.mipmap.biaoqing_40,
            R.mipmap.biaoqing_41,R.mipmap.biaoqing_42,R.mipmap.biaoqing_43,R.mipmap.biaoqing_44,R.mipmap.biaoqing_45,
            R.mipmap.biaoqing_46,R.mipmap.biaoqing_47,R.mipmap.biaoqing_48,R.mipmap.biaoqing_49,R.mipmap.biaoqing_50,
            R.mipmap.biaoqing_51,R.mipmap.biaoqing_52,R.mipmap.biaoqing_53,R.mipmap.biaoqing_54,R.mipmap.biaoqing_55,
            R.mipmap.biaoqing_56,R.mipmap.biaoqing_57,R.mipmap.biaoqing_58,R.mipmap.biaoqing_59,R.mipmap.biaoqing_60,
            R.mipmap.biaoqing_61,R.mipmap.biaoqing_62,R.mipmap.biaoqing_63,R.mipmap.biaoqing_64,R.mipmap.biaoqing_65,
            R.mipmap.biaoqing_66,R.mipmap.biaoqing_67,R.mipmap.biaoqing_68,R.mipmap.biaoqing_69,R.mipmap.biaoqing_70,
            R.mipmap.biaoqing_71,R.mipmap.biaoqing_72,R.mipmap.biaoqing_73,R.mipmap.biaoqing_74,R.mipmap.biaoqing_75,
            R.mipmap.biaoqing_76,R.mipmap.biaoqing_77,R.mipmap.biaoqing_78,R.mipmap.biaoqing_79,R.mipmap.biaoqing_80,
            R.mipmap.biaoqing_81,R.mipmap.biaoqing_82,R.mipmap.biaoqing_83,R.mipmap.biaoqing_84,R.mipmap.biaoqing_85,
            R.mipmap.biaoqing_86,R.mipmap.biaoqing_87,R.mipmap.biaoqing_88,R.mipmap.biaoqing_89,R.mipmap.biaoqing_90,
            R.mipmap.biaoqing_91,R.mipmap.biaoqing_92,R.mipmap.biaoqing_93,R.mipmap.biaoqing_94,R.mipmap.biaoqing_95,
            R.mipmap.biaoqing_96,R.mipmap.biaoqing_97,R.mipmap.biaoqing_98,R.mipmap.biaoqing_99,R.mipmap.biaoqing_100,
            R.mipmap.biaoqing_101,R.mipmap.biaoqing_102,R.mipmap.biaoqing_103,R.mipmap.biaoqing_104,R.mipmap.biaoqing_105,
            R.mipmap.biaoqing_106,R.mipmap.biaoqing_107,R.mipmap.biaoqing_108,R.mipmap.biaoqing_109,R.mipmap.biaoqing_110,
            R.mipmap.biaoqing_111,R.mipmap.biaoqing_112,R.mipmap.biaoqing_113,R.mipmap.biaoqing_114,R.mipmap.biaoqing_115,
            R.mipmap.biaoqing_116,R.mipmap.biaoqing_117,R.mipmap.biaoqing_118,R.mipmap.biaoqing_119,R.mipmap.biaoqing_120,
            R.mipmap.biaoqing_121,R.mipmap.biaoqing_122,R.mipmap.biaoqing_123,R.mipmap.biaoqing_124,R.mipmap.biaoqing_125};

    private int[] kechengbiao_data = {R.mipmap.kechengbiao_1,R.mipmap.kechengbiao_2,R.mipmap.kechengbiao_3,R.mipmap.kechengbiao_4,
            R.mipmap.kechengbiao_5,R.mipmap.kechengbiao_6,R.mipmap.kechengbiao_7,R.mipmap.kechengbiao_8};

    private int[] tukuang_data = {R.mipmap.tukuang_1,R.mipmap.tukuang_2,R.mipmap.tukuang_3,R.mipmap.tukuang_4,R.mipmap.tukuang_5,
            R.mipmap.tukuang_6,R.mipmap.tukuang_7,R.mipmap.tukuang_8,R.mipmap.tukuang_9,R.mipmap.tukuang_10,R.mipmap.tukuang_11,
            R.mipmap.tukuang_12,R.mipmap.tukuang_13,R.mipmap.tukuang_14,R.mipmap.tukuang_15,R.mipmap.tukuang_16,R.mipmap.tukuang_17,
            R.mipmap.tukuang_18,R.mipmap.tukuang_19,R.mipmap.tukuang_20,R.mipmap.tukuang_21,R.mipmap.tukuang_22,R.mipmap.tukuang_23,
            R.mipmap.tukuang_24,R.mipmap.tukuang_25,R.mipmap.tukuang_26,R.mipmap.tukuang_27,R.mipmap.tukuang_28,R.mipmap.tukuang_29,
            R.mipmap.tukuang_30,R.mipmap.tukuang_31,R.mipmap.tukuang_32,R.mipmap.tukuang_33,R.mipmap.tukuang_34,R.mipmap.tukuang_35,
            R.mipmap.tukuang_36,R.mipmap.tukuang_37,R.mipmap.tukuang_38,R.mipmap.tukuang_39,R.mipmap.tukuang_40,R.mipmap.tukuang_41,
            R.mipmap.tukuang_42,R.mipmap.tukuang_43,R.mipmap.tukuang_44,R.mipmap.tukuang_45,R.mipmap.tukuang_46,R.mipmap.tukuang_47,
            R.mipmap.tukuang_48,R.mipmap.tukuang_49,R.mipmap.tukuang_50,R.mipmap.tukuang_51,R.mipmap.tukuang_52,R.mipmap.tukuang_53,
            R.mipmap.tukuang_54,R.mipmap.tukuang_55,R.mipmap.tukuang_56,R.mipmap.tukuang_57,R.mipmap.tukuang_58,R.mipmap.tukuang_59,
            R.mipmap.tukuang_60,R.mipmap.tukuang_61};

    private int[] tuya_data = {R.mipmap.tuya_1,R.mipmap.tuya_2,R.mipmap.tuya_3,R.mipmap.tuya_4,R.mipmap.tuya_5,R.mipmap.tuya_6,
            R.mipmap.tuya_7,R.mipmap.tuya_8,R.mipmap.tuya_9};

    private int[] wenzi_data = {R.mipmap.wenzi_1,R.mipmap.wenzi_2,R.mipmap.wenzi_3,R.mipmap.wenzi_4,R.mipmap.wenzi_5,
            R.mipmap.wenzi_6,R.mipmap.wenzi_7,R.mipmap.wenzi_8,R.mipmap.wenzi_9,R.mipmap.wenzi_10,R.mipmap.wenzi_11,
            R.mipmap.wenzi_12,R.mipmap.wenzi_13,R.mipmap.wenzi_14,R.mipmap.wenzi_15,R.mipmap.wenzi_16,R.mipmap.wenzi_17,
            R.mipmap.wenzi_18,R.mipmap.wenzi_19,R.mipmap.wenzi_20,R.mipmap.wenzi_21,R.mipmap.wenzi_22,R.mipmap.wenzi_23,
            R.mipmap.wenzi_24,R.mipmap.wenzi_25,R.mipmap.wenzi_26,R.mipmap.wenzi_27,R.mipmap.wenzi_28,R.mipmap.wenzi_29,
            R.mipmap.wenzi_30,R.mipmap.wenzi_31,R.mipmap.wenzi_32,R.mipmap.wenzi_33,R.mipmap.wenzi_34,R.mipmap.wenzi_35,
            R.mipmap.wenzi_36,R.mipmap.wenzi_37,R.mipmap.wenzi_38,R.mipmap.wenzi_39,R.mipmap.wenzi_40,R.mipmap.wenzi_41,
            R.mipmap.wenzi_42,R.mipmap.wenzi_43,R.mipmap.wenzi_44,R.mipmap.wenzi_45,R.mipmap.wenzi_46,R.mipmap.wenzi_47,
            R.mipmap.wenzi_48,R.mipmap.wenzi_49};

    private int[] xiaopiao_data = {R.mipmap.xiaopiao_1,R.mipmap.xiaopiao_2};
    private int[] xiaoji_data = {R.drawable.xiaoji_1,R.drawable.xiaoji_2,R.drawable.xiaoji_3};


    private int[] daiban_data = {R.mipmap.biaoqian_24,R.mipmap.biaoqian_26 ,R.mipmap.biaoqian_27,R.mipmap.biaoqian_29
            ,R.mipmap.biaoqian_30,R.mipmap.biaoqian_31};
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        sucaiku.setLayoutManager(new GridLayoutManager(this, 3));
        sucaiku.addItemDecoration(new MediaItemDecoration(8, 3));


    }

    private String sucai_flag = "";
    private int[] datas = null;
    private String[] strs = {"biaoqian","biaoqianzhi","biaoqing","kechengbiao","tukuang","tuya","wenzi","xiaopiao","xiaoji"};
    @Override
    public void initData() {
        sucai_flag = getIntent().getStringExtra("sucai");
//        sucai_flag = strs[4];
        if (null != sucai_flag){
            switch (sucai_flag){
                case "biaoqian":
                    datas = biaoqian_data;
                    break;
                case "biaoqianzhi":
                    datas = biaoqianzhi_data;
                    break;
                case "biaoqing":
                    datas = biaoqing_data;
                    break;
                case "kechengbiao":
                    datas = kechengbiao_data;
                    break;
                case "tukuang":
                    datas = tukuang_data;
                    break;
                case "tuya":
                    datas = tuya_data;
                    break;
                case "wenzi":
                    datas = wenzi_data;
                    break;
                case "xiaopiao":
                    datas = xiaopiao_data;
                    break;
                case "xiaoji":
                    datas = xiaoji_data;
                    break;
                case "daiban":
                    datas = daiban_data;
                    break;

            }
        }

        suCaiAdapter = new SuCaiAdapter(this, datas);
        suCaiAdapter.setLister(this);
        sucaiku.setAdapter(suCaiAdapter);
    }

    @Override
    public int initLayout() {
        return R.layout.sucai_lay;
    }


    @Override
    public void onOssSelect(View view, int position) {
        Log.e("position",position + "");
        Intent intent = new Intent();
        intent.putExtra("img", position);
        setResult(1000, intent);
        finish();
    }


}
