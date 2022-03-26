package me.imatveev.linkedlist;

public class LinkedList<T> {
    private final Node<T> node;
    private int size;

    public LinkedList() {
        this.size = 0;
        this.node = new Node<>();
    }

    public void add(T value) {
        if (size == 0) {
            node.value = value;
        } else {
            Node<T> node = this.node;

            while (node.next != null) {
                node = node.next;
            }

            node.next = new Node<>(value);
        }
        ++size;
    }

    public T get(int index) {
        Node<T> node = getNode(index);

        if (node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    public T remove(int index) {
        if (size == 0) {
            return null;
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }

        Node<T> parent = getNode(index - 1);
        if (parent == null) {
            return null;
        }

        Node<T> node = parent.next;
        parent.next = node.next;

        --size;
        return node.value;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("LinkedList[");

        if (size != 0) {
            Node<T> node = this.node;

            while (node != null) {
                builder.append(node.value);
                if (node.next != null) {
                    builder.append(", ");
                }
                node = node.next;
            }
        }

        return builder.append(']')
                .toString();
    }

    private static final class Node<T> {
        private Node<T> next;

        private T value;

        public Node(T value) {
            this.value = value;
        }

        public Node() {
        }

    }

    private Node<T> getNode(int index) {
        if (size == 0) {
            return null;
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }

        int idx = 0;
        Node<T> node = this.node;
        while (idx < index) {
            node = node.next;
            ++idx;
        }

        return node;
    }
}
