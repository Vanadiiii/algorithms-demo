package me.imatveev.linkedlist;

public class StackBasedOnLinkedList<T> {
    private final Node<T> first;
    private final Node<T> last;
    private int size;

    public StackBasedOnLinkedList() {
        this.size = 0;
        this.first = new Node<>();
        this.last = new Node<>();
        first.next = last;
        last.prev = first;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);

        if (size == 0) {
            first.next = node;
            node.prev = first;
        } else {
            Node<T> penultimate = last.prev;
            penultimate.next = node;
            node.prev = penultimate;
        }
        node.next = last;
        last.prev = node;
        ++size;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        Node<T> penultimate = last.prev;
        last.prev = penultimate.prev;
        penultimate.prev.next = last;

        --size;
        return penultimate.data;
    }

    public T peek() {
        if (size == 0) {
            return null;
        }

        return first.next.data;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("QueueBasedOnLinkedList[");

        if (size > 0) {

            int count = 0;
            Node<T> node = first.next;
            while (count < size - 1) {
                builder.append(node.data)
                        .append(", ");
                ++count;
                node = node.next;
            }

            builder.append(last.prev.data);
        }

        return builder.append("]")
                .toString();
    }

    private static final class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
        }

        public Node() {
        }
    }

}
