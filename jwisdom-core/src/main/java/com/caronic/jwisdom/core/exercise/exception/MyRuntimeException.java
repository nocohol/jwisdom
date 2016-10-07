package com.caronic.jwisdom.core.exercise.exception;

/**
 * Created by caronic on 2016/10/7.
 */
public class MyRuntimeException extends RuntimeException {

    public MyRuntimeException() {
        super();
    }

    public MyRuntimeException(String message) {
        super(message);
    }

    public MyRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
