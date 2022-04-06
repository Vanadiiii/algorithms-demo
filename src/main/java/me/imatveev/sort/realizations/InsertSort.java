package me.imatveev.sort.realizations;

import me.imatveev.sort.AbstractSortingAlgorithm;

import java.util.Comparator;

public class InsertSort<T extends Comparable<T>> extends AbstractSortingAlgorithm<T> {
    /**
     * <p>complexity - O(N^2)</p>
     */
    public void sort(T[] data, Comparator<T> comparator) {
        for (int i = 1; i < data.length; ++i) {
            T swap = data[i];
            int place = findPlace(data, comparator, i);
            moveToRight(data, place, i - 1);
            data[place] = swap;
        }
    }

    private int findPlace(T[] data, Comparator<T> comparator, int comparedIdx) {
        int idx = comparedIdx - 1;
        while (idx >= 0 && comparator.compare(data[comparedIdx], data[idx]) > 0) {
            --idx;
        }
        return idx + 1;
    }

    /**
     * @param data  array
     * @param start start index
     * @param end   end index
     * @apiNote move elements to right for one from index
     */
    private void moveToRight(T[] data, int start, int end) {
        for (int i = end; i >= start; --i) {
            swap(data, i, i + 1);
        }
    }
}
