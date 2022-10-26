package me.imatveev.aston;


/**
 * Задачи 24-12
 * Проверка строки на полиндром.
 * но с нюансом, сделать это так, что бы не создавать новые строки, массивы
 * и не использовать еще какие-то структуры данных, короче использовать только оригинальную строку
 */
public class Palindrome {
    public static void main(String[] args) {
        final String src = "А роза упала на лапу Азора";

        System.out.println(isPalindrome(src));
    }

    public static boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;

        char startChar;
        char endChar;
        while (start < end) {
            startChar = str.charAt(start);
            endChar = str.charAt(end);

            if (!Character.isLetter(startChar)) {
                ++start;
                continue;
            }
            if (!Character.isLetter(endChar)) {
                --end;
                continue;
            }
            if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                return false;
            }
            ++start;
            --end;
        }

        return true;
    }
}
