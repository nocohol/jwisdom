package com.caronic.jwisdom.core.exercise.algorithm;

/**
 * An integer array, need to find the sub array their sum is the biggest one.
 * e.g. [1,2,-4,2,3,-2,1,2], the proper output should be []
 * Created by caronic on 2016/11/19.
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] A = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int result = getBiggestSubArray(A);
        System.out.println(result);
    }

    public static int getBiggestSubArray(int[] A) {
        int result = A[0];
        int temp = result;
        for (int i=0; i<A.length; i++) {
            temp = Math.max(temp + A[i], A[i]);
            result = Math.max(result, temp);
        }
        return result;
    }

}
