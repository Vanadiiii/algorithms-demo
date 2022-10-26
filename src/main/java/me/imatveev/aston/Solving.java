package me.imatveev.aston;

import java.util.HashMap;

public class Solving {
    public static void main(String[] args) {
        Solving solving = new Solving();

        System.out.println(solving.findIndexInSortedArray(new int[]{1, 2, 3, 4, 5}, 3));
    }

    /**
     * <p>1. Найти индекс числа в массиве сортированных чисел</p>
     * <p>usinig binary searching</p>
     * <p>time complexity - O(log2(N))</p>
     */
    public int findIndexInSortedArray(int[] sortedArray, int someValue) {
        int start = 0;
        int end = sortedArray.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            final int value = sortedArray[mid];

            if (value == someValue) {
                return mid;
            } else if (value > someValue) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    /**
     * <p>2. Найти все простые числа из массива</p>
     * <p></p>
     */
    public int[] findAllSimpleNumbers(int[] nums) {
        return nums; //todo
    }

    /**
     * <p>3. Написать получение n-го элемента ряда чисел Фибоначчи</p>
     * <p>time complexity - O(N)</p>
     */
    public int findFibonacciNumber(int num) {
        if (num < 2) {
            return num;
        }

        int first = 0;
        int second = 1;
        int result = num;
        while (num > 1) {
            result = second + first;
            first = second;
            second = result;

            --num;
        }

        return result;
    }

    /**
     * fizzBuzz
     */
    public void fizzBuzz() {
        for (int i = 1; i <= 100; ++i) {
            if (i % 5 == 0 && i % 3 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else {
                System.out.println(i);
            }
        }
    }

    /**
     * <p>5. Напишите метод, находящий максимальное из двух чисел без использования if-else или любых других операторов сравнения.</p>
     */
    public int max(int a, int b) {
        return (a + b + (int) Math.sqrt((a - b) * (a - b))) / 2;
    }

    /**
     * <p>6. Для двух строк напишите метод, определяющий, является ли одна строка перестановкой другой.</p>
     * <p>complexity - </p>
     */
    public boolean isPermutation(String source, String permuted) {
        int sourceLength = source.length();
        int permutedLength = permuted.length();

        if (sourceLength != permutedLength) {
            return false;
        }

        final HashMap<Character, Integer> acc = new HashMap<>(sourceLength);

        for (int i = 0; i < sourceLength; ++i) {
            final char symbol = source.charAt(i);
            acc.put(symbol, acc.getOrDefault(symbol, 0) + 1);
        }

        for (int i = 0; i < permutedLength; ++i) {
            final char symbol = permuted.charAt(i);
            final Integer count = acc.get(symbol);
            if (count == null) {
                return false;
            }

            if (count > 1) {
                acc.put(symbol, count - 1);
            } else {
                acc.remove(symbol);
            }
        }

        return acc.isEmpty();
    }

    /**
     * <p>7. Напишите алгоритм, реализующий следующее условие: если элемент матрицы MxN равен 0, то весь столбец и вся строка обнуляются.</p>
     */
    public void nullifyArray(int[][] array) {
        final boolean[] isRemoveRow = new boolean[array.length];
        final boolean[] isRemoveColumn = new boolean[array[0].length];

        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                final int value = array[i][j];
                if (value == 0) {
                    isRemoveRow[i] = true;
                    isRemoveColumn[j] = true;
                }
            }
        }


    }


    /**
     * <p>Дан массив целых чисел и число k.
     * <br>Нужно найти такой отрезок в массиве, сумма элементов которого будет равна k.
     * <br>Если такого нет, вернуть ошибку</p>
     * <p>complexity - O(N)</p>
     */
    public int[] findSubArray(int[] array, int k) {
        int arrayLength = array.length;

        int left = 0;
        int right = 0;
        int sum = 0;

        int pointer = 0;
        while (pointer < arrayLength && sum != k) {
            if (sum < k) {
                sum += array[pointer];
                ++right;
                ++pointer;
            } else {
                sum -= array[left];
                ++left;
            }
        }

        if (sum != k) {
            throw new RuntimeException("ooops");
        }

        final int length = right - left;
        final int[] result = new int[length];
        System.arraycopy(array, left, result, 0, length);

        return result;
    }

}
