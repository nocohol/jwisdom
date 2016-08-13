package com.caronic.jwisdom.core.exercise.dp.creation.factory;

/**
 * Created by caronic on 2016/8/12.
 */
public class Car implements ICar{
    @Override
    public void drive() {
        System.out.println("car drives");
    }

    @Override
    public void reverse() {
        System.out.println("car reverses");
    }

    @Override
    public void park() {
        System.out.println("car parks");
    }

    @Override
    public void neutral() {
        System.out.println("car neutrals");
    }
}
