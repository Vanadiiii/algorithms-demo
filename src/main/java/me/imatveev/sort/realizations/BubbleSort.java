package me.imatveev.sort.realizations;

import me.imatveev.sort.AbstractSortingAlgorithm;

import java.util.Comparator;

public class BubbleSort<T extends Comparable<T>> extends AbstractSortingAlgorithm<T> {
    /**
     * complexity - O(N^2)
     */
    @Override
    public void sort(T[] data, Comparator<T> comparator) {
        for (int i = 0; i < data.length - 1; ++i) {
            for (int j = data.length - 1; j > i; --j) {
                if (comparator.compare(data[j], data[j - 1]) > 0) {
                    swap(data, j, j - 1);
                }
            }
        }
    }
}
