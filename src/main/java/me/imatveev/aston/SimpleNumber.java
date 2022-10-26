package me.imatveev.aston;

/**
 * Задача 23-12:  определить, что число является простым.
 */
public class SimpleNumber {
    public static void main(String[] args) {
        System.out.println(isSimple(27644437));
    }

    public static boolean isSimple(int number) {
        int start = 2;

        int end = number / 2;
        while (start < end) {
            end = number / start;
            if (number % start == 0) {
                return false;
            }

            ++start;
        }

        return true;
    }

    public static int isSimpleStupid(int number) {
        for (int i = 2; i < number / 2; ++i) {
            if (number % i == 0) {
                return i;
            }
        }
        return number;
    }
}
