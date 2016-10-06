package com.caronic.jwisdom.core.exercise.spring.rpc;

import java.io.Serializable;

/**
 * Created by caronic on 2016/9/27.
 */
public class Account implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
