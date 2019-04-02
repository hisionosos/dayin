package com.test.iview.dayin.wxapi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Administrator on 2017/11/23.
 */

public class WeiXinUtil {
    private static WXMediaMessage msg;
    private static boolean isPengYouQuan;
    public static void ShiPin(String title, String data, String img, String url, boolean isPengYouQuan1) {
        WXVideoObject videp = new WXVideoObject();
        videp.videoUrl = url;
        msg = new WXMediaMessage(videp);
        msg.title = title;
        msg.description = data;
        isPengYouQuan = isPengYouQuan1;
        GetLocalOrNetBitmap(img);
    }

    private static String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static void wangye(String url, String title, String describe, boolean isPengYouQuan1,String img) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = url;
        msg = new WXMediaMessage(webpageObject);
        msg.title = title;
        msg.description = describe;
        isPengYouQuan = isPengYouQuan1;

        GetLocalOrNetBitmap(img);
    }
    public static void wangye1(String url, String title, String describe, boolean isPengYouQuan1,String img) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = url;
        msg = new WXMediaMessage(webpageObject);
        msg.title = title;
        msg.description = describe;
        isPengYouQuan = isPengYouQuan1;

        GetLocalOrNetBitmap(img);
    }

    public static void wenBen(String text, boolean isPengYouQuan) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = text;
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = text;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        if (isPengYouQuan) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
//        MyApplication.mWxApi.sendReq(req);
    }

//    public static void tuPian(String imgPath, boolean isPengYouQuan) {
//        Bitmap bitmap = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.ic_launcher);
//        WXImageObject imgObj = new WXImageObject();
//        imgObj.setImagePath(imgPath);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imgObj;
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
//        bitmap.recycle();
//        msg.thumbData = Utiles.bmpToByteArray(thumbBmp, true);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("img");
//        req.message = msg;
//        if (isPengYouQuan) {
//            req.scene = SendMessageToWX.Req.WXSceneTimeline;
//        } else {
//            req.scene = SendMessageToWX.Req.WXSceneSession;
//        }
////        MyApplication.mWxApi.sendReq(req);
//    }

    private static Bitmap bitmap1 = null;
    @SuppressLint("HandlerLeak")
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg1) {
            super.handleMessage(msg1);
            if (msg1.what == 0) {

////        Bitmap bitmap = BitmapFactory.decodeResource(KuaiQiBuApplication.ctx.getResources(), R.drawable.ic_launcher);
//                Bitmap bit = Bitmap.createScaledBitmap(bitmap1, 120, 120, true);//压缩Bitmap
//                msg.thumbData = Utiles.bmpToByteArray(bit, true);
////                bit.recycle();
//                SendMessageToWX.Req req = new SendMessageToWX.Req();
//                req.transaction = buildTransaction("webpage");
//                req.message = msg;
//                if (isPengYouQuan) {
//                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                } else {
//                    req.scene = SendMessageToWX.Req.WXSceneSession;
//                }
////                MyApplication.mWxApi.sendReq(req);
            } else {
//                Bitmap bit = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.ic_launcher);
////                Bitmap bit =Bitmap.createScaledBitmap(bitmap1, 120, 120, true);//压缩Bitmap
//                msg.thumbData = Utiles.bmpToByteArray(bit, true);
//                SendMessageToWX.Req req = new SendMessageToWX.Req();
//                req.transaction = buildTransaction("webpage");
//                req.message = msg;
//                if (isPengYouQuan) {
//                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                } else {
//                    req.scene = SendMessageToWX.Req.WXSceneSession;
//                }
////                MyApplication.mWxApi.sendReq(req);
            }
        }
    };

    public static void GetLocalOrNetBitmap(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = null;
                    InputStream in = null;
                    BufferedOutputStream out = null;
                    in = new BufferedInputStream(new URL(url).openStream(), 1024);
                    final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
                    out = new BufferedOutputStream(dataStream, 1024);
                    copy(in, out);
                    out.flush();
                    byte[] data = dataStream.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    data = null;
                    Message msg = new Message();
                    msg.what = 0;
                    bitmap1 = bitmap;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }
}
