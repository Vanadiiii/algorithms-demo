package me.imatveev.sort;

public abstract class AbstractSortingAlgorithm<T extends Comparable<T>> implements SortingAlgorithm<T> {
    protected void swap(T[] data, int idx1, int idx2) {
        T swap = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = swap;
    }
}
