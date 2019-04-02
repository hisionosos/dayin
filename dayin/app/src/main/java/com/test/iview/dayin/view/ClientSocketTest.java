package com.test.iview.dayin.view;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class ClientSocketTest {
    public static void main(String[] args) throws IOException {
        // 与服务器建立连接
        Socket socket = new Socket("127.0.0.1", 10000);
        // 发送数据
        OutputStream outputStream = socket.getOutputStream();
        byte[] b = new byte[2];
        outputStream.write(b);
    }
}
