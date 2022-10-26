package me.imatveev.leetcode;

import static me.imatveev.leetcode.DeepestLeavesSum.TreeNode.*;

public class DeepestLeavesSum {
    public static void main(String[] args) {
        final TreeNode tree = of(50,
                of(54,
                        of(98),
                        of(6,
                                null,
                                of(34)
                        )
                )
        );

        System.out.println(deepestLeavesSum(tree));
    }

    public static int deepestLeavesSum(TreeNode root) {
        final Sum sum = new Sum();

        calculateLeavesSumRec(root, 0, sum);

        return sum.value;
    }

    public static void calculateLeavesSumRec(TreeNode node, int level, Sum sum) {
        if (node == null) {
            return;
        }
        if (node.left != null || node.right != null) {
            calculateLeavesSumRec(node.left, level + 1, sum);
            calculateLeavesSumRec(node.right, level + 1, sum);
            return;
        }

        if (level == sum.level) {
            sum.value += node.val;
        } else if (level > sum.level) {
            sum.value = node.val;
            sum.level = level;
        }
    }

    private static class Sum {
        int level = 0;
        int value = 0;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        private TreeNode(int val) {
            this.val = val;
        }

        private TreeNode(int val, TreeNode left, TreeNode right) {
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
    }
}
