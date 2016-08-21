package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caronic on 2016/8/21.
 */
public class ExecutorServiceDemo {

    private static final int CORE_POOL_SIZE = 10;

    public static void main(String[] args) {
        // Create a fixed size thread pool
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 11; i++) {
            fixedThreadPool.submit(() -> method());
        }

        // will create 3 threads in the pool and terminate very soon
        for(int i=0; i < 3; i++) {
            cachedThreadPool.submit(() -> shortTimeTask());
        }

        // Reuse the existing 3 threads in the pool and need to create another 2 new threads.
        for (int i=0; i < 5; i++) {
            cachedThreadPool.submit(() -> longTimeTask());
        }

        // No available threads in the pool, then need to create new threads.
        for(int i=0; i < 2; i++) {
            cachedThreadPool.submit(() -> shortTimeTask());
        }

        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
    }

    static void method() {
        System.out.println(Thread.currentThread().getName() + " is running.");
        try {
            Thread.sleep(2000);
        }catch (Exception ex) {
            //
        }
    }

    static void longTimeTask() {
        System.out.println(Thread.currentThread().getName() + " is running.");
        try {
            Thread.sleep(2000);
        }catch (Exception ex) {
            //
        }
    }

    static void shortTimeTask() {
        System.out.println(Thread.currentThread().getName() + " is running.");
    }

}
