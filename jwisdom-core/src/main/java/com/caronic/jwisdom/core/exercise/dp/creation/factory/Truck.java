package com.caronic.jwisdom.core.exercise.dp.creation.factory;

/**
 * Created by caronic on 2016/8/12.
 */
public class Truck implements ICar{
    @Override
    public void drive() {
        System.out.println("Truck drives");
    }

    @Override
    public void reverse() {
        System.out.println("Truck reverses");
    }

    @Override
    public void park() {
        System.out.println("Truck parks");
    }

    @Override
    public void neutral() {
        System.out.println("Truck neutrals");
    }
}
