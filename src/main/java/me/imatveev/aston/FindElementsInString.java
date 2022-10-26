package me.imatveev.aston;

public class FindElementsInString {
    public static void main(String[] args) {
        final String str = "aaaabbbbccccccdddddd";

        System.out.println(findElementSetInString(str));
    }

    public static String findElementSetInString(String str) {
        final boolean[] alphabet = new boolean[26];
        final int shift = 97;

        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); ++i) {
            final char symbol = str.charAt(i);
            final int symbolPosition = symbol - shift;

            if (!alphabet[symbolPosition]) {
                result.append(symbol);
                alphabet[symbolPosition] = true;
            }
        }

        return result.toString();
    }
}
