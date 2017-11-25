package com.caronic.jwisdom.core.exercise.algorithm;

/**
 * Created by caronic on 2016/8/27.
 *
 * It is to demonstrate how to find the first unique item in an array.
 */
public class Example1 {

    public int firstUniqueChar(String s) {
        for (int i=0; i<s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Example1 example = new Example1();
        long start = System.currentTimeMillis();
        System.out.println(example.firstUniqueChar("leetcode"));
        System.out.println(example.firstUniqueChar("loveleetcode"));
        System.out.println(example.firstUniqueChar("abcabcabc"));
        System.out.println("time: " + (System.currentTimeMillis() - start));
        System.out.println("test");
    }

}
