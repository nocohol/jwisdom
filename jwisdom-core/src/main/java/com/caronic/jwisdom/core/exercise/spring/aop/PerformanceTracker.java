package com.caronic.jwisdom.core.exercise.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by caronic on 2016/9/25.
 */
@Component
@Aspect
public class PerformanceTracker {

    private final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap();

    // all of the joint point that has annotated by PerformanceAware
    @Pointcut("@annotation(com.caronic.jwisdom.core.exercise.spring.aop.PerformanceAware)")
    private void anyPerformanceAwareMethod() {
    }

    @Before("anyPerformanceAwareMethod()")
    public void logStartTime(JoinPoint joinpoint){
        long start = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(start);
        String methodName = joinpoint.getSignature().getName();
        System.out.println(methodName + " was executed at " + cal.getTime());
        map.put(methodName, start);
    }

    @AfterReturning("anyPerformanceAwareMethod()")
    public void logEndTime(JoinPoint joinpoint) {
        long end = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(end);
        String methodName = joinpoint.getSignature().getName();
        System.out.println(methodName + " was normally completed at " + cal.getTime());
        System.out.println("Total execution time: " + (end - map.get(methodName)) / 1000);
    }

}
