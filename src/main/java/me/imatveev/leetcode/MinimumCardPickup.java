package me.imatveev.leetcode;

import java.util.HashSet;
import java.util.Set;

public class MinimumCardPickup {
    public static void main(String[] args) {

    }

    public static int minimumCardPickup(int[] cards) {
        final Set<Integer> cardSet = new HashSet<>();

        for (int i = 0; i < cards.length; ++i) {
            final boolean exists = !cardSet.add(cards[i]);

            if (exists) {
                return i;
            }
        }

        return -1;
    }
}
