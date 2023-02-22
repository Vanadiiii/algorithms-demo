package me.imatveev.leetcode.structure;

import java.util.Objects;

public class ListNode {
    private int val;
    private ListNode next;

    private ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    private ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int val) {
        return new ListNode(val);
    }

    public static ListNode of(int val, ListNode next) {
        return new ListNode(val, next);
    }

    public static ListNode of(int val, int... numbers) {
        final ListNode list = new ListNode(val);

        ListNode node = list;
        for (int num : numbers) {
            final ListNode next = new ListNode(num);
            node.setNext(next);
            node = next;
        }

        return list;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
