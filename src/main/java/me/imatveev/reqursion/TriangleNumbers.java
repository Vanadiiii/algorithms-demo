package me.imatveev.reqursion;

public class TriangleNumbers {
    public static int getTriangleNumbersSum(int num) {
        return (num == 1) ? 1 : num + getTriangleNumbersSum(num);
    }
}
