package com.jiem.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * nio 客户端
 * Created by jiem on 2018/5/9 21:20.
 */
public class Client {

    public static void main(String[] args) {

        //声明连接通道
        SocketChannel sc = null ;

        //建立缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1",1231));

            while (true){
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                buffer.put(bytes);
                buffer.flip();
                sc.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sc != null){
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
