package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caronic on 2016/9/17.
 */
public class Counter {

    private AtomicInteger atomicI = new AtomicInteger(0);
    private AtomicInteger atomicI2 = new AtomicInteger(0);
//    private static final sun.misc.Unsafe UNSAFE;
    private int i = 0;
//    private int j = 0;

//    static {
//        UNSAFE = Unsafe.getUnsafe();
//    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        List<Thread> tl = new ArrayList<>();
        for (int i=0; i<100; i++) {
            Thread thread = new Thread(() -> {
                for (int j=0; j<100000; j++) {
                    counter.safeCount();
                    counter.safeCount2();
//                    counter.safeCount3();
                    counter.count();
                }
            });
            tl.add(thread);
        }

        tl.forEach((t) -> {
            t.start();
        });

        for (Thread t : tl) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicI.get());
        System.out.println(counter.atomicI2.get());
    }

    private void safeCount() {
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc)
                break;
        }
    }

    private void safeCount2() {
        atomicI2.getAndIncrement();
    }

    private void count() {
        i++;
    }

//    private void safeCount3() {
//        UNSAFE.compareAndSwapInt(Integer.class, 0, j, ++j);
//    }

}
