package com.caronic.jwisdom.core.exercise;

import java.util.concurrent.CountDownLatch;

/**
 * Threads themselves will work in disorder. But when to start and
 * Created by caronic on 2016/6/18.
 */
public class CountDownLatchExam {
    private static final CountDownLatch startSignal = new CountDownLatch(1);
    private static final CountDownLatch doneSignal = new CountDownLatch(10);

    public static void main(String[] args) throws Exception{
        // Start 10 threads to do something
        for (int i=0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                // wait for start signal
                try {
                    System.out.println("Worker " + Thread.currentThread().getId() + " is waiting for start signal.");
                    startSignal.await();
                    System.out.println("Worker" + Thread.currentThread().getId() + " starts to work.");
                            doneSignal.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start(); // thread starts, but need to wait for start signal
        }

        // count down start signal
        System.out.println("Count Down signal.");
        startSignal.countDown();
        doneSignal.await(); // wait till all works's work finished.
        System.out.println("All workers have finished work.");
    }
}
