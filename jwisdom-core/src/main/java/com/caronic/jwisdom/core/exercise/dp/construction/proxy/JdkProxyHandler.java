package com.caronic.jwisdom.core.exercise.dp.construction.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by caronic on 2016/9/25.
 */
public class JdkProxyHandler implements InvocationHandler {

    private Object source;
    public JdkProxyHandler(Object source) {
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Doing something before invoking");
        method.invoke(source);
        System.out.println("Doing something after invoking");
        return proxy;
    }
}
