package com.jiem.net.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 * <p/>
 * Created by jiem on 2018/5/8 21:00.
 */
public class Server {

    //线程池
    private ExecutorService executorService;

    //线程组
    private AsynchronousChannelGroup threadGroup;

    //服务器通道
    public AsynchronousServerSocketChannel socket;

    public Server(int port) {

        try {
            executorService = Executors.newCachedThreadPool();
            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            socket = AsynchronousServerSocketChannel.open(threadGroup);

            //进行绑定
            socket.bind(new InetSocketAddress(port));

            System.out.println("server start , port : " + port);

            //进行阻塞
            socket.accept(this, new ServerCompletionHandler());
            //一直阻塞 不让服务器停止
            Thread.sleep(Integer.MAX_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(8765);
    }
}
