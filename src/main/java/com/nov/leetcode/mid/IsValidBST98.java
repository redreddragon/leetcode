package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/11 10:37
 * @description
 */
public class IsValidBST98 {
    public boolean isValidBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) < result.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        }
    }

    // 中序遍历优化，无需全遍历然后判断是否有序
    public boolean isValidBST02(TreeNode root) {
        Stack<Integer> result = new Stack<>();
        return inorderTraversal(root, result);
    }

    private boolean inorderTraversal(TreeNode root, Stack<Integer> result) {
        if (root != null) {
            if (!inorderTraversal(root.left, result)) {
                return false;
            }
            if (result.size() > 0 && result.peek() >= root.val) {
                return false;
            }
            result.add(root.val);
            if (!inorderTraversal(root.right, result)) {
                return false;
            }
        }
        return true;
    }

    // 使用栈结构替代递归进一步优化
    public boolean isValidBST03(TreeNode root) {
        Stack<TreeNode> result = new Stack<>();
        long preVal = Long.MIN_VALUE;
        while (!result.isEmpty() || root != null) {
            while (root != null) {
                result.push(root);
                root = root.left;
            }
            root = result.pop();
            if (root.val <= preVal) {
                return false;
            }
            preVal = root.val;
            root = root.right;
        }
        return true;
    }
}
