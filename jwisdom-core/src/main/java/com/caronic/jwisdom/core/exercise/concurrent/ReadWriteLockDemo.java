package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by caronic on 2016/9/16.
 */
public class ReadWriteLockDemo {

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    private static final Lock readLock = readWriteLock.readLock();
    private static  final Lock writeLock = readWriteLock.writeLock();
    private static Map<String, String> map = new HashMap<>();

    public static void setValue(String key, String value) {
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " is going to add key-value");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread was interrupted by other thread", e);
        }
        map.put(key, value);
        writeLock.unlock();
    }

    public static String getValue(String key) {
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + " is going to get value");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(Thread.currentThread().getName() + " was interrupted by other thread", e);
        }
        String value = map.get(key);
        readLock.unlock();
        return value;
    }

    public static void main(String[] args) throws Exception{
        Thread writeThread = new Thread(() -> {
            ReadWriteLockDemo.setValue("1", "adc");
            System.out.println(Thread.currentThread().getName() + " added key-value");
        }, "writeThread");

        Thread readThread = new Thread(()->{
            System.out.println(ReadWriteLockDemo.getValue("1"));
        }, "readThread");

        Thread readThread1 = new Thread(()->{
            System.out.println(ReadWriteLockDemo.getValue("1"));
        }, "readThread1");

        writeThread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        // simultaneously acquire lock for read
        readThread.start();
        readThread1.start();
    }

}
