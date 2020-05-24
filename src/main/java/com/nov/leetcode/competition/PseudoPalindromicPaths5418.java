package com.nov.leetcode.competition;

import com.nov.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * @author : longwenhe
 * @date : 2020/5/24 11:20
 * @description :
 */
public class PseudoPalindromicPaths5418 {
    int count = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, new Stack<>());
        return count;
    }

    private void dfs(TreeNode node, Stack<Integer> nums) {
        if (node == null) {
            return;
        }
        nums.push(node.val);
        if (isLeaf(node)) {
            if (isPalindromic(nums)) {
                count++;
            }
        }
        dfs(node.left, nums);
        dfs(node.right, nums);
        nums.pop();
    }

    private boolean isLeaf(TreeNode node) {
        if (node.right == null && node.left == null) {
            return true;
        }
        return false;
    }

    private boolean isPalindromic(Stack<Integer> nums) {
        int[] result = new int[10];
        for (Integer num : nums) {
            result[num]++;
        }
        int oddNum = 0;
        for (Integer numCount : result) {
            if (numCount % 2 == 1) {
                oddNum++;
            }
            if (oddNum > 1) {
                return false;
            }
        }
        return true;
    }
}
