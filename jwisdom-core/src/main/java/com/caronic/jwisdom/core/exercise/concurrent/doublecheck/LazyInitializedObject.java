package com.caronic.jwisdom.core.exercise.concurrent.doublecheck;

/**
 * Created by caronic on 2016/7/30.
 */
public class LazyInitializedObject {

    private volatile static LazyInitializedObject instance;
    private String value;

    private LazyInitializedObject(String value) {
        this.value = value;
    }

    public static LazyInitializedObject getInstance() {
        if (instance == null) {
            synchronized (LazyInitializedObject.class) {
                if (instance == null) {
                    instance = new LazyInitializedObject("test");
                    return instance;
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
//        Thread a = new Thread(() -> {
//            LazyInitializedObject object = LazyInitializedObject.getInstance();
//            System.out.println(object.hashCode());
//            System.out.println(object.value);
//        });
//
//        Thread b = new Thread(() -> {
//            LazyInitializedObject object = LazyInitializedObject.getInstance();
//            System.out.println(object.hashCode());
//            System.out.println(object.value);
//        });
//
//        a.start();
//        b.start();

        for (int i=0; i<100; i++) {
            Thread thread = new Thread(() -> {
                LazyInitializedObject object = LazyInitializedObject.getInstance();
                System.out.println(object.hashCode());
                System.out.println(object.value);
            });
            thread.start();
        }
    }

}
