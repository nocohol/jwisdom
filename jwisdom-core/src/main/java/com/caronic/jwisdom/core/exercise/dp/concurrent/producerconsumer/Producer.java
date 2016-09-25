package com.caronic.jwisdom.core.exercise.dp.concurrent.producerconsumer;

import java.util.UUID;

/**
 * Created by caronic on 2016/9/24.
 */
public class Producer {

    public long produceNumber() {
        return UUID.randomUUID().getMostSignificantBits();
    }

}
