package com.caronic.jwisdom.core.exercise.algorithm.sort;

import com.caronic.jwisdom.core.annotation.PerformanceAware;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Created by caronic on 2016/11/1.
 */
@Component
public class BubbleSort<E> implements Sortable<E> {

    @Override
    @PerformanceAware
    public void sort(E[] dataSet, Comparator<E> comparator) {
        if (dataSet != null && dataSet.length > 1) {
            for (int i=0; i<dataSet.length; i++) {
                for (int j=0; j<dataSet.length; j++) {
                    if (comparator.compare(dataSet[j], dataSet[i]) > 0) {
                        E temp = dataSet[i];
                        dataSet[i] = dataSet[j];
                        dataSet[j] = temp;
                    }
                }
            }
        }
    }
}
