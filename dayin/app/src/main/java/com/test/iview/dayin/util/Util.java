package com.test.iview.dayin.util;

import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018\5\21 0021.
 */

public class Util {
    public static void download(String _url, String path) throws Exception {
                try {
// 构造URL
                    URL url = new URL(_url);
                    // 打开连接
                    URLConnection con = url.openConnection();
                    //设置请求超时为5s
                    con.setConnectTimeout(5 * 1000);
                    // 输入流
                    InputStream is = con.getInputStream();

                    // 1K的数据缓冲
                    byte[] bs = new byte[1024 * 1024];
                    // 读取到的数据长度
                    int len;
                    // 输出的文件流
                    File sf = new File(path,"aa.png");

                    OutputStream os = new FileOutputStream(sf);
                    // 开始读取
                    while ((len = is.read(bs)) != -1) {
                        os.write(bs, 0, len);
                    }
                    Log.e("哈哈","成0功");
                    // 完毕，关闭所有链接
                    os.close();
                    is.close();
                } catch (IOException e) {
                    Log.e("哈哈","失败");
                }
    }
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
