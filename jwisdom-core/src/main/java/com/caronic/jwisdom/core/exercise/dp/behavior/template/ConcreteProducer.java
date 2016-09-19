package com.caronic.jwisdom.core.exercise.dp.behavior.template;

/**
 * Created by caronic on 2016/9/3.
 */
public class ConcreteProducer extends AbstractProducer {
    @Override
    public void doSpecificPart() {
        System.out.println("Do specific part.");
    }
}
