package com.caronic.jwisdom.core.exercise.dp.creation.factory;

/**
 * Created by caronic on 2016/8/12.
 */
public class CarFactory {

    private ICar buildACar(ICar.CAR_TYPE carType) {
        switch (carType) {
            case CAR:
                return new Car();
            case TRUCK:
                return new Truck();
            default:
                return new Car();
        }
    }

}
