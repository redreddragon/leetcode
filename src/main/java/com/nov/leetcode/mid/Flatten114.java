package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

/**
 * @author longwenhe
 * @date 2020/4/12 19:08
 * @description
 */
public class Flatten114 {
    // 前序遍历意义的Morris遍历
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
                cur = cur.right;
            }
        }
    }
}
