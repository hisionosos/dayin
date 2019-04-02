package com.test.iview.dayin.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.util.Log;

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

        public BlueSAPI() {
        }

        public int openPrinter(String Deviveid, String Pwd) {
            int res = 0;
            this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
            this.device = this.mBtAdapter.getRemoteDevice(Deviveid);
            if (this.device.getBondState() != 12) {
                try {
//                    this.autoBond(this.device.getClass(), this.device, Pwd);
//                    this.createBond(this.device.getClass(), this.device);
                    ClsUtils.createBond(device.getClass(), device);

                } catch (Exception var6) {
                    System.out.println("配对不成功");
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
                                connectDevice();

                            }


                        }
                    }
                }).start();
            }else{
                connectDevice();
            }



            return res;
        }

        private int connectDevice(){
            int res = 0;
            try {
                this.btSocket = this.device.createRfcommSocketToServiceRecord(MY_UUID);
                this.btSocket.connect();
                this.outStream = this.btSocket.getOutputStream();
                this.inStream = this.btSocket.getInputStream();
            } catch (IOException var5) {
                var5.printStackTrace();
                res = 2002;
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
                        System.out.println("" + read[0]);
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
        public int PrintImage(Bitmap bmp) {
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
                                r = 255 & color;
                                g = ('\uff00' & color) >> 8;
                                b = (16711680 & color) >> 16;
                            } else {
                                r = 111;
                                g = 111;
                                b = 111;
                            }

                            if (k == 8 || k == 16) {
                                ++j;
                            }

                            if (r < 100 && g < 100 && b < 100) {
                                body[j] = (byte)(body[j] * 2 + 1);
                            } else {
                                body[j] = (byte)(body[j] * 2);
                            }
                        }
                    }

                    this.PrintByte(body);
                }
                Log.e("printwriteCount",writeCount + "");
                PrintLn();
                PrintLn();
                PrintLn();
                PrintLn();

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

        private int limitData = 146;
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

                        for (int i = 0;i < data.length;i += limitData){
                            if (limitData  + i > data.length){
                                byte[] bytes = new byte[limitData];
                                System.arraycopy(data,i,bytes,0,data.length - i);
                                vb.add(bytes);
                            }else{
                                byte[] bytes = new byte[limitData];
                                System.arraycopy(data,i,bytes,0,limitData);
                                vb.add(bytes);
                            }
                        }



                        do {
                            if (!isprint){
                                isprint = true;
                                this.outStream.write(vb.get(writeCount));
                                this.outStream.flush();
                            }

                            byte[] read = new byte[20];
                            this.inStream.read(read);
                            System.out.println("" + read[0]);

                            if (inStream.read() == 0x11 && isprint){
                                writeCount ++;
                                if (writeCount < vb.size()){
                                    this.outStream.write(vb.get(writeCount));
                                    this.outStream.flush();
                                }else{
                                    break;
                                }

                            }
                        }while (true);



//                        for (int i = 0;i < data.length;i += limitData){
//                            if (limitData  + i > data.length){
//                                this.outStream.write(data,i,data.length - i);
//                                this.outStream.flush();
//                            }else{
//                                this.outStream.write(data,i,limitData);
//                                this.outStream.flush();
//                            }
//
//                            byte[] read = new byte[20];
//                            this.inStream.read(read);
//                            if (read[0] == 0x11){
//                                writeCount++;
//                            }else{
////                                Thread.sleep(10);
//                                break;
//                            }
//                        }


                    } catch (UnsupportedEncodingException var3) {
                        var3.printStackTrace();
                    } catch (IOException var4) {
                        var4.printStackTrace();
                    }
//                    catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    return 0;
                }
            } else {
                return 2007;
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



}
