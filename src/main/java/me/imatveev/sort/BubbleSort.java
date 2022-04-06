package me.imatveev.sort;

import java.util.Comparator;

public class BubbleSort<T extends Comparable<T>> {

    /**
     * complexity - O(n^2)
     */
    public void sort(T[] data, Comparator<T> comparator) {
        for (int i = 0; i < data.length - 1; ++i) {
            for (int j = data.length - 1; j > i; --j) {
                if (comparator.compare(data[j], data[j - 1]) > 0) {
                    swap(data, j, j - 1);
                }
            }
        }
    }

    private void swap(T[] data, int idx1, int idx2) {
        T swap = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = swap;
    }
}
