package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * It illustrates how threads communicate each other with wait/notify mechanism
 * Created by caronic on 2016/7/31.
 */
public class WaitNotify {

    private volatile static boolean flag = true;

    private final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();

        TimeUnit.SECONDS.sleep(5);

        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();

    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " is going to wait");
            // Add lock, obtain the monitor
            synchronized (lock) {
                System.out.println("Thread " + Thread.currentThread().getName() + " obtains lock monitor");
                while (flag) {
                    try {
                        // current thread releases object lock and enters into WAITING status
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Thread" + Thread.currentThread().getName() + " is notified");
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) { // obtain object monitor, allow to entry into synchronized block
                System.out.println("Thread " + Thread.currentThread().getName() + " is going to notify");
                flag = false;
                System.out.println("Flag is set the false");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notifyAll();
            }
        }
    }

}
