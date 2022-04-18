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
            int medianIdx = findMedian(data, left, right, comparator);
            T element = data[medianIdx];

            final int middle = findMiddle(data, element, comparator, left, right);

            recSort(data, comparator, left, middle - 1);
            recSort(data, comparator, middle + 1, right);
        }
    }

    private int findMedian(T[] data, int left, int right, Comparator<T> comparator) {
        int median = (left + right) / 2;

        if (comparator.compare(data[left], data[median]) > 0) {
            swap(data, left, median);
        }
        if (comparator.compare(data[median], data[right]) > 0) {
            swap(data, median, right);
        }

        return median;
    }

    private int findMiddle(T[] data, T element, Comparator<T> comparator, int left, int right) {
        int left2 = left - 1;
        int right2 = right;

        while (true) {
            //find the smaller than rightElem
            while (comparator.compare(data[++left2], element) < 0) ;
            //find the bigger than rightElem
            while (right2 > 0 && comparator.compare(data[--right2], element) > 0) ;

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
