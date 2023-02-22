package me.imatveev.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxGuests {
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
