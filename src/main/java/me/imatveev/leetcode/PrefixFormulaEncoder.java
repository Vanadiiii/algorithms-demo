package me.imatveev.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * given: formula, like '2[ab2[cd]3e]
 * <br>
 * result: decoded string -> aabbccccddddeeeeee
 */
public class PrefixFormulaEncoder {

    public static void main(String[] args) {
        String formula = "2ab3[c4[d]]e3f";

        System.out.println(encode(formula));
    }

    public static String encode(String formula) {
        int coefficient = 1;
        final Queue<Integer> coefQueue = new LinkedList<>();
        final StringBuilder builder = new StringBuilder();
        boolean coveredByBrackets = true;

        for (int i = 0; i < formula.length(); ++i) {
            char symbol = formula.charAt(i);

            if (Character.isDigit(symbol)) {
                int digit = Character.digit(symbol, 10);
                coefQueue.add(digit);
                coefficient *= digit;
                if (formula.charAt(i + 1) != '[') {
                    coveredByBrackets = false;
                }
            } else if (symbol == '[') {
                //do nothing
            } else if (symbol == ']') {
                final Integer digit = coefQueue.poll();
                coefficient /= digit;
                coveredByBrackets = true;
            } else {
                for (int j = 0; j < coefficient; ++j) {
                    builder.append(symbol);
                }
                if (!coveredByBrackets) {
                    final Integer digit = coefQueue.poll();
                    coefficient /= digit;
                    coveredByBrackets = true;
                }
            }
        }

        return builder.toString();
    }
}
