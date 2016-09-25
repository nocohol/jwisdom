package com.caronic.jwisdom.core.exercise.dp.concurrent.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/9/24.
 */
public class NumberExecutor {

    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private LinkedBlockingQueue<Long> numberQueue = new LinkedBlockingQueue<>();

    public void produceNumbers() throws InterruptedException {
        Producer producer = new Producer();
        for (int i=0; i<10; i++) {
            System.out.println("Producer is producing number");
            TimeUnit.SECONDS.sleep(2);
            numberQueue.put(producer.produceNumber());
        }
    }

    public void consumeNumber() throws InterruptedException {
        while (true) {
            final Long number = numberQueue.poll(3, TimeUnit.SECONDS);
            if (number == null)
                break;

            executorService.submit(() -> {
                Consumer consumer = new Consumer();
                System.out.println("Consumer is consuming number");
                consumer.consumeNumber(number);
            });
        }
        executorService.shutdown();
    }

}
