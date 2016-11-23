package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/11/12.
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            try{
                // this will not be printed in console
                System.out.println("Daemon thread is started.");
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("the finally block in daemon thread.");
            }
        });
        daemonThread.setDaemon(false);
        daemonThread.start();
        // when main thread is end, jvm will shutdown no matter there is any daemon thread is running.
    }
}
