package com.caronic.jwisdom.core.util;

/**
 * Created by nocoh on 2018/2/3.
 */
public class StringUtils {

    public static String reverseWithSpace(String str) {
        if (str == null || str.length() == 0)
            return "";
        int len = str.length();
        StringBuilder sb = new StringBuilder("");
        for (int i = len - 1; i >= 0; i--) {
            sb.append(" ").append(str.charAt(i));
        }
        return sb.toString().replaceFirst(" ", "");
    }

    public static String reverseWithSpace2(String str) {
        if (str == null || str.length() == 0)
            return "";
        char[] values = str.toCharArray();
        char[] valuesWithWhiteSpace = new char[values.length * 2 - 1];
        int n = str.length() - 1;
        if (n == 0) {
            return  str;
        }
        for (int i = (n - 1) >> 1; i >= 0; i--) {
            int k = n - i;
            char begin = values[i];
            char end = values[k];
            valuesWithWhiteSpace[i * 2] = end;
            valuesWithWhiteSpace[i * 2 + 1] = ' ';
            valuesWithWhiteSpace[k * 2] = begin;
            if (k != n)
                valuesWithWhiteSpace[k * 2 + 1] = ' ';
        }
        return new String(valuesWithWhiteSpace);
    }

}
