package me.imatveev.queue;

public class PriorityQueue2<T extends Comparable<T>> {
    private static final int INITIAL_SIZE = 10;
    private int size;
    private Object[] data;

    public PriorityQueue2() {
        this.data = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    public PriorityQueue2(int size) {
        this.data = new Object[size];
        this.size = 0;
    }

    /**
     * complexity - O(1)
     */
    public void push(T value) {
        data[size] = value;
        ++size;
    }

    /**
     * complexity - O(N)
     */
    public T pop() {
        if (size == 0) {
            return null;
        }

        int maxValueIdx = getMaxValueIdx();
        T maxValue = (T) data[maxValueIdx];

        for (int i = maxValueIdx + 1; i < size - 1; ++i) {
            swap(i, i + 1);
        }

        --size;
        return maxValue;
    }

    public int size() {
        return size;
    }


    /**
     * complexity - O(N)
     */
    public T peek() {
        if (size == 0) {
            return null;
        } else {
            return (T) data[getMaxValueIdx()];
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("PriorityQueue[");

        if (size != 0) {
            for (int i = 0; i < size; ++i) {
                builder.append(data[i]);

                if (i != size - 1) {
                    builder.append(", ");
                }
            }
        }

        return builder.append(']')
                .toString();
    }

    private void swap(int leftIdx, int rightIdx) {
        final Object swapped = data[leftIdx];
        data[rightIdx] = data[leftIdx];
        data[leftIdx] = swapped;
    }

    private int getMaxValueIdx() {
        int maxValueIdx = 0;
        T maxValue = (T) data[maxValueIdx];

        int idx = 1;
        while (idx < size) {
            T value = (T) data[idx];
            if (maxValue.compareTo(value) < 0) {
                maxValue = value;
                maxValueIdx = idx;
            }
            ++idx;
        }

        return maxValueIdx;
    }
}
