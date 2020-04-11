package com.nov.leetcode.common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author longwenhe
 * @date 2020/4/11 13:26
 * @description
 */
public class TreeBFS {
    public static void LevelTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> treeNodeQueue = new ArrayDeque<>();
        if (root == null) {
            return;
        } else {
            treeNodeQueue.add(root);
        }
        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.remove();
            if (node.left != null) {
                treeNodeQueue.add(node.left);
            }
            if (node.right != null) {
                treeNodeQueue.add(node.right);
            }
            result.add(node.val);
        }
        System.out.println(result);
    }
}
