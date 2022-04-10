package me.imatveev.sort.realizations;

import me.imatveev.sort.AbstractSortingAlgorithm;

import java.util.Comparator;

/**
 * <p>complexity - O(N * log N)</p>
 * <p>weakness - it claim more memory (for swapArea)</p>
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSortingAlgorithm<T> {
    @Override
    public void sort(T[] data, Comparator<T> comparator) {
        Object[] swapArea = new Object[data.length];

        recSort(data, swapArea, comparator, 0, data.length - 1);
    }

    private void recSort(T[] data, Object[] swapArea, Comparator<T> comparator, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;

            recSort(data, swapArea, comparator, start, middle);
            recSort(data, swapArea, comparator, middle + 1, end);
            merge(data, swapArea, comparator, start, middle + 1, end);
        }
    }

    private void merge(T[] data, Object[] swapArea, Comparator<T> comparator, int leftStart, int rightStart, int rightEnd) {
        int leftIdx = leftStart;
        int rightIdx = rightStart;
        int swapIdx = 0;

        while (leftIdx < rightStart && rightIdx <= rightEnd) {
            if (comparator.compare(data[leftIdx], data[rightIdx]) < 0) {
                swapArea[swapIdx++] = data[leftIdx++];
            } else {
                swapArea[swapIdx++] = data[rightIdx++];
            }
        }

        while (leftIdx < rightStart) {
            swapArea[swapIdx++] = data[leftIdx++];
        }
        while (rightIdx <= rightEnd) {
            swapArea[swapIdx++] = data[rightIdx++];
        }

        int size = rightEnd - leftStart + 1;
        for (swapIdx = 0; swapIdx < size; ++swapIdx) {
            data[swapIdx + leftStart] = (T) swapArea[swapIdx];
        }
    }
}
