package com.caronic.jwisdom.core.exercise.algorithm.sort;

import java.util.Comparator;

/**
 * Created by caronic on 2016/11/1.
 */
public interface Sortable<T> {
    void sort(T[] dataSet, Comparator<T> comparator);
}
