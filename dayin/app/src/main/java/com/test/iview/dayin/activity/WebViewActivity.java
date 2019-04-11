package com.test.iview.dayin.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.DiaLog;
import com.test.iview.dayin.util.QqShare;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.wbapi.WBShareActivity;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import butterknife.BindView;
import butterknife.OnClick;



/**
 * Created by Administrator on 2017/10/11.
 */

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_search)
    TextView searchSearch;
    @BindView(R.id.web_title)
    RelativeLayout webTitle;
    @BindView(R.id.webview_web)
    WebView webView;
    @BindView(R.id.web_lay)
    LinearLayout webLay;
    @BindView(R.id.webview_fanhui)
    ImageView webviewFanhui;
    @BindView(R.id.webview_forward)
    ImageView webviewForward;
    @BindView(R.id.big_txt)
    TextView bigTxt;
    @BindView(R.id.small_txt)
    TextView smallTxt;
    @BindView(R.id.print_cur)
    TextView printCur;
    @BindView(R.id.print_all)
    TextView printAll;
    @BindView(R.id.webview_rl)
    RelativeLayout webviewRl;

    private String url = "http://www.baidu.com";
    WebSettings settings;
    private int textSize = 100;
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
        initTestWebView();
        webView.loadUrl(url);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_webview;
    }


    @OnClick({R.id.back, R.id.search_search, R.id.webview_fanhui, R.id.webview_forward, R.id.big_txt, R.id.small_txt, R.id.print_cur, R.id.print_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search_search:
                url = searchEt.getText().toString();
                if (!url.startsWith("http://")) {
                    url = url + "http://";
                }
                webView.loadUrl(url);
                break;
            case R.id.webview_fanhui:
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                break;
            case R.id.webview_forward:
                if (webView.canGoForward()){
                    webView.goForward();
                }
                break;
            case R.id.big_txt:
                if (textSize < 200){
                    textSize += 10;
                    settings.setTextZoom(textSize);
                }

                break;
            case R.id.small_txt:
                if (textSize > 20){
                    textSize -= 10;
                    settings.setTextZoom(textSize);
                }
                break;
            case R.id.print_cur:
                webTitle.setVisibility(View.GONE);
                webviewRl.setVisibility(View.GONE);
                getCutImage();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                webTitle.setVisibility(View.VISIBLE);
                webviewRl.setVisibility(View.VISIBLE);
                //截屏
                break;
            case R.id.print_all:
                //截取组件视图

                getViewBitmap(webView);
                break;
        }
    }


    private String getCutImage(){
        final View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        String filePath = "";

        try {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // 要在运行在子线程中
                    final Bitmap bmp = dView.getDrawingCache(); // 获取图片
                    savePicture(bmp, System.currentTimeMillis() + "_screen.png");// 保存图片
                    ToastUtils.showShort("保存成功");
                    dView.destroyDrawingCache(); // 保存过后释放资源
                    bmp.recycle();

                }
            },500);

        } catch (Exception e) {
        }

        return filePath;
    }



        private void getViewBitmap(final WebView webView){
            webView.setDrawingCacheEnabled(true);
            webView.buildDrawingCache();
            int height = (int) (webView.getContentHeight() * webView.getScale());
            int width = webView.getWidth();
            int pH = webView.getHeight();
            final Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bm);
            int top = height;
            while (top > 0) {
                if (top < pH) {
                    top = 0;
                } else {
                    top -= pH;
                }
                canvas.save();
                canvas.clipRect(0, top, width, top + pH);
                webView.scrollTo(0, top);
                webView.draw(canvas);
                canvas.restore();
            }

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    savePicture(bm, System.currentTimeMillis() + "_screen.png");// 保存图片
                    ToastUtils.showShort("保存成功");
                    webView.destroyDrawingCache(); // 保存过后释放资源
                    bm.recycle();
                }
            });

        }




    private void getAllImage(){

        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        String filePath = "";
        // WebView 生成长图，也就是超过一屏的图片，代码中的 longImage 就是最后生成的长图
        webView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        webView.layout(0, 0, webView.getMeasuredWidth(), webView.getMeasuredHeight());
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        final Bitmap longImage = Bitmap.createBitmap(webView.getMeasuredWidth(),
                webView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(longImage);  // 画布的宽高和 WebView 的网页保持一致
        Paint paint = new Paint();
        canvas.drawBitmap(longImage, 0, webView.getMeasuredHeight(), paint);
        webView.draw(canvas);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // 要在运行在子线程中
                savePicture(longImage, System.currentTimeMillis() + "_screen.png");// 保存图片
                webView.destroyDrawingCache();

            }
        },500);

        savePicture(longImage, System.currentTimeMillis() + "_screen.png");// 保存图片

    }


    public void savePicture(Bitmap bm, String fileName) {
        if (null == bm) {
            Log.i("savePicture", "---图片为空------");
            return;
        }
        Log.e("path",Environment.getExternalStorageDirectory().getAbsolutePath() );
        File foder = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(foder, fileName);
        try {
            if (!myCaptureFile.exists()) {
                myCaptureFile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            //压缩保存到本地
            bm.compress(Bitmap.CompressFormat.PNG, 90, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;

    private void initTestWebView() {

        settings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键
//                        时的操作
                        webView.goBack();   //后退
                        return true;
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

                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                ToastUtils.showToast("加载失败...");

            }
        });


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
//            isLogin = true;
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


}
