package com.nov.leetcode.hard;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author longwenhe
 * @date 2020/4/12 0:52
 * @description
 */
public class ZigzagLevelOrder103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        if (root == null) {
            return result;
        } else {
            nodeQueue.add(root);
        }
        int level = 0;
        while (!nodeQueue.isEmpty()) {
            int len = nodeQueue.size();
            LinkedList<Integer> tmpResult = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = nodeQueue.remove();
                if (level % 2 == 0) {
                    tmpResult.addLast(node.val);
                } else {
                    tmpResult.addFirst(node.val);
                }
                if (node.left != null) {
                    nodeQueue.add(node.left);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
            }
            result.add(tmpResult);
            level++;
        }
        return result;
    }
}
