package me.imatveev.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeTraversal {

    public static void main(String[] args) {
        System.out.println(rangesToString(new int[]{1, 2, 3, 5, 6, 7, 9, 11}));
    }

    public static String rangesToString(int[] numbers) {
        int startIdx = 0;
        int endIdx = 0;

        final StringBuilder builder = new StringBuilder();

        int currentIdx = 1;

        int start = numbers[startIdx];
        int end = numbers[endIdx];
        int current = numbers[currentIdx];

        while (currentIdx < numbers.length) {
            current = numbers[currentIdx];

            if (current - end > 1) {
                builder.append(start);
                if (end > start) {
                    builder.append("->")
                            .append(end);
                }
                builder.append(",");

                startIdx = currentIdx;
                start = numbers[startIdx];
            }

            endIdx = currentIdx;
            end = numbers[endIdx];
            ++currentIdx;
        }

        if (current - end > 1) {
            builder.append(start);
            if (end > start) {
                builder.append("->")
                        .append(end);
            }
        }

        return builder.toString();
    }

    public static int getMaxGuest(List<Guest> guests) {
        //key - day, value - count
        final Map<Integer, Integer> guestCountMap = new HashMap<>();

        int max = 0;

        for (Guest guest : guests) {
            int start = guest.first;
            int finish = guest.second;

            for (int i = start; i <= finish; ++i) {
                int alreadyExistedCount = guestCountMap.getOrDefault(i, 0);
                int count = guest.getPersonCount() + alreadyExistedCount;

                max = Math.max(max, count);

                guestCountMap.put(i, count);
            }
        }

        return max;
    }

    public static abstract class Guest {
        protected final int first;
        protected final int second;

        public Guest(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public abstract int getPersonCount();
    }

    public static class Single extends Guest {
        public static Single of(int first, int second) {
            return new Single(first, second);
        }

        public Single(int first, int second) {
            super(first, second);
        }

        @Override
        public int getPersonCount() {
            return 1;
        }
    }

    public static class Pair extends Guest {
        public static Pair of(int first, int second) {
            return new Pair(first, second);
        }

        public Pair(int first, int second) {
            super(first, second);
        }

        @Override
        public int getPersonCount() {
            return 2;
        }
    }
}
