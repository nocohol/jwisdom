package com.caronic.jwisdom.core.exercise.concurrent;

/**
 * Created by caronic on 2016/4/23.
 */
public class DeadLock {

    public static void main(String[] args) {

        String a = "a";
        String b = "b";

        Thread threadA = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(b);
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(a);
                }
            }
        });

        threadA.start();
        threadB.start();

    }
}
