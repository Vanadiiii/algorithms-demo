package me.imatveev.reqursion;

public class TriangleNumbers {
    public static int getTriangleNumbersSum(int num) {
        return (num == 1) ? 1 : num + getTriangleNumbersSum(num - 1);
    }

    public static int getTriangleNumbersSum2(int num) {
        int sum = 0;
        while (num >= 1) {
            sum += num--;
        }
        return sum;
    }
}
