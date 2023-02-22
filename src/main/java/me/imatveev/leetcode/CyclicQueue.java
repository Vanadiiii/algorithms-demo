package me.imatveev.leetcode;

public class CyclicQueue<T> {
    private final Object[] array;
    private final int maxSize;

    private int size;

    private int start;
    private int end;

    public CyclicQueue(int size) {
        this.array = new Object[size];
        this.maxSize = size;
        this.size = 0;
        this.start = 0;
        this.end = 0;
    }

    public void add(T value) {
        array[start] = value;
        start = shiftRight(start, maxSize);

        if (size != maxSize) {
            ++size;
        } else {
            end = start;
        }
    }

    public T poll() {
        if (size == 0) {
            return null;
        }

        final T value = (T) array[end];

        end = shiftRight(end, maxSize);
        --size;

        return value;
    }

    private int shiftRight(int idx, int maxSize) {
        if (idx == maxSize - 1) {
            return 0;
        } else {
            return idx + 1;
        }
    }
}
