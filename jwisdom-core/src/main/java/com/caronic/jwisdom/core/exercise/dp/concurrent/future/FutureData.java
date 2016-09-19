package com.caronic.jwisdom.core.exercise.dp.concurrent.future;

/**
 * Created by caronic on 2016/9/18.
 */
public class FutureData implements Data {

    private RealData realData;
    private volatile boolean dataReady = false;

    @Override
    public void prepareData(String str) {
        if (!dataReady) {
            synchronized (this) {
                if (realData == null)
                    realData = new RealData();
                realData.prepareData(str);
                dataReady = true;
                notifyAll();
            }
        }
    }

    public String getResult() {
        synchronized (this) {
            while (!dataReady) {
                try {
                    System.out.println("Waiting for data ready.");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Data is got.");
            return realData.getResult();
        }
    }

    public RealData getRealData() {
        return realData;
    }

    public void setRealData(RealData realData) {
        this.realData = realData;
    }
}
