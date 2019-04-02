package com.test.iview.dayin.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.test.iview.dayin.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/5.
 */

public class WebUtile {
    private static Activity activity;

    private static WebView webViewTop;
    private static WebView webViewBottom;
    private static Map<String,String> header;
    public static void init(final Activity activity1, final WebView webView, String topUrl, final WebView webView1, String bottomUrl, final LinearLayout gif) {
        activity = activity1;
        webViewTop = webView;
        webViewBottom = webView1;
        header = new HashMap<>();
        header.put("android","android");
        initWebView(webViewTop, topUrl, bottomUrl, gif);
        if (webView1 != null) {
            initWebView(webViewBottom, topUrl, bottomUrl, gif);
        }

    }

    private static void initWebView(final WebView webView, String topUrl, String bottomUrl, final LinearLayout gif) {
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
                    if (gif != null) {

                    }
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
                view.loadUrl(url,header);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {   //加载完成
                super.onPageFinished(view, url);
                if (gif != null) {

                }
                if (url.equals("about:blank")) {
                    ToastUtils.showToast("加载失败...");
                    activity.finish();
                }

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                ToastUtils.showToast("加载失败...");
                activity.finish();
            }
        });
        webViewTop.loadUrl(topUrl,header);
        if (webViewBottom != null) {
            webViewBottom.loadUrl(bottomUrl);
        }
    }

    public static ValueCallback<Uri> mUploadMessage;

    public final static int FILECHOOSER_RESULTCODE = 1;

    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;

    public static void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
    }

    public static ValueCallback<Uri[]> mUploadMessageForAndroid5;

    public static void onenFileChooseImpleForAndroid(ValueCallback<Uri[]> filePathCallback) {
        mUploadMessageForAndroid5 = filePathCallback;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

        activity.startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

}
