package com.caronic.jwisdom.core.exercise.algorithm.sort;

import com.caronic.jwisdom.core.annotation.PerformanceAware;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created by caronic on 2016/11/6.
 * Example:
 * 2, 4, 3, 6, 4, 7
 * 2, 4, 3, 6, 4, 7
 * 2, 3, 4, 6, 4, 7
 * 2, 3, 4, 6, 4, 7
 * 2, 3, 4, 4, 6, 7
 */
@Component
public class ChooseSort<E> implements Sortable<E> {
    @Override
    @PerformanceAware
    public void sort(E[] dataSet, Comparator<E> comparator) {
        if (dataSet != null && dataSet.length > 1) {
            for (int i = 0; i < dataSet.length; i++) {
                int smallestIndex = i;
                for (int j = i+1; j < dataSet.length; j++) {
                    // find the smallest index in the sequential data set
                    if (comparator.compare(dataSet[j], dataSet[smallestIndex]) < 0) {
                        smallestIndex = j;
                    }
                }
                // only swap current data with the smallest data in the sub sequential data set
                E temp = dataSet[i];
                dataSet[i] = dataSet[smallestIndex];
                dataSet[smallestIndex] = temp;
            }
        }
    }
}
