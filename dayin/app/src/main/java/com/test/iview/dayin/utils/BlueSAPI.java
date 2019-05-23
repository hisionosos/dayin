package com.test.iview.dayin.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sina.weibo.sdk.utils.ImageUtils;
import com.test.iview.dayin.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.Vector;

public class BlueSAPI {

        private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        private BluetoothDevice device;
        private BluetoothSocket btSocket = null;
        private OutputStream outStream = null;
        private InputStream inStream = null;
        private BluetoothAdapter mBtAdapter = null;
        private byte[] sendbyte = null;

        private boolean isConnect = false;
        private final static BlueSAPI blueApi = new BlueSAPI();

        public static BlueSAPI getInstance(){
            return blueApi;
        }

        public BlueSAPI() { }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public interface CallBack{
            void predo();
            void success();
            void errors();
        }

        public int openPrinter(String Deviveid, String Pwd, final CallBack callBack) {
            if (null != callBack){
                callBack.predo();
            }
            int res = 0;
            this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
            this.device = this.mBtAdapter.getRemoteDevice(Deviveid);
            if (this.device.getBondState() != 12) {
                try {
//                    this.autoBond(this.device.getClass(), this.device, Pwd);
//                    this.createBond(this.device.getClass(), this.device);
                    ClsUtils.createBond(device.getClass(), device);

                } catch (Exception var6) {
                    System.out.println(R.string.dy_blue_error);
                    isConnect = false;
                    return  2003;
                }
                //连接监听
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (device.getBondState() != 12){
                            Log.e("Thread","Theeeeeeread");
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (device.getBondState() == 12){
                                connectDevice(callBack);

                            }


                        }
                    }
                }).start();
            }else{
                connectDevice(callBack);
            }



            return res;
        }

        private int connectDevice(CallBack callBack){
            int res = 0;
            try {
                this.btSocket = this.device.createRfcommSocketToServiceRecord(MY_UUID);
                this.btSocket.connect();
                this.outStream = this.btSocket.getOutputStream();
                this.inStream = this.btSocket.getInputStream();
                isConnect = true;
                if (null != callBack){
                    callBack.success();
                }
            } catch (IOException var5) {
                var5.printStackTrace();
                res = 2002;
                isConnect = false;
                if (null != callBack){
                    callBack.errors();
                }

            }

            return res;
        }


        String getPrinterVersion() {
            return "1.0.0";
        }

        public int PrintLn() {
            return this.PrintByte(new byte[]{10});
        }

        public int SetdefalutLineSpacing() {
            return this.PrintByte(new byte[]{27, 50});
        }

        public int initPrinter() {
            return this.PrintByte(new byte[]{27, 64});
        }

        public int SetLineSpacing(int size) {
            return this.PrintByte(new byte[]{27, 51, (byte)size});
        }

        public int SetPrintmode(int size) {
            return this.PrintByte(new byte[]{27, 33, (byte)size});
        }

        public int TransverseZoomCharacter(int size) {
            return this.PrintByte(new byte[]{27, 85, (byte)size});
        }

        public int VerticalZoomCharacter(int size) {
            return this.PrintByte(new byte[]{27, 86, (byte)size});
        }

        public int SetZoomCharacter(int size) {
            return this.PrintByte(new byte[]{27, 88, (byte)size});
        }

        public int setZoonIn(int widthZoonIn, int heightZoonIn) {
            this.VerticalZoomCharacter(widthZoonIn);
            this.SetZoomCharacter(heightZoonIn);
            return 0;
        }

        public int PrintFeed(int size) {
            return this.PrintByte(new byte[]{27, 74, (byte)size});
        }

        public int PrintFeedLines(int size) {
            return this.PrintByte(new byte[]{27, 100, (byte)size});
        }

        public int SetERightMargin(int size) {
            return this.PrintByte(new byte[]{27, 32, (byte)size});
        }

        public int SetKeyswitch(int size) {
            return this.PrintByte(new byte[]{27, 99, 53, (byte)size});
        }

        public int SetSleepmode(int size) {
            return this.PrintByte(new byte[]{27, 83, 69, (byte)size});
        }

        public int SetSleeplatency(int size) {
            return this.PrintByte(new byte[]{27, 83, 84, (byte)size});
        }

        public int Wakeup() {
            return this.PrintByte(new byte[]{31});
        }

        public int SetPictureLeftmargin(int n) {

            int lm = n / 256;
            int ln = n % 256;
            return this.PrintByte(new byte[]{27, -128, (byte)ln, (byte)lm});
        }

        public int PrintPoint(int m, int l, int h, byte[] Point) {
            this.PrintByte(new byte[]{27, 42, (byte)m, (byte)l, (byte)h});
            this.PrintByte(Point);
            return 0;
        }

        public int Extendedcommands(int size) {
            return this.PrintByte(new byte[]{27, 35, (byte)size});
        }

        public int PrintTwoCode(String code) {
            byte[] sd = null;

            try {
                sd = code.getBytes("GBK");
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }

            if (sd == null) {
                return 2004;
            } else {

                int lm = sd.length / 256;
                int ln = sd.length % 256;
                this.PrintByte(new byte[]{27, 90, 0, 2, 4, (byte)ln, (byte)lm});
                return this.PrintByte(sd);
            }
        }

        public int SetLeftmargin(int n) {

            int lm = n / 256;
            int ln = n % 256;
            return this.PrintByte(new byte[]{27, -128, (byte)ln, (byte)lm});
        }

        public int SetRightmargin(int size) {
            return this.PrintByte(new byte[]{27, 81, (byte)size});
        }

        public int PrintSpace(int m, int n) {
            return this.PrintByte(new byte[]{27, 98, (byte)m, (byte)n});
        }

        public int SetCenter(int size) {
            return this.PrintByte(new byte[]{27, 67, (byte)size});
        }

        public int CheckPrinterStatus() {
            if (this.mBtAdapter != null && this.mBtAdapter.isEnabled()) {
                if (this.outStream == null) {
                    return 2007;
                } else {
                    try {
                        this.outStream.write(new byte[]{27, 118});
                        this.outStream.flush();
                        byte[] read = new byte[20];
                        this.inStream.read(read);
                        System.out.println("" + read);
                    } catch (UnsupportedEncodingException var2) {
                        var2.printStackTrace();
                    } catch (IOException var3) {
                        var3.printStackTrace();
                    }

                    return this.PrintByte(new byte[]{27, 118});
                }
            } else {
                return 2007;
            }
        }

        public int SetLRmargins(int m, int n) {
            return this.PrintByte(new byte[]{28, 83, (byte)m, (byte)n});
        }

        public int SetOnedimCodeHight(int size) {
            return this.PrintByte(new byte[]{29, 104, (byte)size});
        }

        public int SetOnedimCodeWidth(int size) {
            return this.PrintByte(new byte[]{27, 119, (byte)size});
        }

        public int SetOnedimCodeLeftmargin(int size) {
            return this.PrintByte(new byte[]{29, 120, (byte)size});
        }

        public int PrintOnedimCode(int width, int height, int lmargin, String code, int type) {
            if (height != 0) {
                this.SetOnedimCodeHight(height);
            }

            if (width != 0) {
                this.SetOnedimCodeWidth(width);
            }

            if (lmargin != 0) {
                this.SetOnedimCodeLeftmargin(lmargin);
            }

            byte[] sd = code.getBytes();
            int lm = sd.length / 256;
            int ln = sd.length % 256;
            byte m = 0;
            switch(type) {
                case 0:
                    m = 69;
                    break;
                case 1:
                    m = 73;
                    break;
                case 2:
                    m = 68;
                    break;
                case 3:
                    m = 67;
            }

            int n = code.length();
            this.PrintByte(new byte[]{29, 107, m, (byte)n});
            return this.PrintByte(code.getBytes());
        }

        private void PrintImaageHead(int weight) {
            byte[] head = new byte[]{27, 42, 33, 0, 0};
            if (weight > 255) {
                head[3] = (byte)(weight - 256);
                head[4] = 1;
            } else {
                head[3] = (byte)weight;
                head[4] = 0;
            }

            this.PrintByte(head);
        }

        @SuppressLint({"NewApi"})
        public int PrintImage(Bitmap bmp , boolean isBiaoqian) {
            if (bmp == null) {
                return -1;
            } else {
                byte[] var10000 = new byte[]{27, 74, 0};
                int[] pixels = new int[bmp.getByteCount()];
                bmp.getPixels(pixels, 0, bmp.getWidth(), 0, 0, bmp.getWidth(), bmp.getHeight());
                int aHeight = bmp.getHeight();
                int aWeight = bmp.getWidth();
                int height = aHeight;
                int weight = aWeight;
                if (aHeight % 24 != 0) {
                    int modh = aHeight % 24;
                    height = aHeight + (24 - modh);
                }

                if (aWeight % 8 != 0) {
                    int modw = aWeight % 8;
                    weight = aWeight + 8 - modw;
                }

                int width = weight * 3;
                byte[] body = new byte[width];

                for(int i = 0; i < height; i += 24) {
                    this.PrintImaageHead(weight);

                    for(int j = 0; j < width; ++j) {
                        for(int k = 0; k < 24; ++k) {
                            int r;
                            int g;
                            int b;
                            if (i + k < aHeight && j / 3 < aWeight) {
                                int color = pixels[(i + k) * aWeight + j / 3];
//                                r = 255 & color;
//                                g = ('\uff00' & color) >> 8;
//                                b = (16711680 & color) >> 16;
                                r = Color.red(color);
                                g = Color.green(color);
                                b = Color.blue(color);
                            } else {
                                r = 250;
                                g = 250;
                                b = 250;
                            }

                            if (k == 8 || k == 16) {
                                ++j;
                            }

                            if (r < 150 && g < 150 && b < 150) {
                                body[j] = (byte)(body[j] * 2 + 1);
                            } else {
                                body[j] = (byte)(body[j] * 2);
                            }
                        }
                    }

                    this.PrintByte(body);

                    try {
                        int count = 0;
                        while(count == 0 && isConnect){
                            count = inStream.available();
                            Log.e("dedd",count + "");

                        }
                        if(count != 0) {
                            byte[] bt = new byte[count];
                            int readCount = 0;
                            while (readCount < count) {
                                readCount += inStream.read(bt, readCount, count - readCount);
                            }

//                            String xx = new String(bt);
//                            System.out.println(xx);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                Log.e("printwriteCount",writeCount + "");
                PrintLn();
                PrintLn();
                PrintLn();
                PrintLn();
                if (!isBiaoqian){
                    PrintLn();
                    PrintLn();
                    PrintLn();
                }

                return 0;
            }
        }



    public  void draw(Bitmap bit) {
        int aHeight = bit.getHeight();
        int aWeight = bit.getWidth();
        int height = aHeight;
        int weight = aWeight;
//        if (aHeight % 24 != 0) {
//            int modh = aHeight % 24;
//            height = aHeight + (24 - modh);
//        }
//
//        if (aWeight % 8 != 0) {
//            int modw = aWeight % 8;
//            weight = aWeight + 8 - modw;
//        }
//        int width = weight * 3;
        byte[] data = new byte[16920];

//        byte[] data = new byte[16290];
        int k = 0;
        for (int j = 0; j < height/24; j++) {
            data[k++] = 0x1B;
            data[k++] = 0x2A;
            data[k++] = 33; // m=33时，选择24点双密度打印，分辨率达到200DPI。
            data[k++] = 0x68;
            data[k++] = 0x01;
            for (int i = 0; i < weight; i++) {
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 8; n++) {
                        byte b = px2Byte(i, j * 24 + m * 8 + n, bit);
                        data[k] += data[k] + b;
                    }
                    k++;
                }
            }
            data[k++] = 10;
        }
        this.PrintByte(data);
    }


    /**
     * 图片二值化，黑色是1，白色是0
     * @param x  横坐标
     * @param y         纵坐标
     * @param bit 位图
     * @return
     */
    public static byte px2Byte(int x, int y, Bitmap bit) {
        byte b;
        int pixel = bit.getPixel(x, y);
        int red = (pixel & 0x00ff0000) >> 16; // 取高两位
        int green = (pixel & 0x0000ff00) >> 8; // 取中两位
        int blue = pixel & 0x000000ff; // 取低两位
        int gray = RGB2Gray(red, green, blue);
        if ( gray < 128 ){
            b = 1;
        } else {
            b = 0;
        }
        return b;
    }

    /**
     * 图片灰度的转化
     * @param r
     * @param g
     * @param b
     * @return
     */
    private static int RGB2Gray(int r, int g, int b){
        int gray = (int) (0.29900 * r + 0.58700 * g + 0.11400 * b);  //灰度转化公式
        return  gray;
    }


        public int Print(String content) {
            try {
                return this.PrintByte(content.getBytes("GBK"));
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
                return 2000;
            }
        }

        public void closePrinter() {
            if (this.mBtAdapter != null) {
                this.mBtAdapter.cancelDiscovery();
                isConnect = false;
                try {
                    if (this.outStream != null) {
                        this.outStream.close();
                    }

                    if (this.btSocket != null) {
                        this.btSocket.close();
                    }
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }

        }



        private boolean autoBond(Class btClass, BluetoothDevice device, String strPin) throws Exception {
            Method autoBondMethod = btClass.getMethod("setPin", byte[].class);
            Boolean result = (Boolean)autoBondMethod.invoke(device, strPin.getBytes());
            return result;
        }

        private boolean createBond(Class btClass, BluetoothDevice device) throws Exception {
            Method createBondMethod = btClass.getMethod("createBond");
            Boolean returnValue = (Boolean)createBondMethod.invoke(device);
            return returnValue;
        }



    /**
     * 打印
     * @param type 打印内容类型
     */
    public void printContent(final Bitmap bitmap, final int type, final boolean isImg, final int h, final boolean isBiaoqian) {

            /**
             * 初始化打印机 ，带格式的数据打印完成后一定要设置回去否则以后打印的文字都会带次格式
             */
            blueApi.initPrinter();
//            blueApi.CheckPrinterStatus();
            // //新开线程 打印
            new Thread() {
                public void run() {
                    try {
                        int rtn = 0;
                        switch (type) {
                            case 0: {
                                String code ="12345678";
                                rtn = blueApi.PrintOnedimCode(200,50,10,code,0);
                            };
                            break;
                            case 1: {
                                String code ="12345678";
                                rtn = blueApi.PrintOnedimCode(200,50,10,code,1);
                            }
                            break;
                            case 2: {
                                String code ="1234567";
                                rtn = blueApi.PrintOnedimCode(200,50,10,code,2);
                            }
                            break;
                            case 3: {
                                String code ="123456789012";
                                rtn = blueApi.PrintOnedimCode(200,50,10,code,3);
                            }
                            break;
                            case 4: {
                                rtn = blueApi.PrintTwoCode("http://www.baidu.com");
                            }
                            break;
                            case 5: {

//							AssetManager am = getResources().getAssets();
//							try {
//								InputStream is = am.open("1214.png");
//								final Bitmap bmplogo = BitmapFactory.decodeStream(is);
//
//								btapi.PrintImage(bmplogo);
//								is.close();
//								runOnUiThread(new Runnable() {
//									@Override
//									public void run() {
//										bImageView.setVisibility(View.VISIBLE);
//										bImageView.setImageBitmap(bmplogo);
//									}
//								});
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//                                Intent intent = new Intent(Intent.ACTION_PICK,
//                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                startActivityForResult(intent, 2);
                                Log.e("11111vv","11");
                                Bitmap mp;

//                                if (h == 0){
//                                    if (isImg){
//                                        mp = PrinterImageUtils.imageFloydSteinberg(PrinterImageUtils.convertToBlackWhite(PrinterImageUtils.resizeImage(bitmap,384,h),h));
//                                    }else{
//                                        mp = PrinterImageUtils.convertToBlackWhite(PrinterImageUtils.resizeImage(bitmap,384,h),h);
//                                    }
//                                }else{//标签
//                                    mp = PrinterImageUtils.resizeImage(bitmap,384,h);
//                                }

//                                mp = PrinterImageUtils.convertGreyImgByFloyd(bitmap);

//                                Bitmap mp = PrinterImageUtils.getSmallBitmap(path);
//                                Log.e("PrintImageBitmap:",mp.getWidth() + "," + mp.getHeight() + "," + mp.getByteCount()
//                                        + "," + mp.getRowBytes());
                                blueApi.PrintImage(bitmap,isBiaoqian);
//                                BitmapUtil.getInstance().savePicture(mp,System.currentTimeMillis() + "_logo.png");
                                Log.e("11111vv","22");
//                                if (null != view){
//                                    view.destroyDrawingCache(); // 保存过后释放资源
//                                }
//
//                                if (null != bitmap){
//                                    bitmap.recycle();
//                                }

                            }
                            break;
                            case 6: {
                                rtn = blueApi.PrintByte(new byte[] { 0x0A, 0x20, 0x21,
                                        0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28,
                                        0x29, 0x2A, 0x2B, 0x2C, 0x2D, 0x2E, 0x2F,
                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
                                        0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D,
                                        0x3E, 0x3F, 0x40, 0x41, 0x42, 0x43, 0x44,
                                        0x45, 0x46, 0x47, 0x48, 0x49, 0x4A, 0x4B,
                                        0x4C, 0x4D, 0x4E, 0x4F, 0x50, 0x51, 0x52,
                                        0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59,
                                        0x5A, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F, 0x60,
                                        0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67,
                                        0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E,
                                        0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75,
                                        0x76, 0x77, 0x78, 0x79, 0x7A, 0x7B, 0x7C,
                                        0x7D, 0x7E, 0x7F, 0x0A, 0x0A });
                            }
                            break;
                            case 7: {
//                                rtn = blueApi.PrintByte(toPrintText.getText().toString().getBytes("GBK"));

                            }
                            break;
                            default:
                                break;
                        }
//                        rtn = blueApi.PrintLn();
                        if(rtn == 0) {
                            // 提示 打印完成
//                            handler.sendEmptyMessage(1);
                        } else {
                            Message msg =Message.obtain();
                            msg.what = 0;
                            msg.getData().putString("error", "" + rtn);
//							handler.sendMessage(msg);
                        }

                    } catch (Exception e) {
                        // 提示 打印时 出错
                        Message msg =Message.obtain();
                        msg.what = 0;
                        msg.getData().putString("error", e.getMessage());
                        Log.e("printException",e.toString());
                    } finally {

                    }
                }
            }.start();
//        }
    }


    private int limitData = 512;
    private int writeCount = 0;
    private Vector<byte[]> vb = new Vector<>();
    private boolean isprint = false;
    public int PrintByte(byte[] data) {
        Log.e("printDataLenght",data.length + "");
        if (this.mBtAdapter != null && this.mBtAdapter.isEnabled()) {
            if (this.outStream == null) {
                return 2007;
            } else {
                    try {
                        this.outStream.write(data);
                        this.outStream.flush();
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                try {
//
//                    for (int i = 0;i < data.length;i += limitData){
//                        if (limitData  + i > data.length){
//                            this.outStream.write(data,i,data.length - i);
//                            this.outStream.flush();
//                            writeCount++;
//                        }else{
//                            this.outStream.write(data,i,limitData);
//                            this.outStream.flush();
//                            writeCount++;
//                        }
//
//                    }
//
//
//                } catch (UnsupportedEncodingException var3) {
//                    var3.printStackTrace();
//                } catch (Exception var4) {
//                    var4.printStackTrace();
//                }


//                    try {
//
//                        for (int i = 0;i < data.length;i += limitData){
//                            if (limitData  + i > data.length){
//                                byte[] bytes = new byte[limitData];
//                                System.arraycopy(data,i,bytes,0,data.length - i);
//                                vb.add(bytes);
//                            }else{
//                                byte[] bytes = new byte[limitData];
//                                System.arraycopy(data,i,bytes,0,limitData);
//                                vb.add(bytes);
//                            }
//                        }
//
//
//
//                        do {
//                            if (!isprint){
//                                isprint = true;
//                                this.outStream.write(vb.get(writeCount));
//                                this.outStream.flush();
//                            }
//
//                            byte[] read = new byte[20];
//                            this.inStream.read(read);
//                            System.out.println("" + read[0]);
//
//                            if (inStream.read() == 0x11 && isprint){
//                                writeCount ++;
//                                if (writeCount < vb.size()){
//                                    this.outStream.write(vb.get(writeCount));
//                                    this.outStream.flush();
//                                }else{
//                                    break;
//                                }
//
//                            }
//                        }while (true);
//
//
//
////                        for (int i = 0;i < data.length;i += limitData){
////                            if (limitData  + i > data.length){
////                                this.outStream.write(data,i,data.length - i);
////                                this.outStream.flush();
////                            }else{
////                                this.outStream.write(data,i,limitData);
////                                this.outStream.flush();
////                            }
////
////                            byte[] read = new byte[20];
////                            this.inStream.read(read);
////                            if (read[0] == 0x11){
////                                writeCount++;
////                            }else{
//////                                Thread.sleep(10);
////                                break;
////                            }
////                        }
//
//
//                    }catch (Exception var4) {
//                        var4.printStackTrace();
//                    }

                return 0;
            }
        } else {
            return 2007;
        }
    }

}
