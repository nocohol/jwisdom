package com.caronic.jwisdom.core.exercise.algorithm.sort;

import java.util.Comparator;

/**
 * Created by caronic on 2016/11/6.
 */
public class QuickSort<E> implements Sortable<E> {
    @Override
    public void sort(E[] dataSet, Comparator<E> comparator) {
        quickSort(dataSet, 0, dataSet.length - 1, comparator);
    }

    private void quickSort(E[] dataSet, int left, int right, Comparator<E> comparator) {
        if (right - left <= 0) {
            return;
        } else {
            E pivot = dataSet[right];
            int partition = partitionIt(dataSet, left, right, pivot, comparator);
            quickSort(dataSet, left, partition-1, comparator);
            quickSort(dataSet, partition + 1, right, comparator);
        }
    }

    private int partitionIt(E[] dataSet, int left, int right, E pivot, Comparator<E> comparator) {
        int leftPtr = left;
        int rightPtr = right-1;
        while (true) {
            while(comparator.compare(dataSet[++leftPtr], pivot) < 0); // find the element which is greater than pivot from left to right
            while (rightPtr > 0 && comparator.compare(dataSet[--rightPtr], pivot) > 0); // find the element which is less than pivot from right to left
            if (leftPtr >= rightPtr) // break till left and right are met
                break;
            else {
                E temp = dataSet[rightPtr];
                dataSet[rightPtr] = dataSet[leftPtr];
                dataSet[leftPtr] = temp;
            }
        }
        E temp = dataSet[leftPtr];
        dataSet[leftPtr] = dataSet[right];
        dataSet[right] = temp;
        return leftPtr;
    }
}
