package me.imatveev.sort;

import me.imatveev.linkedlist.LinkedList;

public class RadixSort {
    public void sort(final int[] data) {
        final LinkedList<LinkedList<Integer>> storage = new LinkedList<>();

        boolean hasNoFigures = false;
        int figurePosition = 0;

        while (!hasNoFigures) {
            for (int i = 0; i < data.length; ++i) {
                int number = data[i];
            }

            figurePosition++;
        }
    }

    public static void main(String[] args) {
        int value = 123456789;
        System.out.println(getFigure(value, 9));
    }


    private static int getFigure(int number, int position) {
        if (position == 0) {
            return number % 10;
        }

        String numStr = String.valueOf(number);

        int index = numStr.length() - position;
        if (index <= 0) {
            return 0;
        }

        return Integer.parseInt(numStr.substring(index - 1, index));
    }
}
