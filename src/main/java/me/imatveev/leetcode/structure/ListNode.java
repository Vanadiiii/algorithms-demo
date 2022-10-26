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
        return val == listNode.val && Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}
