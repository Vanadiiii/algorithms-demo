package me.imatveev.leetcode;

import me.imatveev.leetcode.structure.TreeNode;

import static me.imatveev.leetcode.structure.TreeNode.*;

public class AddingRowToTree {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (val == 1) {
            return new TreeNode(val, root, null);
        }
        addRec(root, val, depth, 1);
        return root;
    }

    public void addRec(TreeNode node, int val, int destinationDepth, int depth) {
        if (node == null) {
            return;
        }

        if (depth == destinationDepth - 1) {
            node.left = new TreeNode(val, node.left, null);
            node.right = new TreeNode(val, null, node.right);
        } else {
            addRec(node.left, val, destinationDepth, depth + 1);
            addRec(node.right, val, destinationDepth, depth + 1);
        }
    }

    public static void main(String[] args) {
        AddingRowToTree test = new AddingRowToTree();

        System.out.println(
                test.find(new int[]{1, 2, 3, 4}, new int[]{3, 3, 4, 4, 4, 5}, new int[]{3, 5, 6})
        );

        TreeNode node = of(4,
                of(2,
                        of(3),
                        of(1)
                ),
                of(6,
                        of(5),
                        null
                )
        );

        System.out.println(test.addOneRow(node, 1, 2));
    }

    public int find(int[] arr1, int[] arr2, int[] arr3) {
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;

        int result = arr1[0];

        while (counter1 < arr1.length && counter2 < arr2.length && counter3 < arr2.length) {
            int value1 = arr1[counter1];
            if (value1 > result) {
                result = value1;
            } else if (value1 < result) {
                ++counter1;
            }

            int value2 = arr2[counter2];
            if (value2 > result) {
                result = value2;
            } else if (value2 < result) {
                ++counter2;
            }

            int value3 = arr3[counter3];
            if (value3 > result) {
                result = value3;
            } else if (value3 < result) {
                ++counter3;
            }

            if (value1 == value2 && value2 == value3) {
                return value1;
            }
        }

        return -1;
    }
}
