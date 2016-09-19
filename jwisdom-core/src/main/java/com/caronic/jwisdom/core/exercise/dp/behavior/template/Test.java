package com.caronic.jwisdom.core.exercise.dp.behavior.template;

/**
 * Created by caronic on 2016/9/3.
 */
public class Test {

    public static void main(String[] args) {
        AbstractProducer producer = new ConcreteProducer();
        producer.createProduction();
    }

}
