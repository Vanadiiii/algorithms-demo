package me.imatveev.leetcode;

public class MergingNodesBetweenZeros {
    public static void main(String[] args) {
        ListNode node = create(0, 1, 0, 3, 0, 2, 2, 0);
//        ListNode node = create(0, 1, 0);
        System.out.println(node);
        System.out.println(mergeNodes(node));
    }

    public static ListNode mergeNodes(ListNode head) {
        head = head.next;
        final ListNode result = new ListNode(head.val);
        ListNode resultPointer = result;
        head = head.next;

        while (head != null) {
            if (head.val == 0 && head.next != null) {
                resultPointer.next = new ListNode();
                resultPointer = resultPointer.next;
            } else if (head.val != 0) {
                resultPointer.val += head.val;
            }

            head = head.next;
        }

        return result;
    }

    public static ListNode create(int value, int... values) {
        final ListNode result = new ListNode(value);

        ListNode node = result;
        for (int j : values) {
            node.next = new ListNode(j);
            node = node.next;
        }
        return result;
    }

    public static class ListNode {
        private int val;
        private ListNode next;

        private ListNode() {

        }

        private ListNode(int val) {
            this.val = val;
        }

        private ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder("[");
            ListNode head = this;
            while (head != null) {
                builder.append(head.val);
                if (head.next != null) {
                    builder.append(", ");
                }
                head = head.next;
            }
            return builder.append("]")
                    .toString();
        }
    }
}
