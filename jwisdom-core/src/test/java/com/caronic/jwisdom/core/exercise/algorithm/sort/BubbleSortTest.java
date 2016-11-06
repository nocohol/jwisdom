package com.caronic.jwisdom.core.exercise.algorithm.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * BubbleSort Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 6, 2016</pre>
 */
@RunWith(BlockJUnit4ClassRunner.class)
//@SpringBootTest(classes = {JwisdomCoreApplicationTest.class})
//@ContextConfiguration(classes = {JwisdomApplicationConfig.class})
public class BubbleSortTest {
    Sortable<Integer> bubbleSort;
    Sortable<Integer> chooseSort;
    Sortable<Integer> insertSort;
    Sortable<Integer> quickSort;
    Integer[] testData = new Integer[100];
    Comparator<Integer> comparator = null;

    @Before
    public void before() throws Exception {
        Random random = new Random(100);
        for (int i=0; i<100; i++) {
            int data = random.nextInt(100);
            testData[i] = data;
        }
        comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : o1 < o2 ? -1 : 0;
            }
        };
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: sort(E[] dataSet, Comparator<E> comparator)
     */
    @Test
    public void testBubbleSort() throws Exception {
        bubbleSort = new BubbleSort<>();
        Integer[] dataSet = Arrays.copyOf(testData, 100);
        bubbleSort.sort(dataSet, comparator);
        for (Integer data : dataSet) {
            System.out.println(data);
        }
    }

    @Test
    public void testChooseSort() throws Exception {
        chooseSort = new ChooseSort<>();
        Integer[] dataSet = Arrays.copyOf(testData, 100);
        chooseSort.sort(dataSet, comparator);
        for (Integer data : dataSet) {
            System.out.println(data);
        }
    }

    @Test
    public void testInsertSort() throws Exception {
        insertSort = new InsertSort<>();
        Integer[] dataSet = new Integer[] {2, 4, 3, 6, 4, 7, 2};
        insertSort.sort(dataSet, comparator);
        for (Integer data : dataSet) {
            System.out.println(data);
        }
    }

    @Test
    public void testQuickSort() throws  Exception {
        quickSort = new QuickSort<>();
        Integer[] dataSet = new Integer[] {2, 4, 3, 6, 4, 7, 2};
        quickSort.sort(dataSet, comparator);
        for (Integer data : dataSet) {
            System.out.println(data);
        }
    }

} 
