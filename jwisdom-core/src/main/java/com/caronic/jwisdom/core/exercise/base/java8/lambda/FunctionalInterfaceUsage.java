package com.caronic.jwisdom.core.exercise.base.java8.lambda;

/**
 * Created by caronic on 2016/10/15.
 */
public class FunctionalInterfaceUsage {
    private MyFunctionalInterface myFunctionalInterface;

    public FunctionalInterfaceUsage(MyFunctionalInterface myFunctionalInterface) {
        this.myFunctionalInterface = myFunctionalInterface;
    }

    public void method(String str) {
        myFunctionalInterface.method(str);
    }
}
