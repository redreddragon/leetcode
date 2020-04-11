package com.nov.leetcode.easy;

import com.nov.leetcode.common.TreeNode;

/**
 * @author longwenhe
 * @date 2020/4/11 13:49
 * @description
 */
public class IsSameTree100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (!isSameNode(p, q)) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }

        if (!isSameTree(p.left, q.left)) {
            return false;
        }
        if (!isSameTree(p.right, q.right)) {
            return false;
        }
        return true;
    }

    private boolean isSameNode(TreeNode p, TreeNode q) {
        if ((p == null && q == null) || (p != null && q != null && p.val == q.val)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root1.left = node1;
        root1.right = node2;
        TreeNode root2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        root2.left = node3;
        root2.right = node4;
        IsSameTree100 solution = new IsSameTree100();
        System.out.println(solution.isSameTree(root1, root2));
    }
}
