package com.jiem.net.bio2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by jiem on 2018/5/10 22:10.
 */
public class HandlerExecutorPool {

    private ExecutorService executorService ;

    public HandlerExecutorPool(int maxPoolSize, int queueSize){

        this.executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize)
            );
    }

    public void execute (Runnable task){
        this.executorService.execute(task);
    }
}
