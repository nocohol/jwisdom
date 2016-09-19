package com.caronic.jwisdom.core.exercise.dp.concurrent.future;

import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/9/18.
 */
public class RealData implements Data {

    private String result;

    @Override
    public void prepareData(String str) {
        System.out.println("Preparing real data");
        try {
            TimeUnit.SECONDS.sleep(2);
            result = str;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Real data is done.");
    }

    public String getResult() {
        return result;
    }
}
