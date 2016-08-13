package com.caronic.jwisdom.core.exercise.dp.creation.factory;

/**
 * Created by caronic on 2016/8/12.
 */
public class TruckFactory implements ICarFactory {
    private ICar buildATruck() {
        return new Truck();
    }

    @Override
    public ICar buildACar() {
        return buildATruck();
    }
}
