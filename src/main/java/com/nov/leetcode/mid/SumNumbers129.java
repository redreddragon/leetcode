package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/13 0:33
 * @description
 */
/*
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

        例如，从根到叶子节点路径 1->2->3 代表数字 123。

        计算从根到叶子节点生成的所有数字之和。

        说明: 叶子节点是指没有子节点的节点。
*/
public class SumNumbers129 {
    public int sumNumbers(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        if (root == null) {
            return 0;
        } else {
            nodeStack.push(root);
            numStack.push(root.val);
        }
        int sum = 0;
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int num = numStack.pop();
            if (node.left == null && node.right == null) {
                sum += num;
            }
            if (node.right != null) {
                nodeStack.push(node.right);
                numStack.push(num * 10 + node.right.val);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                numStack.push(num * 10 + node.left.val);
            }
        }
        return sum;
    }

    // 递归更易于理解
    int res = 0;
    public int sumNumbers02(TreeNode root) {
        helper(root, 0);
        return res;
    }

    public void helper(TreeNode root, int temp) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            temp = temp * 10 + root.val;
            res += temp;
            return;
        }
        temp = temp * 10 + root.val;
        helper(root.left, temp);
        helper(root.right, temp);
    }
}
