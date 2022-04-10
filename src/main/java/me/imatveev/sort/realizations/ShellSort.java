package me.imatveev.sort.realizations;

import me.imatveev.sort.AbstractSortingAlgorithm;

import java.util.Comparator;

public class ShellSort<T extends Comparable<T>> extends AbstractSortingAlgorithm<T> {

    /**
     * complexity - from O(N³/⁴) to O(N⁷/⁸)
     */
    @Override
    public void sort(T[] data, Comparator<T> comparator) {
        int step = findFirstStep(data.length);

        while (step >= 1) {
            System.out.println("step - " + step);
            nSort(step, data, comparator);
            step = nextStep(step);
        }
    }

    private void nSort(int step, T[] data, Comparator<T> comparator) {
        for (int outer = step; outer < data.length; ++outer) {
            T swapped = data[outer];

            int inner = outer;
            while (inner >= step && comparator.compare(data[inner - step], swapped) > 0) {
                data[inner] = data[inner - step];
                inner -= step;
            }
            data[inner] = swapped;
        }
    }

    private int findFirstStep(int size) {
        int step = 1;
        while (step <= size / 3) {
            step = step * 3 + 1;
        }
        return step;
    }

    private int nextStep(int step) {
        return (step - 1) / 3;
    }
}
