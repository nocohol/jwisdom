package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * It demonstrates how to reject the task when the running threads count exceeds the capacity of thread pool
 * and blocking queue is full.
 * {@link java.util.concurrent.ThreadPoolExecutor.AbortPolicy} - It always throws {@code RejectedExecutionException}
 * {@link java.util.concurrent.ThreadPoolExecutor.DiscardPolicy - It discards the task by doing nothing}
 * {@link java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy}
 * Created by caronic on 2016/9/26.
 */
public class RejectExecutionPolicyDemo {

    public static void main(String[] args) {
        int coreSize = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println(coreSize);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreSize, coreSize, 2,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new ThreadPoolExecutor.AbortPolicy());

        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(coreSize, coreSize, 2,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i=0; i<coreSize+3; i++) {
                threadPoolExecutor.execute(()->{
                    try {
                        System.out.println(Thread.currentThread().getName() + " is running");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                threadPoolExecutor2.execute(() -> {
                    if (threadPoolExecutor2.getQueue().remainingCapacity() == 0)
                        System.out.println(threadPoolExecutor2.getQueue().peek().toString() + " will be discarded.....");
                    System.out.println(Thread.currentThread().getName() + " is running");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is completed");
                });
            }
            threadPoolExecutor.shutdown();
            threadPoolExecutor2.shutdown();
        }catch (RejectedExecutionException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
