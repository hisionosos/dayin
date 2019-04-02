package com.test.iview.dayin.util;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.test.iview.dayin.entity.QqBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/25.
 */

public class QqShare {
    private static QqBean qqBean;
    private static IUiListener iUiListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            qqBean = JsonUtil.parseJsonToBean(o.toString(), QqBean.class);
            if (qqBean.getRet() == 0) {
                ToastUtils.showToast("分享成功");
            } else {
                ToastUtils.showToast("bean分享失败" + qqBean.getRet() + "-" + qqBean.getMsg());
            }
        }

        @Override
        public void onError(UiError uiError) {
            ToastUtils.showToast("分享失败" + uiError.errorCode + "-" + uiError.errorMessage);
        }

        @Override
        public void onCancel() {

        }
    };

    public static void qqShareToTuWen(Activity context, String title, String content, String url, String image) {
        Tencent mTencent = Tencent.createInstance(IURL.QQAppId, context);
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,content);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        if (image != null) {
            if (image.length()>0) {
                ArrayList<String> imglist = new ArrayList<>();
                imglist.add(image);
                params.putStringArrayList(QQShare.SHARE_TO_QQ_IMAGE_URL, imglist);
            }else{
//                ArrayList<String> imglist = new ArrayList<>();
//                imglist.add("https://www.e-carhome.com/qiche/sys/logo.png");
//                params.putStringArrayList(QQShare.SHARE_TO_QQ_IMAGE_URL, imglist);
            }
        }else{
//            ArrayList<String> imglist = new ArrayList<>();
//            imglist.add("https://www.e-carhome.com/qiche/sys/logo.png");
//            params.putStringArrayList(QQShare.SHARE_TO_QQ_IMAGE_URL, imglist);
        }
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        mTencent.shareToQQ(context, params, iUiListener);
    }

    public static void qzoneShareToTuWen(Activity context, String title, String content, String url, String image) {
        Tencent mTencent = Tencent.createInstance(IURL.QQAppId, context);
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, content);
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);
        if (image != null) {
            if (image.length()>0) {
                ArrayList<String> imglist = new ArrayList<>();
                imglist.add(image);
                params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imglist);
            }else{
//                ArrayList<String> imglist = new ArrayList<>();
//                imglist.add("https://www.e-carhome.com/qiche/sys/logo.png");
//                params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imglist);
            }
        }else{
//            ArrayList<String> imglist = new ArrayList<>();
//            imglist.add("https://www.e-carhome.com/qiche/sys/logo.png");
//            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imglist);
        }
        mTencent.shareToQzone(context, params, iUiListener);
    }

}
