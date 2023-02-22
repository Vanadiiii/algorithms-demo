package me.imatveev.aston;

import me.imatveev.leetcode.structure.Graph;
import me.imatveev.leetcode.structure.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Solving {
    public static void main(String[] args) {
        Solving solving = new Solving();

        System.out.println(solving.compress(new int[]{1, 2, 3, 4, 6, 7, 9, 10}));
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
    public List<Integer> findAllSimpleNumbers(int[] nums) {
        final List<Integer> result = new ArrayList<>();

        for (final int value : nums) {
            if (isSimpleNumber(value)) {
                result.add(value);
            }
        }

        return result;
    }

    private boolean isSimpleNumber(int num) {
        int hiBound = num / 2;
        int lowBound = 2;

        while (lowBound <= hiBound) {
            if (num % lowBound == 0) {
                return false;
            }
            ++lowBound;
            hiBound = num / lowBound;
        }
        return true;
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
//        return (a + b + (int) Math.sqrt((a - b) * (a - b))) / 2;
        return a - ((a - b) & ((a - b) >> (Integer.SIZE - 1)));
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

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (isRemoveRow[i] || isRemoveColumn[j]) {
                    array[i][j] = 0;
                }
            }
        }
    }


    /**
     * 8. Напишите код для удаления дубликатов из несортированного связного списка
     */
    public ListNode removeDuplicates(ListNode list) {
        if (list == null || list.getNext() == null) {
            return list;
        }

        final Set<Integer> valueSet = new HashSet<>();

        ListNode parent = list;
        valueSet.add(parent.getVal());

        ListNode node = parent.getNext();
        while (node != null) {
            final boolean isAlreadyExists = !valueSet.add(node.getVal());

            if (isAlreadyExists) {
                final ListNode next = node.getNext();

                parent.setNext(next);
                node = next;
            } else {
                parent = node;
                node = node.getNext();
            }
        }

        return list;
    }

    /**
     * 9. Как реализовать стек, в котором кроме стандартных функций push и рор будет поддерживаться функция min, <br>
     * возвращающая минимальный элемент? <br>
     * Все операции - push, рор и min - должны выполняться за время O(1).
     */
    public static class MinStack {
        private final Stack<Integer> stack;
        private int minimum;

        public MinStack() {
            this.stack = new Stack<>();
        }

        public void push(int value) {
            if (stack.isEmpty()) {
                stack.push(value);
                minimum = value;
                return;
            }

            if (value < minimum) {
                stack.push(2 * value - minimum);
                minimum = value;
            } else {
                stack.push(value);
            }
        }

        public int pop() {
            final int value = stack.pop();

            if (value >= minimum) {
                return value;
            } else {
                final int result = minimum;
                minimum = 2 * minimum - value;

                return result;
            }
        }

        public int min() {
            return minimum;
        }
    }

    /**
     * 9. Для заданного направленного графа разработайте алгоритм, проверяющий существование маршрута между двумя узлами.
     */
    public <T> boolean hasPathInDirectionalGraph(Graph<T> graph, T from, T to) {
        return graph.hasPathDfs(from, to);
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

    /**
     * Задача 30.08.2022<br>
     * Дан список интов, повторяющихся элементов в списке нет.<br>
     * Нужно преобразовать это множество в строку, сворачивая соседние по числовому ряду числа в диапазоны.<br>
     * <p>
     * Примеры:<br>
     * [1,4,5,2,3,9,8,11,0] => "0-5,8-9,11"<br>
     * [1,4,3,2] => "1-4"<br>
     * [1,4] => "1,4"<br>
     */
    public String compress(int[] array) {
        final int length = array.length;

        int end = 0;
        int start = 0;

        final StringBuilder builder = new StringBuilder("[");

        while (end < length) {
            if (end == length - 1 || array[end + 1] - array[end] > 1) {
                this.writeRange(start, end, array, builder);
                start = end + 1;
            }
            ++end;
        }

        return builder.append("]")
                .toString();
    }

    private void writeRange(int start, int end, int[] array, StringBuilder builder) {
        builder.append(array[start]);
        if (end != start) {
            builder.append("->")
                    .append(array[end]);
        }
        if (end < array.length - 1) {
            builder.append(", ");
        }
    }

    /**
     * Задача №3
     * нужно реализовать функцию oneEditApart , <br>
     * проверяющую, можно ли одну строку получить из другой <br>
     * не более, чем за одно исправление (удаление, добавление, изменение символа)
     * <p>
     * oneEditApart("cat", "dog") -> false <br>
     * oneEditApart("cat", "cats") -> true  <br>
     * oneEditApart("cat", "cut") -> true  <br>
     * oneEditApart("cat", "cast") -> true  <br>
     * oneEditApart("cat", "at") -> true  <br>
     * oneEditApart("cat", "acts") -> false  <br>
     */
    public boolean isOneEditApart(String str1, String str2) {
        return false;
    }
}
