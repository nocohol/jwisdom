package com.caronic.jwisdom.core.exercise.dp.behavior.observer;

/**
 * Created by caronic on 2016/9/3.
 */
public class Test {
    public static void main(String[] args) {
        Production production = new Production();
        production.addObserver(new NameObserver());
        production.addObserver(new PriceObserver());
        production.setName("test");
        production.setPrice(100.0f);
    }
}
