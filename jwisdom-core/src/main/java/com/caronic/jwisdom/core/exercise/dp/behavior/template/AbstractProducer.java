package com.caronic.jwisdom.core.exercise.dp.behavior.template;

/**
 * Created by caronic on 2016/9/3.
 */
public abstract class AbstractProducer {

    private void doCommonPart() {
        System.out.println("Do common part");
    }

    protected abstract void doSpecificPart();

    public void createProduction() {
        doCommonPart();
        doSpecificPart();
    }

}
