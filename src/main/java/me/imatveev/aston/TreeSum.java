package me.imatveev.aston;

import me.imatveev.leetcode.structure.TreeNode;

import java.util.Stack;

import static me.imatveev.leetcode.structure.TreeNode.of;

public class TreeSum {
    public static void main(String[] args) {
        TreeNode node = of(1,
                of(2, of(3)),
                of(4, of(5))
        );

        System.out.println(getSumRecursive(node));
    }

    public static int getSumRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int result = root.val;
        result += getSumRecursive(root.left);
        result += getSumRecursive(root.right);

        return result;
    }

    public static int getSumIterative(TreeNode root) {
        final Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        int result = 0;
        while (!stack.isEmpty()) {
            final TreeNode current = stack.pop();
            result += current.val;

            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }

        return result;
    }
}
