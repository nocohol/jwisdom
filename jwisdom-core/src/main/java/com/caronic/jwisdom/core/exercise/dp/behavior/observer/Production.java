package com.caronic.jwisdom.core.exercise.dp.behavior.observer;

import java.util.Observable;

/**
 * Observer pattern can make your program have the capacity to observe state changes against to
 * some object
 * Created by caronic on 2016/9/3.
 */
public class Production extends Observable {

    private String name;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers(this.name);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        setChanged();
        notifyObservers(this.price);
    }
}
