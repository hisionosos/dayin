package com.test.iview.dayin.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ScrollView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.PrintActivity;
import com.test.iview.dayin.activity.ShowBitmapActivity;
import com.test.iview.dayin.view.SingleTouchView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BitmapUtil {

    private final static BitmapUtil bitmapUtil = new BitmapUtil();

    public static BitmapUtil getInstance(){
        return bitmapUtil;
    }
    /**
     * 返回图片宽高数组，第0个是宽，第1个是高
     */
    public static int[] getBitmapWidthHeight(final String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        int[] size = new int[2];
        size[0] = options.outWidth;
        size[1] = options.outHeight;
        return size;
    }

    public static String getPathByUri(Context context, Uri uri) {
        Activity activity = null;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }

        if (activity == null) {
            return null;
        }

        String path = null;
        if (uri == null) {
            return path;
        }
        if ("content".equals(uri.getScheme())) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index);
        } else if ("file".equals(uri.getScheme())) {
            path = uri.getPath();
        }
        return path;
    }

    /**
     * 此方法仅仅用于计算createScaledBitmap() 方法获取Bitmap 时的缩放比例。
     * 目的是为了防止OOM
     */
    private static int getOptionSize(final float size) {
        int optionSize = 1;
        if (size >= 2f && size < 4f) {
            optionSize = 2;
        } else if (size >= 4f && size < 8f) {
            optionSize = 4;
        } else if (size >= 8f && size < 16) {
            optionSize = 8;
        } else if (size >= 16f) {
            optionSize = 16;
        }
        return optionSize;
    }

    /**
     * 保存图片到sdcard
     */
    public static void saveBitmap(final Bitmap bmp, final MyCallback callback) {
        if (callback != null) {
            callback.onPrepare();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File("", fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                    if (callback != null) {
                        callback.onError();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError();
                    }
                }
                String path = file.getAbsolutePath();

                if (callback != null) {
                    callback.onSucceed(path);
                }

            }
        }).start();

    }

    /**
     * 按原图比例缩放裁剪图片
     *
     * @param activity   BaseLibActivity
     * @param imagePath  图片的路径
     * @param imageWidth 想要被缩放到的target图片宽度，我会根据此宽度和原图的比例，去计算target图片高度
     * @param callback   回调监听
     */
    public static void createScaledBitmap(final Activity activity, final String imagePath, final int imageWidth, final MyCallback callback) {
        if (callback == null) {
            throw new NullPointerException("MyCallback must not null");
        }
        callback.onPrepare();

        if (TextUtils.isEmpty(imagePath) || !(new File(imagePath).exists())) {
            callback.onError();
            return;
        }

        //原图宽度大于传入的宽度才压缩，否则不压缩，直接返回原图路径
        final int[] size = getBitmapWidthHeight(imagePath);


        new Thread() {
            @Override
            public void run() {
                super.run();


                int imageHeight = (int) (size[1] * 1f * imageWidth * 1f / size[0] * 1f);
                float scale = size[0] * 1f / imageWidth * 1f;

                BitmapFactory.Options options = new BitmapFactory.Options();
                if (size[0] <= imageWidth) {
                    options.inSampleSize = 1;
                }else{
                    options.inSampleSize = getOptionSize(scale);
                }

                options.inPreferredConfig = Bitmap.Config.RGB_565;
                Bitmap targetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(imagePath, options), imageWidth, imageHeight, true);
                callback.onSucceed(targetBitmap);

            }
        }.start();
    }


    public static Bitmap choosePath(String imagePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        final int[] size = getBitmapWidthHeight(imagePath);

        Bitmap targetBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(imagePath, options), size[0], size[1], true);
        return targetBitmap;
    }
    /**
     * 按原图比例缩放裁剪图片
     *
     * @param activity   BaseLibActivity
     * @param imageUri   图片的Uri
     * @param imageWidth 想要被缩放到的target图片宽度，我会根据此宽度和原图的比例，去计算target图片高度
     * @param callback   回调监听
     */
    public static void createScaledBitmap(final Activity activity, final Uri imageUri, int imageWidth, final MyCallback callback) {
        final String imagePath = getPathByUri(activity, imageUri);
        createScaledBitmap(activity, imagePath, imageWidth, callback);
    }

    /**
     * 返回bitmap 所占内存的大小
     */
    public static int getBitmapBytes(Bitmap bitmap) {
        return bitmap.getByteCount();
    }



    /**
     * 根据目标View的尺寸压缩图片返回bitmap
     * @param resources
     * @param resId
     * @param width 目标view的宽
     * @param height 目标view的高
     * @return
     */
    public static Bitmap decodeBitmapFromResource(Resources resources, int resId, int width , int height){

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(resources,resId,options);
        //获取采样率
        options.inSampleSize = calculateInSampleSize(options,width,height);

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(resources,resId,options);

    }



    public static void cannelEdit(ArrayList<SingleTouchView> arrs, ArrayList<EditText> edt,boolean b){
        if (null != edt){
            for (int i = 0; i <edt.size() ; i++) {
                edt.get(i).setHint("");
                if (!b){
                    edt.get(0).setCursorVisible(b);
                }

            }
        }
        if (null != arrs){
            for (int i = 0; i < arrs.size(); i++) {
                SingleTouchView singleTouchView = arrs.get(i);
                if (null != singleTouchView){
                    arrs.get(i).setEditable(b);
                }
            }
        }

    }

    /**
     * 获取采样率
     * @param options
     * @param reqWidth 目标view的宽
     * @param reqHeight 目标view的高
     * @return 采样率
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        int inSampleSize = 1;


        if (originalHeight > reqHeight || originalWidth > reqHeight){
            int halfHeight = originalHeight / 2;
            int halfWidth = originalWidth / 2;
            //压缩后的尺寸与所需的尺寸进行比较
            while ((halfWidth / inSampleSize) >= reqHeight && (halfHeight /inSampleSize)>=reqWidth){
                inSampleSize *= 2;
            }

        }

        return inSampleSize;

    }

    public void showBitmap(final View dView, final boolean isImg,final int h,final boolean isBiaoqian){
        final ProgressDialog pd = new ProgressDialog(dView.getContext());
        pd.setTitle(dView.getContext().getString(R.string.dy_tips));
        pd.setMessage("请稍候……");
        pd.show();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bmp = dView.getDrawingCache(); // 获取图片
                final String path = BitmapUtil.getInstance().savePicture(bmp,System.currentTimeMillis() + "_logo.png");
                ((Activity)dView.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(dView.getContext(), ShowBitmapActivity.class);
                        intent.putExtra("path",path);
                        intent.putExtra("isImg",isImg);
                        intent.putExtra("h",h);
                        intent.putExtra("isBiaoqian",isBiaoqian);
                        dView.getContext().startActivity(intent);
                        if (null != pd){
                            pd.dismiss();
                        }
                        if (null != dView){
                            dView.destroyDrawingCache(); // 保存过后释放资源
                        }

                        if (null != bmp){
                            bmp.recycle();
                        }
                    }
                });
            }
        }).start();


    }


    public void showScollBitmap(final ScrollView scrollView, final boolean isImg,final int hei){
        final ProgressDialog pd = new ProgressDialog(scrollView.getContext());
        pd.setTitle(scrollView.getContext().getString(R.string.dy_tips));
        pd.setMessage("请稍候……");
        pd.show();

        int h = 0;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }

        try {
            final int finalH = h;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    final Bitmap bitmap = Bitmap.createBitmap(scrollView.getWidth(), finalH,
                            Bitmap.Config.RGB_565);
                    final Canvas canvas = new Canvas(bitmap);
                    scrollView.draw(canvas);

                    final String path = BitmapUtil.getInstance().savePicture(bitmap,System.currentTimeMillis() + "_logo.png");
                    ((Activity)scrollView.getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(scrollView.getContext(), ShowBitmapActivity.class);
                            intent.putExtra("path",path);
                            intent.putExtra("isImg",isImg);
                            intent.putExtra("h",hei);
                            intent.putExtra("isBiaoqian",false);
                            scrollView.getContext().startActivity(intent);
                            if (null != pd){
                                pd.dismiss();
                            }
                            if (null != scrollView){
                                scrollView.destroyDrawingCache(); // 保存过后释放资源
                            }

                            if (null != bitmap){
                                bitmap.recycle();
                            }
                        }
                    });

                }
            }).start();

        } catch (Exception e) {

        }

    }


    public void getwebViewBitmap(final WebView webView){
        final ProgressDialog pd = new ProgressDialog(webView.getContext());
        pd.setTitle(webView.getContext().getString(R.string.dy_tips));
        pd.setMessage("请稍候……");
        pd.show();

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String path = BitmapUtil.getInstance().savePicture(bm,System.currentTimeMillis() + "_logo.png");
                ((Activity)webView.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(webView.getContext(), ShowBitmapActivity.class);
                        intent.putExtra("path",path);
                        intent.putExtra("isImg",true);
                        intent.putExtra("h",0);
                        intent.putExtra("isBiaoqian",false);
                        webView.getContext().startActivity(intent);
                        if (null != pd){
                            pd.dismiss();
                        }
                        if (null != webView){
                            webView.destroyDrawingCache(); // 保存过后释放资源
                        }

                        if (null != bm){
                            bm.recycle();
                        }
                    }
                });

            }
        }).start();
    }


    public String getCutImage(final View dView, final boolean isImg,final int h,final boolean isBiaoqian){
        boolean b = BlueSAPI.getInstance().isConnect();
        if (!b){
            dView.getContext().startActivity(new Intent(dView.getContext(), PrintActivity.class));
            return "";
        }
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        final String fileName = System.currentTimeMillis() + "_screen.png";
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName;
        try {
            final ProgressDialog pd = new ProgressDialog(dView.getContext());
            pd.setTitle(dView.getContext().getString(R.string.dy_tips));
            pd.setMessage("正在打印，请稍候……");
            pd.show();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    // 要在运行在子线程中
                    final Bitmap bmp = dView.getDrawingCache(); // 获取图片
//                    BlueSAPI.getInstance().printContent(pd,dView,dView.getContext(),bmp,5,isImg,h,isBiaoqian);
//                    savePicture(bmp, fileName);// 保存图片
//                    dView.destroyDrawingCache(); // 保存过后释放资源
//                    if (null != bmp){
//                        bmp.recycle();
//                    }


                }
            }).start();

        } catch (Exception e) {
            filePath = "";
        }

        return filePath;
    }

    public static String getBitmapScrollView(final ScrollView scrollView,final boolean isImg,final int hei  ) {
        int h = 0;
        boolean b = BlueSAPI.getInstance().isConnect();
        if (!b){
            scrollView.getContext().startActivity(new Intent(scrollView.getContext(), PrintActivity.class));
            return "";
        }
        final String fileName = System.currentTimeMillis() + "_screen.png";
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }

        try {
            final int finalH = h;
            final ProgressDialog pd = new ProgressDialog(scrollView.getContext());
            pd.setTitle(scrollView.getContext().getString(R.string.dy_tips));
            pd.setMessage("正在打印，请稍候……");
            pd.show();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    Bitmap bitmap = Bitmap.createBitmap(scrollView.getWidth(), finalH,
                            Bitmap.Config.RGB_565);
                    final Canvas canvas = new Canvas(bitmap);
                    scrollView.draw(canvas);


//                    BlueSAPI.getInstance().printContent(pd,scrollView,scrollView.getContext(),bitmap,5,isImg,hei,false);
//                    ToastUtils.showShort("保存成功");


                }
            }).start();

        } catch (Exception e) {
            filePath = "";
        }

        return filePath;

    }

    public static Bitmap toGrayScale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayScale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayScale;
    }



    public String savePicture(Bitmap bm, String fileName) {
        if (null == bm) {
            Log.i("savePicture", "---图片为空------");
            return null;
        }

        Log.e("path", Environment.getExternalStorageDirectory().getAbsolutePath() );
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
            bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return myCaptureFile.getAbsolutePath();
    }


    public interface MyCallback {
        void onPrepare();
        void onSucceed(Object object);
        void onError();
    }



    public static Bitmap imageoperation (Bitmap mbitmap ,float hue,float saturation,float lum){
        //传入的Bitmap默认不可修改，需啊哟创建新的Bitmap
        Bitmap mbitmap_fu=Bitmap.createBitmap(mbitmap.getWidth(),mbitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建画布，在新的bitmap上绘制
        Canvas canvas=new Canvas(mbitmap_fu);
        //设置画笔抗锯齿，后面在Bitmap上绘制需要使用到画笔
        Paint mpaint=new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix huematrix=new ColorMatrix();
        huematrix.setRotate(0,hue);
        huematrix.setRotate(1,hue);
        huematrix.setRotate(2, hue);

        ColorMatrix saturationmatrix=new ColorMatrix();
        saturationmatrix.setSaturation(saturation);

//        ColorMatrix lummatrix=new ColorMatrix();
        //参数：rscale gscale bscale 透明度
//        lummatrix.setScale(lum,lum,lum,1);

        ColorMatrix imagematrix=new ColorMatrix();
        imagematrix.postConcat(huematrix);
        imagematrix.postConcat(saturationmatrix);
//        imagematrix.postConcat(lummatrix);
        //通过画笔的setColorFilter进行设置
        mpaint.setColorFilter(new ColorMatrixColorFilter(imagematrix));
        canvas.drawBitmap(mbitmap,0,0,mpaint);
        return mbitmap_fu;
    }


}


