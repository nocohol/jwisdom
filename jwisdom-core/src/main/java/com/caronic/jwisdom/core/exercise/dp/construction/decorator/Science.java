package com.caronic.jwisdom.core.exercise.dp.construction.decorator;

/**
 * Created by caronic on 2016/9/19.
 */
public class Science extends GirlDecorator {

    private Girl girl;

    public Science(Girl girl) {
        this.girl = girl;
    }

    @Override
    public String getDescription() {
        return girl.getDescription() + " like science";
    }
}
