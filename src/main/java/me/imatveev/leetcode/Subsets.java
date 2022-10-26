package me.imatveev.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {

    }

    public List<List<Integer>> subsets(int[] nums) {
        final List<List<Integer>> subsets = new ArrayList<>(nums.length * nums.length);

        for (int size = nums.length; size > 0; --size) {
            final List<List<Integer>> subset = new ArrayList<>(size);
            for (int i = 0; i < size; ++i) {
                subset.add(new ArrayList<>());
            }

            for (int i = 0; i < size; ++i) {
                
            }
        }

        return subsets;//todo
    }
}
