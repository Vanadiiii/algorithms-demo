package me.imatveev.queue;

public class BoundedStack<T> {
    private final int maxSize;
    private final Object[] data;
    private int top;

    public BoundedStack(int size) {
        this.maxSize = size;
        this.data = new Object[size];
        this.top = -1;
    }

    public void push(T elem) {
        this.data[++top] = elem;
    }

    public T pop() {
        return (T) this.data[top--];
    }

    public T peek() {
        return (T) this.data[top];
    }

    public T peek(int index) {
        return (T) this.data[index];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("BoundedStack(")
                .append(maxSize)
                .append(")[");

        for (int i = 0; i < top; ++i) {
            builder.append(data[i])
                    .append(", ");
        }

        if (top >= 0) {
            builder.append(data[top]);
        }

        return builder.append(']')
                .toString();
    }
}
