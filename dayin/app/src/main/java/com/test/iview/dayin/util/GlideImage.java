package com.test.iview.dayin.util;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.iview.dayin.global.MyApplication;

import java.io.File;

/**
 * 加载图片
 * Created by zou on 201/11/19.
 */

public class GlideImage {
    public static void loaderImage(String url, ImageView imageView){
        Glide.with(MyApplication.getContext())
                .load(url)
                .asBitmap()
                .fitCenter()
//                .placeholder(R.color.f0)//加载中图片//
//                .error(R.drawable.play_check)
//                .fitCenter()
//                .centerCrop()
//                .crossFade(1000)//动画
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .priority(Priority.HIGH)
                .into(imageView);
    }
    public static void loaderImageFile(File file, ImageView imageView){
        Glide.with(MyApplication.getContext())
                .load(file)
//                .placeholder(R.drawable.play_uncheck)//加载中图片
//                .error(R.drawable.play_uncheck)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imageView);
    }
    public static void loaderImageUri(Uri uri, ImageView imageView){
        Glide.with(MyApplication.getContext())
                .load(uri)
//                .placeholder(R.drawable.play_uncheck)//加载中图片
//                .error(R.drawable.play_uncheck)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imageView);
    }
}
