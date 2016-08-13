package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.*;

/**
 * Created by caronic on 2016/8/13.
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 10;
    private int start;
    private int end;
    private LinkedBlockingDeque<CountTask> folkedTasksDeque;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
        this.folkedTasksDeque = new LinkedBlockingDeque<>();
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start + 1 > THRESHOLD) {
            try {
                folkTasks();
                while (!folkedTasksDeque.isEmpty()) {
                    sum += folkedTasksDeque.take().join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }
        return sum;
    }

    private void folkTasks() throws InterruptedException {
        int size = end - start + 1;
//        if (size > THRESHOLD) {
            int segments = size / THRESHOLD;
            if (segments * THRESHOLD < size)
                segments += 1;
            int newStart, newEnd;
            for(int j = 0; j < segments; j++) {
                newStart = start + j * THRESHOLD;
                newEnd = (start + (j+1) * THRESHOLD) - 1 > end ? end : (start + (j+1) * THRESHOLD) - 1;
                CountTask subTask = new CountTask(newStart, newEnd);
                subTask.fork();
                this.folkedTasksDeque.put(subTask);
            }
//        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1, 100);
        Future<Integer> result = pool.submit(task);
        try {
            System.out.println("Result: " + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
