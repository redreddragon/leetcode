package com.nov.leetcode.easy;

import com.nov.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/12 17:43
 * @description
 */
public class HasPathSum112 {
    // 不记录路径只判定存在的递归
    public boolean hasPathSum(TreeNode root, int sum) {
        Stack<Integer> nodeStack = new Stack<>();
        nodeStack.push(sum);
        return traversal(root, nodeStack);
    }

    public boolean traversal(TreeNode root, Stack<Integer> nodeStack) {
        boolean flag = false;
        if (root != null) {
            int tmp = nodeStack.peek();
            nodeStack.push(tmp - root.val);
            if (root.left == null && root.right == null && nodeStack.peek() == 0) {
                return true;
            }
            if (root.left != null) {
                flag = traversal(root.left, nodeStack);
            }
            if (flag) {
                return true;
            }
            if (root.right != null) {
                flag = traversal(root.right, nodeStack);
            }
            nodeStack.pop();
        }
        return flag;
    }

    // 只判定存在的迭代
    public boolean hasPathSum02(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        sumStack.push(sum - root.val);
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int tmp = sumStack.pop();
            if (node.left == null && node.right == null && tmp == 0) {
                return true;
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                sumStack.push(tmp - node.left.val);
            }
            if (node.right != null) {
                nodeStack.push(node.right);
                sumStack.push(tmp - node.right.val);
            }
        }
        return false;
    }

    // 记录路径
    public boolean hasPathSum03(TreeNode root, int sum) {
        Stack<TreeNode> nodeStack = new Stack<>();
        return traversal(root, sum, nodeStack);
    }

    public boolean traversal(TreeNode root, int sum, Stack<TreeNode> nodeStack) {
        boolean flag = false;
        if (root != null) {
            nodeStack.push(root);
            if (root.left == null && root.right == null) {
                for (int i = 0; i < nodeStack.size(); i++) {
                    sum -= nodeStack.get(i).val;
                }
                if (sum == 0) {
                    return true;
                }
            }
            if (root.left != null) {
                flag = traversal(root.left, sum, nodeStack);
            }
            if (flag) {
                return true;
            }
            if (root.right != null) {
                flag = traversal(root.right, sum, nodeStack);
            }
            nodeStack.pop();
        }
        return flag;
    }
}
