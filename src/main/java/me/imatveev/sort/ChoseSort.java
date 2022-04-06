package me.imatveev.sort;

import java.util.Comparator;

public class ChoseSort<T extends Comparable<T>> {

    /**
     * <p>complexity - O(n^2)</p>
     * <p>work in a 2 time faster, then bubble sort</p>
     */
    public void sort(T[] data, Comparator<T> comparator) {
        for (int i = 0; i < data.length - 1; ++i) {
            int smallestIndex = findSmallest(data, comparator, i);
            swap(data, smallestIndex, i);
        }
    }

    private void swap(T[] data, int idx1, int idx2) {
        T swap = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = swap;
    }

    private int findSmallest(T[] data, Comparator<T> comparator, int start) {
        int idx = start;
        for (int i = start; i < data.length; ++i) {
            if (comparator.compare(data[i], data[idx]) < 0) {
                idx = i;
            }
        }

        return idx;
    }
}
