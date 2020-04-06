package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/4/6 11:22
 * @description
 */
public class BuildTree106 {
    /*
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            Map<Integer, Integer> inorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }
            return getNode(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inorderMap);
        }

        private TreeNode getNode(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> inorderMap) {
            if (inStart > inEnd || postStart > postEnd) {
                return null;
            }
            int val = postorder[postEnd];
            TreeNode root = new TreeNode(val);
            int index = inorderMap.get(val);
            int leftSize = index - inStart;
            postEnd--;
            root.right = getNode(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd, inorderMap);
            root.left = getNode(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1, inorderMap);
            return root;
        }
        */
    Map<Integer, Integer> inorderMap = new HashMap<>();
    int postEnd;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 中序遍历hash表用于加速获取值对应的中序数组索引，该索引为根节点，从而确认子树划分的位置
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        postEnd = inorder.length - 1;
        return getNode(inorder, postorder, 0, postorder.length - 1);
    }

    private TreeNode getNode(int[] inorder, int[] postorder, int left, int right) {
        // 使用left和right变量来限定子树所在的区间（含中、后序遍历两个数组），用于退出循环而不是作为索引取值
        if (left > right) {
            return null;
        }
        int val = postorder[postEnd];
        TreeNode root = new TreeNode(val);
        int index = inorderMap.get(val);
        // postEnd作为global变量，每次递归必然减少1，且不可能出现负数的情形，因为postEnd == 0时所有的递归都已经满足边界退出条件
        postEnd--;
        root.right = getNode(inorder, postorder, index + 1, right);
        root.left = getNode(inorder, postorder, left, index - 1);
        return root;
    }
}
