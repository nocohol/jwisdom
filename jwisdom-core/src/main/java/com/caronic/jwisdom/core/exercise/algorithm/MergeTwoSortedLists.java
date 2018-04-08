package com.caronic.jwisdom.core.exercise.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caronic on 2016/11/12.
 */
public class MergeTwoSortedLists {
    public static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode mergedList;
        if (list1.val < list2.val) {
            mergedList = list1;
            mergedList.next = merge(list1.next, list2);
        } else {
            mergedList = list2;
            mergedList.next = merge(list1, list2.next);
        }
        return  mergedList;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
