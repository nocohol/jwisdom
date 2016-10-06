package com.caronic.jwisdom.core.exercise.spring.aop;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/9/25.
 */
@Component
public class TargetObject {

    @PerformanceAware
    public void doSomething() {
        System.out.println("Doing something");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PerformanceAware
    public void doSomethingWrong() {
        throw new RuntimeException("Do something wrong");
    }

}
