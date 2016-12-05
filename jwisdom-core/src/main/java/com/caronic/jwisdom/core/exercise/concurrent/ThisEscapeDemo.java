package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/12/5.
 */
public class ThisEscapeDemo {

    public String name;

    public ThisEscapeDemo() {
        new Thread(() -> {
            System.out.println(this.name); // this escape before constructor completely finish
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1); // mock a long run for instance constructs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = "test";
    }

    public static void main(String[] args) {
        ThisEscapeDemo demo = new ThisEscapeDemo();
        System.out.println(demo.name);
    }

}
