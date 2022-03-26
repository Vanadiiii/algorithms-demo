package me.imatveev.queue;

public class Queue<T> {
    private static final int INITIAL_SIZE = 10;
    private int end;
    private int front;
    private Object[] data;

    public Queue() {
        this.data = new Object[INITIAL_SIZE];
        this.end = 0;
        this.front = 0;
    }

    public Queue(int size) {
        this.data = new Object[size];
        this.end = 0;
        this.front = 0;
    }

    /**
     * complexity - O(1)
     */
    public void push(T value) {
        if (end != data.length - 1) {
            data[end] = value;
        } else {
            throw new RuntimeException("Can't resize yet...");
        }

        ++end;
    }

    /**
     * complexity - O(1)
     */
    public T pop() {
        if (front == end) {
            return null;
        } else {
            return (T) data[front++];
        }
    }

    public int size() {
        return end - front;
    }

    public T peek() {
        if (front == end) {
            return null;
        } else {
            return (T) data[front];
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Queue[");

        if (end != 0) {
            for (int i = front; i < end; ++i) {
                builder.append(data[i]);

                if (i != end - 1) {
                    builder.append(", ");
                }
            }
        }

        return builder.append(']')
                .toString();
    }
}
