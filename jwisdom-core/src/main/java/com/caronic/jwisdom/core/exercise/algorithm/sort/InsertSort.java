package com.caronic.jwisdom.core.exercise.algorithm.sort;

import java.util.Comparator;

/**
 * Created by caronic on 2016/11/6.
 * 4, 2, 1, 6, 4, 7, 2
 * 2, 4, 1, 6, 4, 7, 2
 * 1, 2, 4, 6, 4, 7, 2
 * 1, 2, 4, 6, 4, 7, 2
 * 1, 2, 4, 4, 6, 7, 2
 *
 */
public class InsertSort<E> implements Sortable<E>{

    @Override
    public void sort(E[] dataSet, Comparator<E> comparator) {
        if (dataSet != null && dataSet.length > 1) {
            int out,in;
            for (int i = 1; i < dataSet.length; i++) {
                out = in = i;
                E temp = dataSet[out];
                while (in > 0 && comparator.compare(dataSet[in-1], temp) >= 0) {
                    dataSet[in] = dataSet[in - 1];
                    in--;
                }
                dataSet[in] = temp;
            }
        }
    }
}
