package me.imatveev.tree;

public class BinaryTree<K extends Comparable<K>, T> {
    private final Node<K, T> root;

    public BinaryTree(K key, T value) {
        this.root = new Node<>(key, value);
    }


    public T find(K key) {
        Node<K, T> current = root;
        int comparation = current.key.compareTo(key);

        while (comparation != 0) {
            if (comparation > 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }

            if (current == null) {
                return null;
            }

            comparation = current.key.compareTo(key);
        }
        return current.value;
    }

    public void insert(K key, T value) {
        Node<K, T> child = new Node<>(key, value);

        Node<K, T> parent = root;
        Node<K, T> current;

        while (true) {
            int comparation = key.compareTo(parent.key);

            if (comparation < 0) {
                current = parent.leftChild;
                if (current == null) {
                    parent.leftChild = child;
                    return;
                }
            } else {
                current = parent.rightChild;
                if (current == null) {
                    parent.rightChild = child;
                    return;
                }
            }
            parent = current;
        }
    }

    public void delete(K key) {
        Node<K, T> parent = root;
        Node<K, T> current = root;
        boolean isLeftChild = false;

        int comparation = key.compareTo(current.key);
        while (comparation != 0) {
            parent = current;

            if (comparation < 0) {
                current = parent.leftChild;
                isLeftChild = true;
            } else {
                current = parent.rightChild;
                isLeftChild = false;
            }

            comparation = key.compareTo(current.key);
        }

        //node has no children
        if (current.leftChild == null && current.rightChild == null) {
            if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }

            //node has both children
        } else if (current.leftChild != null && current.rightChild != null) {
            Node<K, T> heirParent = current;
            Node<K, T> heir = current;
            Node<K, T> current2 = current.rightChild;

            while (current2 != null) {
                heirParent = heir;
                heir = current2;
                current2 = current2.leftChild;
            }

            if (heir != current.rightChild) {
                heirParent.leftChild = heir.rightChild;
                heir.rightChild = current.rightChild;
            }

            //node has only left child
        } else if (current.leftChild != null) {

            if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }

            //node has only right child
        } else {

            if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }

        }
    }

    private static class Node<K extends Comparable<K>, T> {
        private final K key;
        private final T value;
        private Node<K, T> leftChild;
        private Node<K, T> rightChild;

        private Node(K key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.toString(0);
        }

        public String toString(int level) {
            String gap = "\t".repeat(level + 1);

            return "Node{" +
                    "key=" + key +
                    ", data=" + value +
                    (leftChild != null ? ", leftChild=\n" + gap + leftChild.toString(level + 1) : "") +
                    (rightChild != null ? ", rightChild=\n" + gap + rightChild.toString(level + 1) : "") +
                    "}";
        }
    }

    @Override
    public String toString() {
        return "BinaryTree{\n" + root + "}";
    }
}
