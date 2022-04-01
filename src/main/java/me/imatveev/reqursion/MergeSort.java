package me.imatveev.reqursion;

import java.util.Comparator;

@SuppressWarnings({"unchecked", "raw"})
public class MergeSort<T extends Comparable<T>> {
    private final T[] data;
    private final Object[] swap;
    private final Comparator<T> comparator;

    public MergeSort(T[] data, Comparator<T> comparator) {
        this.data = data;
        this.comparator = comparator;
        this.swap = new Object[data.length];
    }

    public void sort() {
        sortHelper(0, data.length - 1);
    }

    private void sortHelper(int leftIdx, int rightIdx) {
        if (leftIdx != rightIdx) {
            int midIdx = (leftIdx + rightIdx) / 2;
            sortHelper(leftIdx, midIdx);
            sortHelper(midIdx + 1, rightIdx);
            merge(leftIdx, midIdx + 1, rightIdx);
        }
    }

    private void merge(int leftIdx, int rightIdx, int upperBound) {
        int swapIdx = 0;
        int mid = rightIdx - 1;
        int low = leftIdx;
        int size = upperBound - leftIdx + 1;

        while (leftIdx <= mid && rightIdx <= upperBound) {
            if (comparator.compare(data[leftIdx], data[rightIdx]) < 0) {
                swap[swapIdx++] = data[leftIdx++];
            } else {
                swap[swapIdx++] = data[rightIdx++];
            }
        }

        while (leftIdx <= mid) {
            swap[swapIdx++] = data[leftIdx++];
        }
        while (rightIdx <= upperBound) {
            swap[swapIdx++] = data[rightIdx++];
        }

        //copy back
        for (swapIdx = 0; swapIdx < size; ++swapIdx) {
            data[low + swapIdx] = (T) swap[swapIdx];
        }
    }
}
