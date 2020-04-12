package com.nov.leetcode.easy;

import com.nov.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/11 19:45
 * @description
 */
public class IsSymmetric101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetricTraversal(root.left, root.right);
    }

    private boolean symmetricTraversal(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null) {
            return false;
        }
        if (leftNode.val != rightNode.val) {
            return false;
        }
        return symmetricTraversal(leftNode.left, rightNode.right) && symmetricTraversal(leftNode.right, rightNode.left);
    }

    // 迭代写法
    public boolean isSymmetric02(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        if (root.left != null) {
            stack.push(root.left);
        }
        if (root.right != null) {
            stack.push(root.right);
        }
        if (stack.size() % 2 != 0) {
            return false;
        }

        while (!stack.isEmpty()) {
            TreeNode rightNode = stack.pop();
            TreeNode leftNode = stack.pop();
            if (leftNode.val != rightNode.val) {
                return false;
            }
            if (leftNode.left != null) {
                stack.push(leftNode.left);
            }
            if (rightNode.right != null) {
                stack.push(rightNode.right);
            }
            if (stack.size() % 2 != 0) {
                return false;
            }
            if (leftNode.right != null) {
                stack.push(leftNode.right);
            }
            if (rightNode.left != null) {
                stack.push(rightNode.left);
            }
            if (stack.size() % 2 != 0) {
                return false;
            }
        }

        return true;
    }

}
