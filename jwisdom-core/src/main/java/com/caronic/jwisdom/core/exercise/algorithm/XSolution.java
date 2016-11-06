package com.caronic.jwisdom.core.exercise.algorithm;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by caronic on 2016/10/18.
 */
public class XSolution {

    static class Tree {
        public int x;
        public Tree l;
        public Tree r;

        public Tree(int x, Tree l, Tree r) {
            this.x = x;
            this.l = l;
            this.r = r;
        }
    }

    // Rotate Array
    public static int[] solution(int A[], int K) {
//        LinkedList<Tree> nodes = new LinkedList<>();
//        LinkedList<Integer> counts = new LinkedList<>();
//        nodes.add(T);
//        counts.add(0);
//        while (!nodes.isEmpty()) {
//            Tree rootNode = nodes.remove();
//            int count = counts.remove();
//
//            if(rootNode.l == null && rootNode.r == null) {
//                return count;
//            }
//
//            if (rootNode.l != null) {
//                counts.add(count+1);
//                if (rootNode.l.x <= rootNode.x) {
//                    nodes.add(rootNode.l);
//                }
//            }
//
//            if (rootNode.r != null ) {
//                counts.add(count+1);
//                if (rootNode.r.x <= rootNode.x) {
//                    nodes.add(rootNode.r);
//                }
//            }
//        }
//        return counts.size() + 1;
        return null;
    }

    // Odd Occurrences in Array
    public static int solution2(int[] A) {
        java.util.Set<Integer> hashset = new HashSet<>();
        for (int num : A ) {
            if (!hashset.add(num)) {
                hashset.remove(num);
            }
        }
        return hashset.iterator().next();
    }

    public static int solution3(Tree T) {
        LinkedList<Tree> nodes = new LinkedList<>();
        int count = 1;
        nodes.add(T);
        while (!nodes.isEmpty()) {
            Tree rootNode = nodes.remove();
            if(rootNode.l == null && rootNode.r == null) {
                return count;
            }

            if (rootNode.l != null) {
                if (rootNode.l.x >= rootNode.x) {
                    count+=1;
                }
                if (rootNode.l.x < rootNode.x) {
                    //count++;
                    nodes.add(rootNode.l);
                }
            }

            if (rootNode.r != null ) {
                if (rootNode.r.x >= rootNode.x) {
                    count+=1;
                }
                if (rootNode.r.x < rootNode.x) {
                    //count++;
                    nodes.add(rootNode.r);
                }
            }
        }
        return count;
    }


    public static int solution4(String s) {
        int num = Integer.valueOf(s, 2);
        int count = 0;
        while(num != 0) {
            num = num % 2 == 0 ? num / 2 : (num - 1);
            count++;
        }
        return count;
    }

    public static int solution6(int[] A) {
        int total = 0;
        for (int i = 0; i < A.length; i++) {
            total+=A[i];
        }

        int diff = 0;
        for (int p = 0; p < A.length; p++) {
            int left = 0;
            for (int i = 0; i <= p; i++) {
                left += A[i];
            }
            int a = Math.abs(2* left - total);
            if (p == 0)
                diff = a;
            else
                diff = diff <= a ? diff : a;
        }
        return diff;
    }

    public static int solution5(int[][] grid, int[] ships) {
        int volley = 0;
        return volley;
    }

    public static String lengthOfLongestSubstring(String s) {
        if (s == null)
            return null;
        if (s.length() == 1)
            return s;

        String longest = "";

        for (int i=0; i<s.length(); i++) {
            String currStr = s.substring(i,i+1);
            String subStr = s.substring(i+1);
            int nextPos = subStr.indexOf(currStr);
            if (longest.length() < (nextPos - i + 1)) {
                longest = s.substring(i, nextPos);
            }
        }
        return longest;
    }

    public static void main(String[] args) {

//        Tree root = null;
//        Tree tree1 = new Tree(8, null, null);
//        Tree tree2 = new Tree(7, null, null);
//        Tree tree3=new Tree(1, null, null);
//        Tree tree4 = new Tree(2, tree1, tree2);
//        Tree tree5 = new Tree(6, tree3, null);
//        root = new Tree(8, tree4, tree5);
//
//        int result = solution3(root);
//        System.out.println(result);


//        int[] data = new int[100];
//        for(int i=0; i<100; i++) {
//            data[i] = i;
//        }
//        int[] result = solution(new int[] {1,2,3,4,5,6,7,8}, 9);
//
//        for (int i: result) {
//            System.out.println(i);
//        }

//        int result = solution2(new int[] {1, 9999, 9999, 9999, 1});
//        System.out.println(result);
//        int result2 = solution4("011100");
//        System.out.println(result2);

        System.out.println(solution6(new int[]{3,3}));
        System.out.println(lengthOfLongestSubstring("pwwkew"));

    }

}
