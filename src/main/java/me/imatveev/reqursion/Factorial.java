package me.imatveev.reqursion;

public class Factorial {
    public static int getFactorial(int num) {
        return (num == 1 || num == 0) ? 1 : num * getFactorial(num - 1);
    }
}
