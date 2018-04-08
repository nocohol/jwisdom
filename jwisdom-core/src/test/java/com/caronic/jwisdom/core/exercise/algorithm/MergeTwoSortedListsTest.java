package com.caronic.jwisdom.core.exercise.algorithm;

import junit.framework.TestCase;

/**
 * Created by nocoh on 2018/2/4.
 */
public class MergeTwoSortedListsTest extends TestCase {

    public void testMerge() throws Exception {

        MergeTwoSortedLists.ListNode list1 = new MergeTwoSortedLists.ListNode(1);
        list1.next = new MergeTwoSortedLists.ListNode(3);
        list1.next.next = new MergeTwoSortedLists.ListNode(5);
        list1.next.next.next = new MergeTwoSortedLists.ListNode(7);

        MergeTwoSortedLists.ListNode list2 = new MergeTwoSortedLists.ListNode(2);
        list2.next = new MergeTwoSortedLists.ListNode(4);
        list2.next.next = new MergeTwoSortedLists.ListNode(6);
        list2.next.next.next = new MergeTwoSortedLists.ListNode(8);

        MergeTwoSortedLists.ListNode expectedResult = new MergeTwoSortedLists.ListNode(1);
        expectedResult.next = new MergeTwoSortedLists.ListNode(2);
        expectedResult.next.next = new MergeTwoSortedLists.ListNode(3);
        expectedResult.next.next.next = new MergeTwoSortedLists.ListNode(4);
        expectedResult.next.next.next.next = new MergeTwoSortedLists.ListNode(5);
        expectedResult.next.next.next.next.next = new MergeTwoSortedLists.ListNode(6);
        expectedResult.next.next.next.next.next.next = new MergeTwoSortedLists.ListNode(7);
        expectedResult.next.next.next.next.next.next.next = new MergeTwoSortedLists.ListNode(8);

        MergeTwoSortedLists.ListNode actualResult = new MergeTwoSortedLists().merge(list1, list2);


    }
}