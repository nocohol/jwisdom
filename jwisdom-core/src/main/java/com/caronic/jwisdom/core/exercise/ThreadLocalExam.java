package com.caronic.jwisdom.core.exercise;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caronic on 2016/6/12.
 */
public class ThreadLocalExam {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static Integer sharedId = new Integer(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static Integer get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            sharedId = 1;
            System.out.println("Shared Id: " + ThreadLocalExam.sharedId);
            System.out.println("Thread Id: " + ThreadLocalExam.get());
        });

        Thread thread2 = new Thread(() -> {
            sharedId = 2;
            System.out.println("Shared Id: " + ThreadLocalExam.sharedId);
            System.out.println("Thread Id: " + ThreadLocalExam.get());
        });

        thread1.start();
        thread2.start();
    }

}
