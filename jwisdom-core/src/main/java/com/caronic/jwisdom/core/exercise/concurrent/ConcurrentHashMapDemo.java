package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by caronic on 2016/8/31.
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        // To simulate multiple threads doing retrieval and update operations
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(20);
        for (int i = 0; i<100; i++) {
            final int j = i;
            executorService.execute(() -> {
                try {
                    startSignal.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Put " + j);
                map.put("a", j);
                startSignal.countDown();
                doneSignal.countDown();
            });
        }

        for (int j = 0; j<10; j++) {
            executorService.execute(() -> {
                try {
                    startSignal.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Get " + map.get("a"));
                doneSignal.countDown();
            });
        }

        startSignal.countDown();
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
