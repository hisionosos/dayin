package com.test.iview.dayin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.entity.InstallBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.util.TimeUtile;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.clip.ClipActivity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\2 0002.
 * 设置
 */

public class InstallActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title1;
    @BindView(R.id.install_zizhi_tv)
    TextView install_zizhi_tv;
    @BindView(R.id.install_bangding_tv)
    TextView install_bangding_tv;
    TextView insallTime;
    @BindView(R.id.install_pic)
    SimpleDraweeView installPic;
    @BindView(R.id.install_pic111)
    ImageView install_pic1;
    @BindView(R.id.install_name)
    EditText installName;


    private InstallBean bean;
    ImagePicker imagePicker;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_install;
    }

    private void initView() {

        imagePicker = new ImagePicker();
        imagePicker.setTitle("设置头像");
// 设置是否裁剪图片
        imagePicker.setCropImage(true);

        title1.setText("设置");
        insallTime = (TextView) findViewById(R.id.install_time111);
        request();
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
    }

    private void request() {

        OkGo.post(IURL.Install)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, InstallBean.class);
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
//                                GlideImage.loaderImage(bean.getInfo().getAvatar(), installPic);
                                GlideImage.loaderImage(bean.getInfo().getAvatar(), install_pic1);
                                installName.setText(bean.getInfo().getUname());
                                install_zizhi_tv.setText(bean.getInfo().getDress());
                                install_bangding_tv.setText(bean.getInfo().getTel());
                                if (bean.getInfo().getBirth().toString() != null) {
                                    insallTime.setText(bean.getInfo().getBirth().toString());
                                }
                            } else {
                                ToastUtils.showToast(bean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }

                    }
                });
    }

    @OnClick({R.id.fanhui, R.id.install_bangding, R.id.install_touxiang, R.id.install_dizhi, R.id.install_shengri, R.id.install_baocun, R.id.install_tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.install_bangding:
                startActivity(new Intent(MyApplication.getContext(), BindingPhoneActivity.class));
                break;
            case R.id.install_dizhi:
                if ("".equals(bean.getInfo().getDress())) {
                    startActivity(new Intent(this, AddAddressActivity.class));
                } else {
                    startActivity(new Intent(this, AddressActivity.class));
                }
                break;
            case R.id.install_touxiang:
//                AlertDialog.Builder dialog = getListDialogBuilder(
//                        this, items, title, dialogListener);
//                dialog.show();
                startChooser();
                break;
            case R.id.install_shengri:
                Calendar calendar = Calendar.getInstance();
                Calendar calendar1 = Calendar.getInstance();
                calendar.set(1950, 0, 1);
                calendar1.set(2017, 11, 31);
                final TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        insallTime.setText(TimeUtile.getDateToStringNianYueRi(date.getTime()));
                    }
                }).setRangDate(calendar, calendar1)
                        .build();
                pvTime.show();
                break;
            case R.id.install_baocun:
                baoCun();
                break;
            case R.id.install_tuichu:
                MyApplication.userToken = null;
                SharedPreferences settings = getSharedPreferences("userToken", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("userToken", MyApplication.userToken);
                editor.commit();
                finish();
//                Intent mStartActivity = new Intent(this, MainActivity.class);
//                int mPendingIntentId = 123456;
//                PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
//                AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
//                System.exit(0);
                break;
        }
    }

    private File file;

    private void baoCun() {

        PostRequest postRequest = OkGo.post(IURL.Install_UpDate)
                .tag(this)
                .params("user_id", MyApplication.userToken);
        if (file != null) {
            postRequest.params("avatar", file);
        }
        postRequest.params("uname", installName.getText().toString())
                .params("birth", insallTime.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                        if (baseBean != null) {
                            if (baseBean.getStatus() == 0) {
                                finish();
                            }
                            ToastUtils.showToast(baseBean.getMsg());
                        } else {
                            finish();
                            ToastUtils.showToast("");
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        finish();
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    private void startPhotoZoom(String path) {
        Intent intent = new Intent(this, ClipActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, 5);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void startChooser() {
        // 启动图片选择器
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            // 选择图片回调
            @Override
            public void onPickImage(Uri imageUri) {

            }

            // 裁剪图片回调
            @Override
            public void onCropImage(Uri imageUri) {
                install_pic1.setVisibility(View.GONE);
                installPic.setVisibility(View.VISIBLE);
                installPic.setImageURI(imageUri);
                installPic.getHierarchy().setRoundingParams(RoundingParams.asCircle());
                try {
                    file = new File(new URI(imageUri.toString()));
                    String path = file.getPath();
//                    GlideImage.loaderImage(file.getPath(), installPic);
//                    installPic.setImageURI(imageUri);
                    Log.e("哈哈", path);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    Log.e("哈哈", "出错了");
                }
            }

            // 自定义裁剪配置
            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(true)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape.OVAL)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(960, 540)
                        // 宽高比
                        .setAspectRatio(9, 9);
            }

            // 用户拒绝授权回调
            @Override
            public void onPermissionDenied(int requestCode, String[] permissions,
                                           int[] grantResults) {
            }
        });
    }
}
