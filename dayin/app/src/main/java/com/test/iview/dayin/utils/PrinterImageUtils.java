package com.test.iview.dayin.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;

public class PrinterImageUtils {

    public PrinterImageUtils() {}

    public static Bitmap imageFloydSteinberg(Bitmap bitmap) {
        int[] bitcount = new int[bitmap.getByteCount()];
        int[] bitcount1 = new int[bitmap.getByteCount()];
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        bitmap.getPixels(bitcount, 0, width, 0, 0, width, height);
        float[][] bitarr = (float[][]) Array.newInstance(Float.TYPE, new int[]{height, width});
        int[][] bitarr1 = (int[][])Array.newInstance(Integer.TYPE, new int[]{height, width});
        float e = 0.0F;

        int h;
        int w;
        for(h = 0; h < height; ++h) {
            for(w = 0; w < width; ++w) {
                bitarr[h][w] = (float)(bitcount[h * width + w] & 255);
                e += bitarr[h][w];
            }
        }

        e = e / (float)width / (float)height;
        if (e < 78.795F) { }

        if (e > 176.205F) { }

        for(h = 0; h < height; ++h) {
            for(w = 0; w < width; ++w) {
                float[] hcount = bitarr[h];
                hcount[w] += 0.0F;
                if (bitarr[h][w] > 127.5F) {
                    bitarr1[h][w] = 0;
                    bitcount1[h * width + w] = -1;
                    e = bitarr[h][w] - 255.0F;
                } else {
                    bitarr1[h][w] = 1;
                    bitcount1[h * width + w] = -16777216;
                    e = bitarr[h][w] - 0.0F;
                }

                hcount = bitarr[h];
                int var4 = w + 1;

                try {
                    hcount[var4] += 32.0F * e / (float)210;
                    hcount = bitarr[h];
                    var4 = w + 2;

                    hcount[var4] += 16.0F * e / (float)210;
                    hcount = bitarr[h];
                    var4 = w + 3;

                    hcount[var4] += 8.0F * e / (float)210;

                    int var7;
                    for(var4 = -3; var4 < 4; ++var4) {
                        if (h + 1 < height){
                            hcount = bitarr[h + 1];
                            var7 = w + var4;
                            hcount[var7] += (float)Math.pow(2.0D, (double)(5 - Math.abs(var4))) * e / (float)210;

                        }

                    }

                    for(var4 = -3; var4 < 4; ++var4) {
                        if (h + 2 < height){
                            hcount = bitarr[h + 2];
                            var7 = w + var4;
                            hcount[var7] += (float)Math.pow(2.0D, (double)(4 - Math.abs(var4))) * e / (float)210;

                        }

                    }

                    for(var4 = -3; var4 < 4; ++var4) {
                        if (h + 3 < height){
                            hcount = bitarr[h + 3];
                            var7 = w + var4;
                            hcount[var7] += (float)Math.pow(2.0D, (double)(3 - Math.abs(var4))) * e / (float)210;

                        }

                    }


                } catch (Exception e1) {
                    e1.getStackTrace();
                }

            }
        }

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(bitcount1, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static byte[] bitmapToByteArray(String var0) {
        Bitmap var2 = getSmallBitmap(var0);
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        var2.compress(Bitmap.CompressFormat.JPEG, 40, var1);
        return var1.toByteArray();
    }

    public static String bitmapToString(String var0) {
        Bitmap var2 = getSmallBitmap(var0);
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        var2.compress(Bitmap.CompressFormat.JPEG, 40, var1);
        return Base64.encodeToString(var1.toByteArray(), 0);
    }

    public static int calculateInSampleSize(BitmapFactory.Options bitpam, int width) {
        int outHeight = bitpam.outHeight;
        int outWidth = bitpam.outWidth;
        int size = 1;
        int per = (int)((float)width / (float)outWidth * (float)outHeight);
        if (outHeight > per || outWidth > width) {
            size = Math.round((float)outHeight / (float)per);
            width = Math.round((float)outWidth / (float)width);
            if (size >= width) {
                return width;
            }
        }

        return size;
    }

    public static Bitmap convertToBlackWhite(Bitmap var0 ,int h) {
//        ToastUtils.showShort("数据处理中...");
        int var3 = var0.getWidth();
        int var4 = var0.getHeight();
        int[] var6 = new int[var3 * var4];
        var0.getPixels(var6, 0, var3, 0, 0, var3, var4);

        int var1;
        int var2;
        for(var1 = 0; var1 < var4; ++var1) {
            for(var2 = 0; var2 < var3; ++var2) {
                int var5 = var6[var3 * var1 + var2];
                var5 = (int)((double)((16711680 & var5) >> 16) * 0.3D + (double)(('\uff00' & var5) >> 8) * 0.59D + (double)(var5 & 255) * 0.11D);
                var6[var3 * var1 + var2] = var5 | var5 << 16 | -16777216 | var5 << 8;
            }
        }

        var0 = Bitmap.createBitmap(var3, var4, Bitmap.Config.RGB_565);
        var0.setPixels(var6, 0, var3, 0, 0, var3, var4);
        return var0;
//        var2 = (int)(384.0F / (float)var3 * (float)var4);
//        var1 = var2;
//        if (var2 == 682) {
//            var1 = 681;
//        }
//
//        if (h > 0){
//            var1 = h;
//        }
//        return ThumbnailUtils.extractThumbnail(var0, 384, var1);
    }


    public static Bitmap resizeImage(Bitmap bitmap, int w,int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = (int)(((float)newWidth / (float)width * (float)height));


        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        if (h > 0){
            float sw = ((float) newWidth) / width;
            float sh = ((float) h) / height;
            matrix.postScale(sw, sh);
        }else{
            matrix.postScale(scaleWidth, scaleHeight);
        }

        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Log.e("oodododod",width + "");
        Log.e("oodododod1",height + "");

        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);

        return resizedBitmap;
    }

    public static Bitmap convertToBlackWhiteLogo(Bitmap var0) {
        int var3 = var0.getWidth();
        int var4 = var0.getHeight();
        int[] var6 = new int[var3 * var4];
        var0.getPixels(var6, 0, var3, 0, 0, var3, var4);

        for(int var1 = 0; var1 < var4; ++var1) {
            for(int var2 = 0; var2 < var3; ++var2) {
                int var5 = var6[var3 * var1 + var2];
                var5 = (int)((double)((16711680 & var5) >> 16) * 0.3D + (double)(('\uff00' & var5) >> 8) * 0.59D + (double)(var5 & 255) * 0.11D);
                var6[var3 * var1 + var2] = var5 | var5 << 16 | -16777216 | var5 << 8;
            }
        }

        var0 = Bitmap.createBitmap(var3, var4, Bitmap.Config.RGB_565);
        var0.setPixels(var6, 0, var3, 0, 0, var3, var4);
        return ThumbnailUtils.extractThumbnail(var0, 120, 120);
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


    public static Bitmap getSmallBitmap(String param0) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(param0, options);
        options.inSampleSize = calculateInSampleSize(options, 374);//自定义一个宽和高
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(param0, options);

        return imageFloydSteinberg(convertToBlackWhite(bitmap,0));
    }

    public static Bitmap getVideoThumbnail(String var0, int var1, int var2) {
        return ThumbnailUtils.extractThumbnail(ThumbnailUtils.createVideoThumbnail(var0, 1), var1, var2, 2);
    }

    public static void storeImage(Bitmap var0, String var1) throws FileNotFoundException {
        FileOutputStream var2 = new FileOutputStream(var1);
        var0.compress(Bitmap.CompressFormat.JPEG, 100, var2);
    }


    public static Bitmap convertGreyImgByFloyd(Bitmap img) {
        int width = img.getWidth();
        int height = img.getHeight();

        int[] pixels = new int[width * height]; //通过位图的大小创建像素点数组  
        img.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] gray=new int[height*width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];
                int red = ((grey&0x00FF0000) >> 16);
                gray[width*i+j]=red;
            }
        }


        int e=0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int g=gray[width*i+j];
                if (g>=128) {
                    pixels[width*i+j]=0xffffffff;
                    e=g-255;
                }else {
                    pixels[width*i+j]=0xff000000;
                    e=g-0;
                }
//                if (j<width-1&&i<height-1) {
////右边像素处理
//                    gray[width*i+j+1]+=3*e/8;
////下
//                    gray[width*(i+1)+j]+=3*e/8;
////右下
//                    gray[width*(i+1)+j+1]+=e/4;
//                }else if (j==width-1&&i<height-1) {//靠右或靠下边的像素的情况
////下方像素处理
//                    gray[width*(i+1)+j]+=3*e/8;
//                }else if (j<width-1&&i==height-1) {
////右边像素处理
//                    gray[width*(i)+j+1]+=e/4;
//                }
            }
        }

        Bitmap mBitmap=Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return mBitmap;
    }

}
