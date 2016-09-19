package com.caronic.jwisdom.core.exercise.dp.concurrent.future;

import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/9/18.
 */
public class Test {
    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.requestData("test data");
        doSomethingElse();
        String result = data.getResult();
        System.out.println("Result is: " + result);
    }

    private static void doSomethingElse() {
        System.out.println("Doing something else before data is return");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
