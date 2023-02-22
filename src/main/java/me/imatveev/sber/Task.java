package me.imatveev.sber;

import java.util.HashMap;
import java.util.Map;

public class Task {
    public static void main(String[] args) {

        System.out.println(canReordered("abc", "bcc"));
    }

    public static boolean canReordered(String source, String out) {
        final Map<Character, Integer> charCountMap = new HashMap<>();

        for (int i = 0; i < source.length(); ++i) {
            final char symbol = source.charAt(i);

            charCountMap.put(
                    symbol,
                    charCountMap.getOrDefault(symbol, 0) + 1
            );
        }

        for (int i = 0; i < out.length(); ++i) {
            final char symbol = out.charAt(i);

            final Integer count = charCountMap.get(symbol);
            if (count == null || count == 0) {
                return false;
            }

            charCountMap.put(symbol, count - 1);
        }

        return true;
    }

    public static String reversePhrase(String phrase) {
        final String[] words = phrase.trim()
                .split("\\s+");

        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; --i) {
            result.append(words[i]);
            if (i != 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }
}
