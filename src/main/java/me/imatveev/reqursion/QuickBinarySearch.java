package me.imatveev.reqursion;

public class QuickBinarySearch<T extends Comparable<T>> {
    private final T[] data;

    public QuickBinarySearch(T[] data) {
        this.data = data;
    }

    public int search(T value) {
        return searchHelper(value, 0, data.length);
    }

    private int searchHelper(T value, int leftBound, int rightBound) {
        if (leftBound >= rightBound) {
            return -1;
        }

        int middle = (rightBound + leftBound) / 2;
        T middleValue = data[middle];

        if (value == middleValue) {
            return middle;
        } else if (value.compareTo(middleValue) < 1) {
            return searchHelper(value, leftBound, middle);
        } else {
            return searchHelper(value, middle + 1, rightBound);
        }
    }
}
