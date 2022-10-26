package me.imatveev.aston;


/**
 *01-02-2021
 * нужно вычислить максимальный размер последовательности единиц, при условии, что мы ДОЛЖНЫ удалить один элемент из массива
 * //    // Дан массив из нулей и единиц.
 * ////  Нужно определить, какой максимальный по длине подинтервал единиц можно получить,
 * ////  удалив ровно один элемент массива.
 * //
 * //
 * //// maxOnes({0, 0}) == 0
 * //// maxOnes({1, _0}) == 1
 * //// maxOnes({_0, 1}) == 1
 * //// maxOnes({1, 1, 1, 1, _0, 0}) == 4
 * //// maxOnes({1, 1, 1, _0, 1, 1, 0, 0, 1, 1, 1, 1}) == 5
 * //// maxOnes({1, 1, 1, 0, 1, 1, _0, 1, 1, 1, 1}) == 6
 */
public class MaxOnes {
    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0};
        System.out.println(maxOnes(arr));
    }

    public static int maxOnes(int[] array) {
        int firstZeroIdx;
        int secondZeroIdx = -1;
        int thirdZeroIdx = -1;

        int max = 0;
        for (int i = 0; i < array.length; ++i) {
            final int value = array[i];

            if (value == 0) {
                firstZeroIdx = secondZeroIdx;
                secondZeroIdx = thirdZeroIdx;
                thirdZeroIdx = i;

                max = Math.max(max, thirdZeroIdx - firstZeroIdx - 2);
            }
        }

        return max;
    }
}
