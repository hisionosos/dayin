package com.test.iview.dayin.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.DiaLog;
import com.test.iview.dayin.util.QqShare;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.wbapi.WBShareActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/10/11.
 */

public class WebViewActivity extends BaseActivity {
    WebView webView;
    final public static int READ_EXTERNAL_STORAGE = 555;
    private RelativeLayout webview_rl;
    private ImageView webview_fanhui;
    private boolean isLogin = false;
    Map<String,String> header = new HashMap<>();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        webView = (WebView) findViewById(R.id.webview_web);
        webview_rl = (RelativeLayout) findViewById(R.id.webview_rl);
        webview_fanhui = (ImageView) findViewById(R.id.webview_fanhui);
        webview_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent().getIntExtra("WebView_Type",0)==0){
            webview_rl.setVisibility(View.GONE);
        }else{
            webview_rl.setVisibility(View.VISIBLE);
        }
        header.put("android","android");

        webView.addJavascriptInterface(new JavaScriptinterface(this), "app");
        initTestWebView();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_webview;
    }

    public class JavaScriptinterface {
        private Activity mactivity;
        private Dialog dialog;

        /**
         * Instantiate the interface and set the context
         */
        public JavaScriptinterface(Activity activity) {
            mactivity = activity;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void showToast1(String toast) {
            ToastUtils.showToast(toast);
        }


        /**
         * 关闭页面
         */
        @JavascriptInterface
        public void finish() {
            mactivity.finish();
        }

        /**
         * 登陆
         */
        @JavascriptInterface
        public void login() {
            isLogin = true;
            startActivity(new Intent(WebViewActivity.this, LoginActivity.class));
        }

        /**
         * 获取Token
         */
        @JavascriptInterface
        public String getToken() {
            if (MyApplication.userToken != null) {
                return MyApplication.userToken;
            } else {
                return "";
            }
        }

        /**
         * 获取社区ID
         */
        @JavascriptInterface
        public String getCommunityId() {
            return getIntent().getStringExtra("DianDongBaiKeWenZhangXiangQingActivity_SheQvId");
        }


        /**
         * 分享
         *
         * @param url      网页URL
         * @param title    标题
         * @param describe 描述
         */
        @JavascriptInterface
        public void share(final String url, final String title, final String describe, final String imgUrl) {
            RelativeLayout layout = DiaLog.diaLog(dialog, mactivity, R.layout.item_fenxiang);
            layout.findViewById(R.id.item_fenxiang_weixin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DiaLog.dismiss();
//                    WeiXinUtil.wangye(url, title, describe, false);
                }
            });
            layout.findViewById(R.id.item_fenxiang_pengyouquan).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DiaLog.dismiss();
//                    WeiXinUtil.wangye(url, title, describe, true);
                }
            });
            layout.findViewById(R.id.item_fenxiang_qq).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QqShare.qqShareToTuWen(WebViewActivity.this, title, describe, url, imgUrl);
                    DiaLog.dismiss();
                }
            });
            layout.findViewById(R.id.item_fenxiang_qqkongjian).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QqShare.qzoneShareToTuWen(WebViewActivity.this, title, describe, url, imgUrl);
                    DiaLog.dismiss();
                }
            });
            layout.findViewById(R.id.item_fenxiang_weibo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DiaLog.dismiss();
                    Intent intent = new Intent(mactivity, WBShareActivity.class);
                    intent.putExtra("WBShareActivity_Title", title);
                    if (describe.equals("")) {
                        intent.putExtra("WBShareActivity_Text", title);
                    } else {
                        intent.putExtra("WBShareActivity_Text", describe);
                    }
                    if (url.indexOf("http") == -1) {
                        intent.putExtra("WBShareActivity_Url", "https://" + url);
                    } else {
                        intent.putExtra("WBShareActivity_Url", url);
                    }

                    mactivity.startActivity(intent);
                }
            });
        }
    }

    private ArrayList<String> list = new ArrayList<>();


    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;

    private void initTestWebView() {
        String url = getIntent().getStringExtra("WebView_URL");
//        String url = "https://www.baidu.com/?tn=95531022_s_hao_pg";
        WebSettings settings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setAllowFileAccess(true);
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键
//                        时的操作
                        webView.goBack();   //后退
//                        webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        webView.addJavascriptInterface(new JavaScriptinterface(WebViewActivity.this), "app");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("xxx提示").setMessage(message).setPositiveButton("确定", null);
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                dialog.show();
                result.confirm();
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {

                }
            }

            //扩展浏览器上传文件
            //3.0++版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                openFileChooserImpl(uploadMsg);
            }

            //3.0--版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooserImpl(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooserImpl(uploadMsg);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                onenFileChooseImpleForAndroid(filePathCallback);
                return true;
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {   //加载完成
                super.onPageFinished(view, url);

                if (url.equals("about:blank")) {
                    ToastUtils.showToast("加载失败...");
                    finish();
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                ToastUtils.showToast("加载失败...");
                finish();
            }
        });

        webView.loadUrl(url);
    }


    public ValueCallback<Uri> mUploadMessage;

    public void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
    }

    public ValueCallback<Uri[]> mUploadMessageForAndroid5;

    public void onenFileChooseImpleForAndroid(ValueCallback<Uri[]> filePathCallback) {
        mUploadMessageForAndroid5 = filePathCallback;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;

        } else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            if (null == mUploadMessageForAndroid5)
                return;
            Uri result = (intent == null || resultCode != RESULT_OK) ? null : intent.getData();
            if (result != null) {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
            } else {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
            }
            mUploadMessageForAndroid5 = null;
        }
    }

}
