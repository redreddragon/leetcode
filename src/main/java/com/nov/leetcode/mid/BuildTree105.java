package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author longwenhe
 * @date 2020/4/5 23:06
 * @description
 */
public class BuildTree105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode node = getNode(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return node;
    }

    private TreeNode getNode(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = getIndex(inorder, preorder[preStart], inStart, inEnd);
        preStart++;
        // 使用元素个数来划分左右子树，而不是索引下标
        int leftSize = index - inStart;
        root.left = getNode(preorder, preStart, preStart + leftSize - 1, inorder, inStart, index - 1);
        root.right = getNode(preorder, preStart + leftSize, preEnd, inorder, index + 1, inEnd);
        return root;
    }

    private int getIndex(int[] inorder, int target, int inStart, int inEnd) {
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] preorder = {1, 2, 3};
        int[] inorder = {3, 2, 1};
        BuildTree105 solution = new BuildTree105();
        TreeNode root = solution.buildTree(preorder, inorder);
        Queue<TreeNode> treeNodeDeque = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        if (root == null) {
            result.add("null");
        } else {
            treeNodeDeque.add(root);
        }
        while (!treeNodeDeque.isEmpty()) {
            TreeNode node = treeNodeDeque.remove();
            result.add(node.val + "");
            if (node.left == null) {
//                result.add("left");
            } else {
                treeNodeDeque.add(node.left);
            }
            if (node.right == null) {
//                result.add("right");
            } else {
                treeNodeDeque.add(node.right);
            }
        }
        System.out.println(result);
    }

}
