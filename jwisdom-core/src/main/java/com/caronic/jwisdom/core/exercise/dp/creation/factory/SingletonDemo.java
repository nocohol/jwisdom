package com.caronic.jwisdom.core.exercise.dp.creation.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caronic on 2016/9/3.
 */
public class SingletonDemo {

    private static SingletonDemo instance;
    private String name;
    private int age;

    private SingletonDemo() {

    }

    public static SingletonDemo getInstance() {
        SingletonDemo inst = instance;
        if (inst == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
            return instance;
        }
        return inst;
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(20);
        for (int i=0; i<20; i++) {
            es.submit(()->{
                SingletonDemo instance = SingletonDemo.getInstance();
                System.out.println(instance.hashCode());
            });
        }
        es.shutdown();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
