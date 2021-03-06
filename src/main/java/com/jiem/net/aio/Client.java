package com.jiem.net.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * 客户端
 * <p/>
 * Created by jiem on 2018/5/8 21:49.
 */
public class Client implements Runnable{

    private AsynchronousSocketChannel socketChannel ;

    public Client() throws Exception {
        socketChannel = AsynchronousSocketChannel.open();
    }

    public void connect(){
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8765));
    }


    public void write(String request){
        try {
            socketChannel.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        try {

            socketChannel.read(buf).get();
            buf.flip();
            byte[] respByte = new byte[buf.remaining()];
            buf.get(respByte);
            System.out.println(new String(respByte,"utf-8").trim());

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        Client c1 = new Client();
        c1.connect();

        Client c2 = new Client();
        c2.connect();

        Client c3 = new Client();
        c3.connect();

        new Thread(c1, "c1").start();
        new Thread(c2, "c2").start();
        new Thread(c3, "c3").start();

        Thread.sleep(1000);

        c1.write("c1 aaa");
        c2.write("c2 bbbb");
        c3.write("c3 ccccc");

    }
}
