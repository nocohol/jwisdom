package com.caronic.jwisdom.core.exercise.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/7/31.
 */
public class Synchronized {
    public static void main(String[] args) {
        Synchronized o = new Synchronized();
        Synchronized s = new Synchronized();
        Thread threadA = new Thread(() -> {
            // obtain object lock via synchronized method
            o.methodA();
        });

        Thread threadB = new Thread(() -> {
            // obtain object lock via synchronized method
            o.methodB();
        });

        Thread threadC = new Thread(() -> {
            // obtain the object lock via synchronized block
            synchronized (s) {
                s.methodC();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadD = new Thread(() -> {
            // obtain the object lock via synchronized block
            synchronized (s) {
                s.methodD();
            }
        });

        Thread threadE = new Thread(() -> {
            // obtain the Class lock via synchronized static method
            synchronized (Synchronized.class) {
                Synchronized.methodE();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadF = new Thread(() -> {
            // obtain the Class lock via synchronized static block
            synchronized (Synchronized.class) {
                Synchronized.methodF();
            }
        });

        threadA.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();

        threadC.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadD.start();

        threadE.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadF.start();
    }

    public synchronized void methodA() {
        System.out.println("Enter into method A");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodB() {
        System.out.println("Enter into method B");
    }

    public void methodC() {
        System.out.println("Enter into method C");
    }

    public void methodD() {
        System.out.println("Enter into method D");
    }

    public synchronized static void methodE() {
        System.out.println("Enter into method E");
    }

    public synchronized static void methodF() {
        System.out.println("Enter into method F");
    }
}
