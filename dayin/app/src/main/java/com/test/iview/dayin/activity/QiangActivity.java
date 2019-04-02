package com.test.iview.dayin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.ShareBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.DiaLog;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.util.QqShare;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.wbapi.WBShareActivity;
import com.test.iview.dayin.wxapi.WeiXinUtil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\21 0021.
 */

public class QiangActivity extends BaseActivity {
    @BindView(R.id.qiang_fanhui)
    ImageView qiangFanhui;
    @BindView(R.id.qiang_pic)
    ImageView qiangPic;
    @BindView(R.id.qiang_share)
    TextView qiangShare;
    private Bitmap image;

    private ShareBean bean;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        OkGo.post(IURL.Share)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s,ShareBean.class);
                        if (bean!=null){
                            if (bean.getStatus()==0){
                                GlideImage.loaderImage(bean.getLink(),qiangPic);
                            }else{
                                ToastUtils.showToast(bean.getMsg());
                            }
                        }else{
                            ToastUtils.showToast("");
                        }
                    }
                });
    }

    @Override
    public int initLayout() {
        return R.layout.item_my_qiang;
    }

    //    375 * 667
    @OnClick({R.id.qiang_fanhui, R.id.qiang_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qiang_fanhui:
                finish();
                break;
            case R.id.qiang_share:
                initDialogView();
                break;
        }
    }

    private Dialog dialog;

    private void initDialogView() {
        RelativeLayout layout = DiaLog.diaLog(dialog, this, R.layout.item_fenxiang);

        layout.findViewById(R.id.item_fenxiang_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye1(bean.getShare(), bean.getShartitle(), bean.getShardesc(), false,bean.getSharpic());
            }
        });
        layout.findViewById(R.id.item_fenxiang_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye(bean.getShare(), bean.getShartitle(), bean.getShardesc(), true,bean.getSharpic());
            }
        });
        layout.findViewById(R.id.item_fenxiang_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qqShareToTuWen(QiangActivity.this, bean.getShartitle(), bean.getShardesc(), bean.getShare(),bean.getSharpic());
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_qqkongjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qzoneShareToTuWen(QiangActivity.this, bean.getShartitle(), bean.getShardesc(), bean.getShare(), bean.getSharpic());
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                Intent intent = new Intent(QiangActivity.this, WBShareActivity.class);
                intent.putExtra("WBShareActivity_Title", "");
                intent.putExtra("WBShareActivity_Text", bean.getShartitle()+bean.getShardesc());
                intent.putExtra("WBShareActivity_Url", bean.getShare());
                intent.putExtra("WBShareActivity_img", bean.getSharpic());
                startActivity(intent);
            }
        });
    }
}
