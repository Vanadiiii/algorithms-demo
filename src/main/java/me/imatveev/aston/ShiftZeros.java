package me.imatveev.aston;

import java.util.Arrays;

/**
 * задача 30-12-2021 Передвинуть все нули в конец, чтобы порядок ненулевых не изменился:
 * In: 1, 0, 2, 0, 3 -> 1, 2, 3, x, x
 * Out: 1, 2, 3, 0, 0
 */
public class ShiftZeros {
    public static void main(String[] args) {
        final int[] arr = {1, 0, 2, 0, 3};
        shiftZeros(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shiftZeros(int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            final int value = array[start];

            if (value != 0) {
                ++start;
            } else {
                array[start] = array[end];
                array[end] = value;
                --end;
            }
        }
    }
}
