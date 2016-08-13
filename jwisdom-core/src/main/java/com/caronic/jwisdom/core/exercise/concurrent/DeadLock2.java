package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caronic on 2016/4/25.
 */
public class DeadLock2 {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock(false);
        ReentrantLock lock2 = new ReentrantLock(false);
        final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock2.newCondition();

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(() -> {
            lock.lock();
            System.out.println("Thread " + Thread.currentThread().getId() + " obtains the lock");
            try {
                TimeUnit.SECONDS.sleep(1);
                while(!lock2.tryLock())
                    condition1.await();
                System.out.println("Thread" + Thread.currentThread().getId() + " obtains the signal");
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
                lock.unlock();
            } finally {
                lock.unlock();
                System.out.println("Thread" + Thread.currentThread().getId() + " release the lock");
            }
        });

        es.submit(() -> {
            lock2.lock();
            System.out.println("Thread " + Thread.currentThread().getId() + " obtains the lock");
            try {
                TimeUnit.SECONDS.sleep(1);
                while (!lock.tryLock())
                    condition2.await();
                System.out.println("Thread " + Thread.currentThread().getId() + " send the release signal");
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
                lock2.unlock();
            } finally {
                lock2.unlock();
            }
        });

        es.shutdown();

    }

}
