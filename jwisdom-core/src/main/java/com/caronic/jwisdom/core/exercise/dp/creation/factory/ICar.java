package com.caronic.jwisdom.core.exercise.dp.creation.factory;

/**
 * Created by caronic on 2016/8/12.
 */
public interface ICar {
    enum CAR_TYPE {CAR, TRUCK}
    void drive();
    void reverse();
    void park();
    void neutral();
}
