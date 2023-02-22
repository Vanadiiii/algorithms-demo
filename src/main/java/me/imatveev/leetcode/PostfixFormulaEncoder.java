package me.imatveev.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * given: formula, like '[ab[cd]2e3]2'
 * <br>
 * result: decoded string -> aabbccccddddeeeeee
 */
public class PostfixFormulaEncoder {
    public static void main(String[] args) {
        String formula = "[ab[cd]2e3]2";

        System.out.println(encode(formula));
    }

    public static String encode(String formula) {
        int coefficient = 1;
        final Deque<Integer> coefQueue = new LinkedList<>();
        final StringBuilder builder = new StringBuilder();
        boolean coveredByBrackets = true;

        for (int i = formula.length() - 1; i >= 0; --i) {
            char symbol = formula.charAt(i);

            if (Character.isDigit(symbol)) {
                int digit = Character.digit(symbol, 10);
                coefQueue.addFirst(digit);
                coefficient *= digit;
                if (formula.charAt(i - 1) != ']') {
                    coveredByBrackets = false;
                }
            } else if (symbol == ']') {
                //do nothing
            } else if (symbol == '[') {
                final Integer digit = coefQueue.removeFirst();
                coefficient /= digit;
            } else {
                for (int j = 0; j < coefficient; ++j) {
                    builder.insert(0, symbol);
                }
                if (!coveredByBrackets) {
                    final Integer digit = coefQueue.removeFirst();
                    coefficient /= digit;
                    coveredByBrackets = true;
                }
            }
        }

        return builder.toString();
    }
}
