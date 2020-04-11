package com.nov.leetcode.common;

/**
 * @author longwenhe
 * @date 2020/4/6 22:21
 * @description
 */
public class ColorNode {
    public TreeNode node;
    public boolean visited;

    public ColorNode(TreeNode node, boolean visited) {
        this.node = node;
        this.visited = visited;
    }
}
