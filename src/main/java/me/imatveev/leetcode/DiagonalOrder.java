package me.imatveev.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DiagonalOrder {
    public static void main(String[] args) {
        List<List<Integer>> nums = List.of(
                List.of(1, 2, 3, 4, 5),
                List.of(6, 7),
                List.of(8),
                List.of(9, 10, 11),
                List.of(12, 13, 14, 15, 16)
        );

        System.out.println(Arrays.toString(findDiagonalOrder(nums)));
    }

    public static int[] findDiagonalOrder(List<List<Integer>> nums) {
        final List<LinkedList<Integer>> restoredNums = new ArrayList<>();
        int resultArraySize = 0;

        for (int i = 0; i < nums.size(); i++) {
            final List<Integer> row = nums.get(i);
            for (int j = 0; j < row.size(); j++) {
                final int idxSum = i + j;
                ++resultArraySize;

                if (restoredNums.size() == idxSum) {
                    restoredNums.add(new LinkedList<>());
                }

                final LinkedList<Integer> list = restoredNums.get(idxSum);

                list.push(row.get(j));
            }
        }

        final int[] result = new int[resultArraySize];

        int i = 0;
        for (List<Integer> diagonal : restoredNums) {
            for (Integer num : diagonal) {
                result[i++] = num;
            }
        }

        return result;

    }

}
