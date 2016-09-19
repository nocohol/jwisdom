package com.caronic.jwisdom.core.exercise.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
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

        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        ReentrantLock lock = new ReentrantLock(false);
        ReentrantLock lock2 = new ReentrantLock(false);
        final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock2.newCondition();

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(() -> {
//            lock.lock();
//            lock.lockInterruptibly();
            System.out.println("Thread " + Thread.currentThread().getId() + " obtains the lock");
            try {
                lock.lockInterruptibly();
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
//            lock2.lock();
            System.out.println("Thread " + Thread.currentThread().getId() + " obtains the lock");
            try {
                lock2.lockInterruptibly();
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
        DeadlockChecker.check();

    }

    static class DeadlockChecker {
        private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        final static Runnable deadlockChecker = ()-> {
            while (true) {
                long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                if (deadlockedThreadIds != null) {
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                    for (Thread t: Thread.getAllStackTraces().keySet()) {
                        for (int i=0; i<threadInfos.length; i++) {
                            if (t.getId() == threadInfos[i].getThreadId()) {
                                t.interrupt();
                            }
                        }
                    }
                }
            }
        };

        public static void check() {
            Thread t = new Thread(deadlockChecker);
            t.setDaemon(true);
            t.start();
        }

    }

}
