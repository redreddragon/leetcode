package com.nov.leetcode.hard;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/6 23:33
 * @description
 */
public class PostorderTraversal145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            if (!nodeStack.isEmpty() && node == nodeStack.peek()) {
                if (node.right != null) {
                    nodeStack.push(node.right);
                    nodeStack.push(node.right);
                }
                if (node.left != null) {
                    nodeStack.push(node.left);
                    nodeStack.push(node.left);
                }
            } else {
                result.add(node.val);
            }

        }
        return result;
    }
    // Morris Traversal，倒转线性表

}
