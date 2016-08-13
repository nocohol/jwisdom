package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/7/31.
 */
public class Synchronized {
    public static void main(String[] args) {
        Synchronized o = new Synchronized();
        Thread threadA = new Thread(() -> {
            synchronized (o) {
                o.methodA();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                }
            }
        });

        Thread threadB = new Thread(() -> {
            o.methodB();
        });

        threadA.start();
        threadB.start();
    }

    public synchronized void methodA() {
        System.out.println("Enter into method A");
    }

    public synchronized void methodB() {
        System.out.println("Enter into method B");
    }
}
