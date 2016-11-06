package com.caronic.jwisdom.core.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * This utilization tool is used to calculate the execution time for targeting method.
 * The method needs to be tracked, should be annotated by {@link com.caronic.jwisdom.core.annotation.PerformanceAware}
 * Created by caronic on 2016/11/6.
 */
@Aspect
@Component
public class PerformanceTracker {

//    private final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap();
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private Logger LOGGER = LoggerFactory.getLogger(PerformanceTracker.class);

    @Pointcut("@annotation(com.caronic.jwisdom.core.annotation.PerformanceAware)")
    private void anyPerformanceAwareMethod() {
    }

    @Before("anyPerformanceAwareMethod()")
    public void logStartTime(JoinPoint joinpoint){
        long start = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(start);
        String methodName = joinpoint.getSignature().getName();
        System.out.println(methodName + " was executed at " + cal.getTime());
//        map.put(methodName, start);
        startTime.set(start);
    }

    @AfterReturning("anyPerformanceAwareMethod()")
    public void logEndTime(JoinPoint joinpoint) {
        long end = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(end);
        String methodName = joinpoint.getSignature().getName();
        System.out.println(methodName + " was normally completed at " + cal.getTime());
        System.out.println("Total execution time: " + (end - startTime.get()) / 1000);
        startTime.remove();
    }

    @AfterThrowing(value="anyPerformanceAwareMethod()", throwing = "e")
    public void logException(JoinPoint joinpoint, Throwable e) {
        LOGGER.error("Exception happened.");
        String methodName = joinpoint.getSignature().getName();
        System.out.println(methodName);

        if (e instanceof RuntimeException) {
            System.out.println("RuntimeException happened");
        }
    }

}
