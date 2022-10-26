package me.imatveev.aston;

import java.util.Arrays;

/**
 * задачи 13-01-2021
 * Дан массив целых чисел и число k. Нужно найти такой отрезок в массиве,
 * сумма элементов которого будет равна k. Если такого нет, вернуть ошибку
 */
public class FindSubArrayInArrayBySum {
    public static void main(String[] args) {
        final int[] arr = {1, 2, -3, 10, 10, 5, 6, 7};
        System.out.println(Arrays.toString(findSubArray(arr, 7)));
    }

    public static int[] findSubArray(int[] array, int expectedSum) throws RuntimeException {
        int start = 0;
        int sum = 0;
        int idx = 0;

        while (idx < array.length) {
            if (sum == expectedSum) {
                final int[] result = new int[idx - start];
                System.arraycopy(array, start, result, 0, idx - start);
                return result;
            } else if (sum > expectedSum) {
                sum -= array[start];
                ++start;
            } else {
                sum += array[idx];
                ++idx;
            }
        }

        throw new RuntimeException("Ooops");
    }
}
