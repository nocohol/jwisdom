package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * To demonstrate how to use condition of lock to implement wait/nofity communication.
 * Created by caronic on 2016/8/6.
 */
public class BoundQueue {

    private Object[] queue;
    private int count, removeIndex, addIndex;

    private ReentrantLock lock = new ReentrantLock();
    // the condition for adding item to the queue
    private Condition hasRoom = lock.newCondition();
    // the condition for removing item to the queue
    private Condition hasItem = lock.newCondition();

    public BoundQueue(int size) {
        queue = new Object[size];
    }

    public void addItem(Object item) throws InterruptedException{
        lock.lock();
        try {
            while (count ==  queue.length)
                hasRoom.await();
            queue[addIndex] = item;
            if (++addIndex == queue.length)
                addIndex = 0;
            ++count;
            hasItem.signal();
        } finally {
            lock.unlock();
        }
    }

    public void removeItem() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                hasItem.await();
            queue[removeIndex] = null;
            if (++removeIndex == queue.length)
                removeIndex = 0;
            --count;
            hasRoom.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService addService = Executors.newFixedThreadPool(10);
        ExecutorService removeService = Executors.newFixedThreadPool(10);

        BoundQueue queue = new BoundQueue(10);

        for (int i=0; i<10; i++) {
            addService.submit(() -> {
                System.out.println("Thread " + Thread.currentThread().getId() + " is going to add item");
                try {
                    queue.addItem("a");
                    System.out.println("Thread " + Thread.currentThread().getId() + " already added item");
                } catch (InterruptedException e) {
                }
            });
        }

        for (int i=0; i<10; i++) {
            removeService.submit(() -> {
                System.out.println("Thread " + Thread.currentThread().getId() + " is going to remove item");
                try {
                    queue.removeItem();
                    System.out.println("Thread " + Thread.currentThread().getId() + " already removed item");
                } catch (InterruptedException e) {
                }
            });
        }
    }

}
