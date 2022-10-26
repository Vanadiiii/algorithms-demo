package me.imatveev.leetcode;

import java.io.Serializable;
import java.util.ArrayList;

public class OverloadingDemo {
    public static void main(String[] args) {
        Serializable s = pick("d", new ArrayList<String>());
    }

    public static <T extends Monster & Roaring> void doSomething(T monster) {
        if (monster.isSelected()) {
            monster.roar();
        }
    }

    static <T> T pick(T __, T a2) {
        return a2;
    }

    public static class Monster {
        boolean isSelected() {
            return true;
        }
    }

    public interface Roaring {
        void roar();
    }
}
