package me.imatveev.leetcode;

public class DeciBinaryNumbers {
    public static void main(String[] args) {
        System.out.println(minPartitions("8922131"));
    }

    public static int minPartitions(String n) {
        final int shift = 48;
        int max = 0;
        for (int i = 0; i < n.length(); ++i) {
            int value = n.charAt(i) - shift;
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
