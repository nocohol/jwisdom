package com.caronic.jwisdom.core.exercise.dp.construction.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by caronic on 2016/9/25.
 */
public class Test {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        JdkProxyHandler handler = new JdkProxyHandler(myClass);
        IMyClass proxy = (IMyClass)Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{IMyClass.class}, handler);
        proxy.doSomething();

        MyClass proxy1 = (MyClass)new CglibEnhancer().getProxy(MyClass.class);
        proxy1.doSomething();
    }

}
