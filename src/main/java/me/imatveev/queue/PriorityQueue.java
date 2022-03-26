package me.imatveev.queue;

public class PriorityQueue<T extends Comparable<T>> {
    private static final int INITIAL_SIZE = 10;
    private int size;
    private Object[] data;

    public PriorityQueue() {
        this.data = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    /**
     * complexity - O(n)
     */
    public void insert(T value) {
        if (size == 0) {
            data[0] = value;
        } else {
            int index = 0;
            while (index < size && ((T) data[index]).compareTo(value) < 0) {
                index++;
            }

            if (data.length == size) {
                resize();
            }

            if (index != size) {
                for (int i = index; i < size; ++i) {
                    swap(i, i + 1);
                }
            }
            data[index] = value;
        }
        ++size;
    }

    /**
     * complexity - O(1)
     */
    public T remove() {
        return (T) data[--size];
    }

    private void swap(int leftIdx, int rightIdx) {
        final Object swapped = data[leftIdx];
        data[rightIdx] = data[leftIdx];
        data[leftIdx] = swapped;
    }

    private void resize() {
        final Object[] swapped = data;
        data = new Object[data.length * 2];
        System.arraycopy(swapped, 0, data, 0, swapped.length);
    }

    public int size() {
        return size;
    }

    public T show() {
        if (size == 0) {
            return null;
        } else {
            return (T) data[size];
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
}
