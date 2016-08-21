package com.caronic.jwisdom.core.exercise.concurrent;

/**
 * It is to demonstrate how faster concurrent computing than serial computing
 * Created by caronic on 2016/6/26.
 */
public class ConcurrentTest {

    private static final long count = 100000000l;

    public static void main(String[] args) throws Exception{
        concurrent();
        serial();
    }

    public static void concurrent() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (int i=0; i<count; i++) {
                a += 5;
            }
        });
        thread.start();

        int b = 0;
        for (int j=0; j<count; j++) {
            b++;
        }

        thread.join();
        System.out.println("concurrent takes time " + (System.currentTimeMillis() - startTime) + "ms, b=" + b);
    }

    public static void serial() {
        long startTime = System.currentTimeMillis();
        int a = 0;
        for (int i=0; i<count; i++) {
            a += 5;
        }

        int b = 0;
        for (int j=0; j<count; j++) {
            b++;
        }

        System.out.println("serial takes time " + (System.currentTimeMillis() - startTime) + "ms, b=" + b);
    }

}
