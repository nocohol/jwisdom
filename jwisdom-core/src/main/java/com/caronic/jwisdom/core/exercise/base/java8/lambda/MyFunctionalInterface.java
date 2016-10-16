package com.caronic.jwisdom.core.exercise.base.java8.lambda;

/**
 * Created by caronic on 2016/10/15.
 */
@FunctionalInterface()
public interface MyFunctionalInterface {

    default void defaultMethod(String string) {
        System.out.println(string);
    }

    void method(String string);

}
