package com.caronic.jwisdom.core.exercise.algorithm;

import org.springframework.util.Assert;

/**
 * Created by caronic on 2016/11/25.
 */
public class LongestSubArrayWithoutRepeating {

    public static int solution(String s) {
        if (s == null || "".equals(s))
            return 0;
        int len = 0, tmpLen = 0;
        String currentString = "";
        for (int i=0; i<s.length(); i++) {
            String c = s.substring(i,i+1);
            if (!currentString.contains(c)) {
                tmpLen++;
                currentString += c;
            } else if (tmpLen >= len) {
                len = tmpLen;
                tmpLen = 0;
                currentString = "";
            }
        }
        return Math.max(len, currentString.length());
    }

    public static void main(String[] args) {
        String str1 = "bbbbbb";
        Assert.isTrue(solution(str1) == 1);
        String str2 = "abcabcabcbb";
        Assert.isTrue(solution(str2) == 3);
        String str3 = "akwwec";
        Assert.isTrue(solution(str3) == 3);
    }

}
