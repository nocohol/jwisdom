package com.caronic.jwisdom.core.exercise.dp.concurrent.producerconsumer;

/**
 * Created by caronic on 2016/9/24.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        NumberExecutor executor = new NumberExecutor();

        Thread produceThread = new Thread(()->{
            try{
                executor.produceNumbers();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }, "Producer Thread");

        Thread consumeThread = new Thread(()->{
            try {
                executor.consumeNumber();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consume Thread");

        produceThread.start();
        consumeThread.start();

    }
}
