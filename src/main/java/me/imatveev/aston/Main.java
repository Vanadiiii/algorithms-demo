package me.imatveev.aston;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }

    /**
     * Дано множество из N натуральных чисел.
     * Необходимо выбрать его подмножество с максимальной четной суммой.
     * Вывести эту сумму.
     * [2,1,3,4,5] = 14
     */
    public int findMaxEvenSum(int[] array) {
        int minOdd = Integer.MAX_VALUE;
        int sum = 0;

        for (int num : array) {
            sum += num;
            if (num % 2 == 1 && num < minOdd) {
                minOdd = num;
            }
        }

        if (sum % 2 == 0) {
            return sum;
        } else {
            return sum - minOdd;
        }
    }

    /**
     * Есть массив целых чисел и число K.
     * Найти индексы двух таких (не обязательно различных) чисел в массиве,
     * сумма которых равна K
     * Input: nums = [2,7,11,15], K = 13
     * Output: [0,2]
     */
    public static int[] findNums(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            int sum = array[start] + array[end];

            if (sum == k) {
                return new int[]{start, end};
            } else if (sum < k) {
                ++start;
            } else {
                --end;
            }
        }

        return null;
    }

    public static int[] findNums1(int[] array, int k) {
        final Map<Integer, Integer> numIndexMap = new HashMap<>();

        for (int i = 0; i < array.length; ++i) {
            int value = array[i];

            final Integer idx2 = numIndexMap.get(k - value);
            if (idx2 != null) {
                return new int[]{idx2, i};
            }
            numIndexMap.put(value, i);
        }

        return null;
    }

    /**
     * Дан массив из нулей и единиц. Нужно определить, какой максимальный по длине подинтервал единиц можно получить,
     * удалив ровно один элемент массива.
     * maxOnes(new int[]{0, 0}) == 0
     * maxOnes(new int[]{1, 0}) == 1
     * maxOnes(new int[]{0, 1}) == 1
     * maxOnes(new int[]{1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1}) == 5
     * maxOnes(new int[]{1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}) == 6
     * <p>
     * Удалять один элемент из массива обязательно.
     * Требуется решение без дополнительной памяти - O(1), и за линейное время - O(N), исходный массив менять нельзя
     */
    public int maxOnes(int[] array) {
        int result = 0;

        int firstResult = 0;
        int secondResult = 0;

        for (int num : array) {
            firstResult += num;

            if (num == 0) {
                secondResult = firstResult;
                firstResult = 0;
            }

            result = Math.max(result, firstResult + secondResult);
        }

        return result;
    }


    /**
     * Дан список интов, повторяющихся элементов в списке нет.
     * Нужно преобразовать это множество в строку, сворачивая соседние по числовому ряду числа в диапазоны.
     * <p>
     * Примеры:
     * [1,4,5,2,3,9,8,11,0] => "0-5,8-9,11"
     * [1,4,3,2] => "1-4"
     * [1,4] => "1,4"
     */
    public static String convert(int[] range) {
        Arrays.sort(range);

        final StringBuilder builder = new StringBuilder();
        builder.append(range[0]);

        boolean isRange = false;
        for (int i = 1; i < range.length; ++i) {
            if (range[i] - range[i - 1] > 1) {
                if (isRange) {
                    builder.append('-')
                            .append(range[i - 1]);
                }

                builder.append(", ")
                        .append(range[i]);
                isRange = false;
            } else {
                isRange = true;
            }
        }

        if (isRange) {
            builder.append('-')
                    .append(range[range.length - 1]);
        }

        return builder.toString();
    }

    /**
     * Написать функцию, которая определяет, является ли переданная строка палиндромом (читается слева направо и справа налево одинаково)
     * <p>
     * Примеры палиндромов:
     * <p>
     * Казак
     * А роза упала на лапу Азора
     * Анна
     * Ограничение по памяти О(1)
     * <p>
     * Собственно нужно реализовать метод
     */
    public boolean isPalindrome(String value) {
        value = value.toLowerCase().replace(" ", "");

        int start = 0;
        int end = value.length() - 1;

        while (start < end) {
            if (value.charAt(start) != value.charAt(end)) {
                return false;
            }
            ++start;
            --end;
        }

        return true;
    }
}
