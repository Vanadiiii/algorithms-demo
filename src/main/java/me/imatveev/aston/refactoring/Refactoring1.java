package me.imatveev.aston.refactoring;

public class Refactoring1 {
    private static final int BIG_NUM_LENGTH = 4;
    private static final int UP_STEP = 1;
    private static final int DOWN_STEP = -1;
    private static final int SAME_STEP = 0;

    public static void main(String[] args) {
        Refactoring1 refactoring1 = new Refactoring1();
        System.out.println(refactoring1.isPrettyNum(432));
    }

    public boolean isPrettyNum(int num) {
        final String numStr = String.valueOf(num);

        if (numStr.length() > BIG_NUM_LENGTH) {
            if (isInOrder(numStr, SAME_STEP)) {
                return true;
            }

            if (isInOrder(numStr, UP_STEP)) {
                return true;
            }
        }

        return isInOrder(numStr, DOWN_STEP);
    }

    private boolean isInOrder(String numStr, int step) {
        int value = Character.getNumericValue(numStr.charAt(0));

        for (int i = 1; i < numStr.length(); ++i) {
            int nextValue = Character.getNumericValue(numStr.charAt(i));
            if (nextValue != value + step) {
                return false;
            }
            value += step;
        }

        return true;
    }

    public int isPrettyNumOld(String s) {
        if (s.length() > 4) {
            char i = s.charAt(0);
            for (char l : s.substring(1).toCharArray()) {
                if (l != i) {
                    i = 0;
                    break;
                }
            }
            if (i != 0) {
                return 1;
            }
            int j = Integer.parseInt(s.substring(0, 1));
            for (char l : s.substring(1).toCharArray()) {
                if (Integer.parseInt(Character.toString(l)) == j + 1) {
                    j += 1;
                } else {
                    j = 0;
                    break;
                }
            }
            if (j != 0) {
                return 1;
            }
        }
        int j = Integer.parseInt(Character.toString(s.charAt(s.length() - 1)));
        for (int i = s.length() - 2; i >= 0; i--) {
            if (Integer.parseInt(Character.toString(s.charAt(i))) == j + 1) {
                j += 1;
            } else {
                j = 0;
                break;
            }
        }
        if (j != 0) {
            return 1;
        }
        return 0;
    }
}
