package com.test.iview.dayin.wbapi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MultiImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoSourceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.test.iview.dayin.R;
import com.test.iview.dayin.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/23.
 */

public class WBShareActivity extends Activity implements WbShareCallback {
    private WbShareHandler shareHandler;
    private boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        shareHandler.setProgressColor(0xff33b5e5);
        aBoolean = getIntent().getBooleanExtra("WBShareActivity_Imgs",false);
        sendMessage(true, getIntent().getBooleanExtra("WBShareActivity_Img",false));
    }
    private VideoSourceObject getVideoObject(){
        //获取视频
        VideoSourceObject videoSourceObject = new VideoSourceObject();
//        videoSourceObject.videoPath = Uri.fromFile(new File("http:"+substring));
        videoSourceObject.actionUrl=getIntent().getStringExtra("WBShareActivity_Url");
        return videoSourceObject;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent,this);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMessage(boolean hasText, boolean hasImage) {
        sendMultiMessage(hasText, hasImage);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMultiMessage(boolean hasText, boolean hasImage) {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.mediaObject = getTextObj();
//        weiboMessage.videoSourceObject = getVideoObject();
        shareHandler.shareMessage(weiboMessage, false);

    }

    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.title =getIntent().getStringExtra("WBShareActivity_Title");
        textObject.text = getIntent().getStringExtra("WBShareActivity_Text")+getIntent().getStringExtra("WBShareActivity_Url");
//        textObject.actionUrl = getIntent().getStringExtra("WBShareActivity_Url");
        return textObject;
    }
    /**
     * 创建图片消息对象。
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.battery_10);
        imageObject.title =  "最低1元捡漏!"+getIntent().getStringExtra("WBShareActivity_Title");
        imageObject.description = getIntent().getStringExtra("WBShareActivity_Text");
        imageObject.imagePath = getIntent().getStringExtra("WBShareActivity_img");
//        imageObject.setImageObject(bitmap);
        return imageObject;
    }
    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title ="最低一元捡漏"+getIntent().getStringExtra("WBShareActivity_Text")+getIntent().getStringExtra("WBShareActivity_Url");
        mediaObject.description = "测试描述";
        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml";
        mediaObject.defaultText = "Webpage 默认文案";
        return mediaObject;
    }
    /***
     * 创建多图
     * @return
     */
    private MultiImageObject getMultiImageObject(){
        MultiImageObject multiImageObject = new MultiImageObject();
        //pathList设置的是本地本件的路径,并且是当前应用可以访问的路径，现在不支持网络路径（多图分享依靠微博最新版本的支持，所以当分享到低版本的微博应用时，多图分享失效
        // 可以通过WbSdk.hasSupportMultiImage 方法判断是否支持多图分享,h5分享微博暂时不支持多图）多图分享接入程序必须有文件读写权限，否则会造成分享失败
        ArrayList<Uri> pathList = new ArrayList<Uri>();
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/aaa.png")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/bbbb.jpg")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/ccc.JPG")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/ddd.jpg")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/fff.jpg")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/ggg.JPG")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/eee.jpg")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/hhhh.jpg")));
        pathList.add(Uri.fromFile(new File(getExternalFilesDir(null)+"/kkk.JPG")));
        multiImageObject.setImageList(pathList);
        return multiImageObject;
    }
    @Override
    public void onWbShareSuccess() {
        ToastUtils.showToast("分享成功");
        finish();
    }


    @Override
    public void onWbShareCancel() {
        ToastUtils.showToast("取消分享");
        finish();
    }

    @Override
    public void onWbShareFail() {
        ToastUtils.showToast("分享失败");
        finish();
    }
}
