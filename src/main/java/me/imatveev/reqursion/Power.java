package me.imatveev.reqursion;

public class Power {
    public static long execute(long base, long power) {
        if (power == 1) {
            return base;
        }

        if (power % 2 == 0) {
            return execute(base * base, power / 2);
        } else {
            return base * execute(base, power - 1);
        }
    }

    public static long execute2(long base, long power) {
        long multiplier = 1;

        while (power > 1) {
            if (power % 2 == 0) {
                base *= base;
                power /= 2;
            } else {
                power -= 1;
                multiplier *= base;
            }
        }

        return multiplier * base;
    }
}
