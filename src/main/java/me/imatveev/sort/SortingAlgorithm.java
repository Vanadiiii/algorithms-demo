package me.imatveev.sort;

import java.util.Comparator;

public interface SortingAlgorithm<T extends Comparable<T>> {
    void sort(T[] data, Comparator<T> comparator);
}
