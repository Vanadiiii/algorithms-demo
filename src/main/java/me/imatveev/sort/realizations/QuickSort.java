package me.imatveev.sort.realizations;

import me.imatveev.sort.AbstractSortingAlgorithm;

import java.util.Comparator;

public class QuickSort<T extends Comparable<T>> extends AbstractSortingAlgorithm<T> {
    /**
     * <p>complexity - O(N* log N)</p>
     * <p>hardcode for right element like 'criteria'</p>
     */
    @Override
    public void sort(T[] data, Comparator<T> comparator) {
        recSort(data, comparator, 0, data.length - 1);
    }

    private void recSort(T[] data, Comparator<T> comparator, int left, int right) {
        if (right > left) {
            T rightElem = data[right];

            final int middle = findMiddle(data, rightElem, comparator, left, right);

            recSort(data, comparator, left, middle - 1);
            recSort(data, comparator, middle + 1, right);
        }
    }

    private int findMiddle(T[] data, T rightElem, Comparator<T> comparator, int left, int right) {
        int left2 = left - 1;
        int right2 = right;

        while (true) {
            //find the smaller than rightElem
            while (comparator.compare(data[++left2], rightElem) < 0) ;
            //find the bigger than rightElem
            while (right2 > 0 && comparator.compare(data[--right2], rightElem) > 0) ;

            if (left2 >= right2) {
                break;
            } else {
                swap(data, left2, right2);
            }
        }
        swap(data, left2, right);

        return left2;
    }
}
