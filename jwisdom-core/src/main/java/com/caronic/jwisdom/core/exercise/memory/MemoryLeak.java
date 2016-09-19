package com.caronic.jwisdom.core.exercise.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caronic on 2016/8/26.
 */
public class MemoryLeak {

    public static void main(String[] args) {
        String str = "abc";
        Map<Integer, String> map = new HashMap<>();
        map.put(1, str);
        str = null;
        System.gc();
        System.out.println(map.get(1));
        while (true) {

        }
    }

}
