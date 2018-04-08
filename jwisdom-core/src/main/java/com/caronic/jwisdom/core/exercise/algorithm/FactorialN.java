package com.caronic.jwisdom.core.exercise.algorithm;

/**
 * Created by nocoh on 2018/2/3.
 */
public class FactorialN implements Compute<Integer, Integer> {

    @Override
    public Integer run(Integer input) {
        return iterate(input);
    }

    private int recursion(int n) {
        int result = n == 0 ? 1 : n;
        if (n > 1) {
            result = n * recursion(n-1);
        }
        return result;
    }

    private int iterate(int n) {
        int result = 1;
        for(int i = n; i > 1; i--) {
            result *= i;
        }
        return result;
    }
}
