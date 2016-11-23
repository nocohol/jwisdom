package com.caronic.jwisdom.core.exercise.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caronic on 2016/11/12.
 */
public class MergeTwoSortedLists {
    public static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        LinkedList<Integer> linkedList1 = (LinkedList<Integer>)list1;
        LinkedList<Integer> linkedList2 = (LinkedList<Integer>)list2;
        LinkedList<Integer> mergedList = new LinkedList<>();
        if (linkedList1 == null || linkedList1.isEmpty())
            return list2;
        if (linkedList2 == null || linkedList2.isEmpty())
            return list1;

        while (!linkedList1.isEmpty() || !linkedList2.isEmpty()) {
            Integer cur = null;
            if (linkedList1.isEmpty()) {
                cur = linkedList2.remove();
            }
            else if (linkedList2.isEmpty()) {
                cur = linkedList1.remove();
            }
            else {
                if (linkedList1.getFirst() > linkedList2.getFirst()) {
                    cur = linkedList2.remove();
                } else {
                    cur = linkedList1.remove();
                }
            }
            mergedList.add(cur);
        }
        return mergedList;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(1,2,3,4,5,6));
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(2,3,4,5,6,7));
        List<Integer> mergedList = MergeTwoSortedLists.merge(list1, list2);
        mergedList.forEach(System.out::println);
    }
}
