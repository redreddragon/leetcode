package com.nov.leetcode.hard;

import com.nov.leetcode.common.TreeNode;

/**
 * @author longwenhe
 * @date 2020/4/14 0:45
 * @description
 */
/*

给定一个非空二叉树，返回其最大路径和。

        本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
*/
public class MaxPathSum124 {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        getMax(root);
        return max;
    }

    public int getMax(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(getMax(node.left), 0);
        int right = Math.max(getMax(node.right), 0);

        max = Math.max(max, left + right + node.val);

        return node.val + Math.max(left, right);
    }

}
