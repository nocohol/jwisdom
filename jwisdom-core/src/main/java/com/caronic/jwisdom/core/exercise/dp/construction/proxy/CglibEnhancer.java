package com.caronic.jwisdom.core.exercise.dp.construction.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by caronic on 2016/9/25.
 */
public class CglibEnhancer implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Do something before invoking");
        Object result = methodProxy.invokeSuper(target, args);
        System.out.println("Do something after invoking");
        return result;
    }
}
