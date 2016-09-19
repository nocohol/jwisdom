package com.caronic.jwisdom.core.exercise.dp.construction.decorator;

/**
 * Created by caronic on 2016/9/19.
 */
public class Test {
    public static void main(String[] args) {
        Girl girl =  new Science(new Art(new AmericanGirl()));
        System.out.println(girl.getDescription());
    }
}
