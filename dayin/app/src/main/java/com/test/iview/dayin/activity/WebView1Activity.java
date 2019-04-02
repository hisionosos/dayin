package com.test.iview.dayin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.util.DiaLog;
import com.test.iview.dayin.util.QqShare;
import com.test.iview.dayin.util.WebUtile;
import com.test.iview.dayin.wbapi.WBShareActivity;
import com.test.iview.dayin.wxapi.WeiXinUtil;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\30 0030.
 */

public class WebView1Activity extends BaseActivity {
    @BindView(R.id.webview1_title)
    TextView webview1Title;
    @BindView(R.id.webview1)
    WebView webview1;


    private Dialog dialog;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        webview1Title.setText(getIntent().getStringExtra("WebView_Title_t"));

//        WebUtile.init(this, webview1, getIntent().getStringExtra("WebView_URL"), null, null, gif);
//        webview1.addJavascriptInterface(new JavaScriptinterface(), "app");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_webview1;
    }


    public class JavaScriptinterface {
        public JavaScriptinterface() {
        }

        /**
         * Show a toShoping from the web page
         * @param goodId   商品ID
         */
        @JavascriptInterface
        public void toShoping(String goodId) {
            Intent intent = new Intent(WebView1Activity.this, JianLouDetailsActivity.class);
            intent.putExtra("JianLouDetailsActivity_Id", goodId);
            startActivity(intent);
        }

    }

    @OnClick({R.id.webview1_fanhui, R.id.webview1_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webview1_fanhui:
                finish();
                break;
            case R.id.webview1_share:
                share(getIntent().getStringExtra("WebViewShare_URL"), getIntent().getStringExtra("WebView_title"), getIntent().getStringExtra("WebView_Des"), getIntent().getStringExtra("WebView_img"));
                break;
        }
    }

    private void share(final String url, final String title, final String describe, final String imgUrl) {
        RelativeLayout layout = DiaLog.diaLog(dialog, this, R.layout.item_fenxiang);
        layout.findViewById(R.id.item_fenxiang_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye1(url, title, describe, false, imgUrl);
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
                WeiXinUtil.wangye1(url, title, describe, true, imgUrl);
            }
        });
        layout.findViewById(R.id.item_fenxiang_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qqShareToTuWen(WebView1Activity.this, title, describe, url, imgUrl);
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_qqkongjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qzoneShareToTuWen(WebView1Activity.this, title, describe, url, imgUrl);
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                Intent intent = new Intent(WebView1Activity.this, WBShareActivity.class);
                intent.putExtra("WBShareActivity_Title", title);
                if (describe.equals("")) {
                    intent.putExtra("WBShareActivity_Text", title);
                } else {
                    intent.putExtra("WBShareActivity_Text", title);
                }
//                if (url.indexOf("http") == -1) {
//                    intent.putExtra("WBShareActivity_Url", "https://" + url);
//                } else {
                    intent.putExtra("WBShareActivity_Url", url);
//                }

                startActivity(intent);
            }
        });
    }
}
