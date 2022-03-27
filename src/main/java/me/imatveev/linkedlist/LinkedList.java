package me.imatveev.linkedlist;

public class LinkedList<T> {
    private Node<T> node;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    /**
     * complexity - O(1);
     * add value to first node
     */
    public void add(T value) {
        Node<T> node = new Node<>(value);
        node.next = this.node;
        this.node = node;
        ++size;
    }

    /**
     * complexity - O(N);
     * add value to some indexed place
     */
    public void add(T value, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("index more than size");
        }
        if (index == 0) {
            add(value);
            return;
        }

        Node<T> parent = getNode(index - 1);
        Node<T> child = parent.next;
        Node<T> node = new Node<>(value);

        parent.next = node;
        node.next = child;
    }

    /**
     * complexity - O(N)
     */
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

        if (index == 0) {
            Node<T> node = this.node;
            this.node = this.node.next;
            size--;
            return node.value;
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
