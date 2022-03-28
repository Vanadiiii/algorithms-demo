package me.imatveev.linkedlist;

import java.util.Comparator;

public class SortedLinkedList<T extends Comparable<T>> {
    private final Node<T> first;
    private final Node<T> last;
    private final Comparator<T> comparator;
    private int size;

    public SortedLinkedList(Comparator<T> comparator) {
        this.size = 0;
        this.comparator = comparator;
        this.first = new Node<>();
        this.last = new Node<>();
        first.next = last;
        last.prev = first;
    }

    /**
     * complexity - O(N)
     */
    public void add(T value) { 
        Node<T> node = new Node<>(value);

        if (size == 0) {
            first.next = node;
            node.prev = first;
            last.prev = node;
            node.next = last;

            ++size;
            return;
        }

        Node<T> iterated = first.next;
        while (iterated != last) {
            if (comparator.compare(value, iterated.value) > 0) {
                node.next = iterated;
                node.prev = iterated.prev;
                iterated.prev.next = node;
                iterated.prev = node;
                ++size;
                return;
            }

            iterated = iterated.next;
        }

        node.next = iterated;
        node.prev = iterated.prev;
        iterated.prev.next = node;
        iterated.prev = node;

        ++size;
    }

    /**
     * complexity - O(N)
     */
    public T get(int index) {
        Node<T> node = getNode(index);

        if (node == null || node == first) {
            return null;
        } else {
            return node.value;
        }
    }

    /**
     * complexity - O(N)
     */
    public T remove(int index) {
        if (size == 0) {
            return null;
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("negative index");
        }

        Node<T> removedNode = getNode(index);
        removedNode.prev.next = removedNode.next;
        removedNode.next.prev = removedNode.prev;

        --size;
        return removedNode.value;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("SortedLinkedList[");

        if (size != 0) {
            Node<T> node = first.next;

            while (node != last) {
                builder.append(node.value);
                if (node != last.prev) {
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
        private Node<T> prev;
        private final T value;

        public Node(T value) {
            this.value = value;
        }

        public Node() {
            this.value = null;
        }

    }

    private Node<T> getNode(int index) {
        if (size == 0 || index < 0) {
            return first;
        }

        int count = 0;
        Node<T> node = first.next;
        while (count < index) {
            node = node.next;
            ++count;
        }

        return node;
    }
}
