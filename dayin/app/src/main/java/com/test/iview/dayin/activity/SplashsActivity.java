package com.test.iview.dayin.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.BaseRequest;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.UpDataBean;
import com.test.iview.dayin.global.HttpManager;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.utils.BaseResponse;
import com.test.iview.dayin.utils.SharedPreferencesUtils;
import com.test.iview.dayin.utils.ToastUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class SplashsActivity extends BaseActivity implements HttpManager.HttpCallBackListener {
    public static final String TAG = "SplashsActivity";

    @BindView(R.id.welcome_rl)
    ImageView welcome_rl;
    @BindView(R.id.home_pbar)
    ProgressBar pbar;
    @BindView(R.id.home_pbar_num)
    TextView pbar_num;
    @BindView(R.id.home_pbar_rl)
    RelativeLayout pbar_rl;
    private UpDataBean upDataBean;
    private boolean guide = false;
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTopBar(false);
        guide = (Boolean) SharedPreferencesUtils.getParam("guide", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                go();
               }
            }, 500);
    }


    @Override
    public void initData() {}

    @Override
    public int initLayout() {
        return R.layout.act_welcome;
    }


    @Override
    public void onBefore(BaseRequest request) {
        showLoadingDialog();
    }

    @Override
    public void onSuccess(BaseResponse baseResponse, String s, Call call, Response response) {
        if (IURL.URL.equals(response.request().url().toString())){

        }
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        ToastUtils.showShort(response.message());
    }

    @Override
    public void onAfter(String s, Exception e) {
        hideLaodingDialog();
    }


    private void openFile(File file) {
        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            data = FileProvider.getUriForFile(this, "com.test.jianlou.jianlou.fileprovider", file);
            data = FileProvider.getUriForFile(this, "com.test.iview.dayin.imagePicker.provider", file);
        } else {
            String[] s = {"chmod","777",file.getPath()};
            ProcessBuilder processBuilder = new ProcessBuilder(s);
            try{
                processBuilder.start();
            }catch (IOException e){
                e.printStackTrace();
            }
            data = Uri.fromFile(file);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void downLoad() {
        pbar_rl.setVisibility(View.VISIBLE);
        pbar_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        OkGo.get(upDataBean.getInfo().getDownloadurl())
                .tag(this)
                .execute(new FileCallback(this.getApplicationContext().getFilesDir().getAbsolutePath(), "logo.apk") {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        openFile(file);
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                        pbar.setMax(new Long(totalSize / 1000).intValue());
                        pbar.setProgress(new Long(currentSize / 1000).intValue());
                        pbar_num.setText(Math.round(progress * 100) + "%");
                    }
                });
    }

    /**
     * 更新对话框
     */
    private void showNormalDialog(String title, final String button1) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setTitle("软件更新提示:")
                .setMessage(title)
                .setCancelable(false)
                .setPositiveButton("更新",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                                downLoad();
                            }
                        })

                .setNegativeButton("以后再说",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                                if (button1.equals("true")) {
                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_HOME);
                                    startActivity(intent);
                                    System.exit(0);
                                }
                                go();
                            }
                        })
                // 显示
                .show();
    }

    private void go() {
        startActivity(new Intent(SplashsActivity.this, MainActivity.class));
        finish();
    }

    @OnClick({R.id.home_pbar, R.id.home_pbar_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_pbar:
                break;
            case R.id.home_pbar_num:
                break;
        }
    }


}
