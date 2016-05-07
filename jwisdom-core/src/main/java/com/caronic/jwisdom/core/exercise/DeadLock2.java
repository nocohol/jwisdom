package com.caronic.jwisdom.core.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caronic on 2016/4/25.
 */
public class DeadLock2 {

    public static void main(String[] args) {
        String a = "a";
        String b = "b";

//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantLock lock = new ReentrantLock(false);
        final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock.newCondition();

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(() -> {
            lock.lock();
            System.out.println(a);
            try {
//                Thread.sleep(1000);
                condition2.await();
//                lock.lock();
                System.out.println(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
                lock.unlock();
            } finally {
                lock.unlock();
            }
        });

        es.submit(() -> {
            lock.lock();
            System.out.println(b);
            try {
                Thread.sleep(1000);
//                condition1.await();
//                lock.lock();
                System.out.println(a);
                condition1.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
                lock.unlock();
            } finally {
                lock.unlock();
            }
        });

    }

}
