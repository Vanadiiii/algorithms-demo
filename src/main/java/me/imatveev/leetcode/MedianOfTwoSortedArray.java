package me.imatveev.leetcode;

public class MedianOfTwoSortedArray {
    public static void main(String[] args) {

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int lengthSum = nums1.length + nums2.length;

        boolean isOdd = lengthSum % 2 == 1;
        int medianIdx = lengthSum / 2;

        int i = 0;
        int j = 0;
        boolean isFirst = false;
        int value1 = 0;
        int value2 = 0;

        while (i + j < medianIdx) {
            value1 = nums1[i];
            value2 = nums2[j];

            if (value1 > value2) {
                ++j;
                isFirst = true;
            } else {
                ++i;
                isFirst = false;
            }
        }

        if (!isOdd) {
            return (float) (value1 + value2) / 2;
        } else if (isFirst) {
            return (float) value1;
        } else {
            return (float) value2;
        }
    }
}
