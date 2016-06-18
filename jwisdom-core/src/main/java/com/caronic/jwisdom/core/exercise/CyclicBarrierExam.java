package com.caronic.jwisdom.core.exercise;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by caronic on 2016/6/18.
 */
public class CyclicBarrierExam {

    private final String[] resources;
    private final String[] target;
    private final CyclicBarrier barrier;

    // need some workers to change the resources to uppercase
    public static void main(String[] args) {
        CyclicBarrierExam resolver = new CyclicBarrierExam(new String[] {"abc", "defg", "hig", "klm", "nop", "qrs"});
        for (int i=0; i<resolver.resources.length; i++){
            final int index = i;
            Thread thread = new Thread(() -> {
                String resource = resolver.resources[index];
                resolver.target[index] = resource.toUpperCase();
                System.out.println("worker " + Thread.currentThread().getId() + " has handled resource.");
                try {
                    resolver.barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    public CyclicBarrierExam(String[] resources) {
        this.resources = resources;
        this.target = new String[resources.length];
        barrier = new CyclicBarrier(resources.length, () -> {
            printInoRder();
        });
    }

    void printInoRder() {
        for (int i=0; i< target.length; i++) {
                    System.out.println(target[i]);
        }
    }

}
