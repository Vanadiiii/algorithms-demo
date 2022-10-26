package me.imatveev.leetcode.structure;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode of(int val) {
        return new TreeNode(val);
    }

    public static TreeNode of(int val, TreeNode left) {
        return new TreeNode(val, left, null);
    }

    public static TreeNode of(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }

    @Override
    public String toString() {
        return "Node(" + val +
                ((left != null) ? ", " + left.toString() : "") +
                ((right != null) ? ", " + right.toString() : "") +
                ")";
    }
}