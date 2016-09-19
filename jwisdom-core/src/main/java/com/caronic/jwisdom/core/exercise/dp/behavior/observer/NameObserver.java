package com.caronic.jwisdom.core.exercise.dp.behavior.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by caronic on 2016/9/3.
 */
public class NameObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            Production production = (Production) o;
            System.out.println("Production Name was changed to " + production.getName());
        }
    }
}
