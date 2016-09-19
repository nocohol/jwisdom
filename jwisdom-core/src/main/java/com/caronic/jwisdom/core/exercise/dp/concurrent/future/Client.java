package com.caronic.jwisdom.core.exercise.dp.concurrent.future;

/**
 * Created by caronic on 2016/9/18.
 */
public class Client {

    public Data requestData(final String str) {
        FutureData future = new FutureData();
        new Thread(() -> {
            future.prepareData(str);
        }).start();
        return future;
    }

}
