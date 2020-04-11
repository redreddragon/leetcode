package com.nov.leetcode.hard;

import com.nov.leetcode.common.TreeBFS;
import com.nov.leetcode.common.TreeNode;

/**
 * @author longwenhe
 * @date 2020/4/11 11:57
 * @description
 */
public class RecoverTree99 {
    public void recoverTree(TreeNode root) {
        TreeNode next = null;
        long preInt = Long.MIN_VALUE;
        TreeNode cur = root;
        TreeNode pre = root; // 初始化指向根节点
        // 用来标记pre是否已经找到，更具体的可以使用int flag = 0来标记
        // 此时 flag == 0 时表示未找到乱序元素
        // flag == 1时表示找到第一个乱序元素，此时应置pre == cur的前驱
        // flag == 2时表示找到第二个乱序元素，此时应置next == cur
        boolean preFlag = false;
        while (cur != null) {
            if (cur.left == null) {
                // do sth
                if (cur.val <= preInt) {
                    next = cur;
                    preFlag = true;
                }
                if (!preFlag) {
                    pre = cur;
                }
                preInt = cur.val;

                cur = cur.right;
            } else {
                TreeNode tmp = cur.left;
                while (tmp.right != null && tmp.right != cur) {
                    tmp = tmp.right;
                }

                if (tmp.right == null) {
                    tmp.right = cur;
                    cur = cur.left;
                }
                if (tmp.right == cur) {
                    // do sth
                    if (cur.val <= preInt) {
                        next = cur;
                        preFlag = true;
                    }
                    if (!preFlag) {
                        pre = cur;
                    }
                    preInt = cur.val;

                    cur = cur.right;
                    tmp.right = null;
                }
            }
        }
        int val = pre.val;
        pre.val = next.val;
        next.val = val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        node1.right = node2;
        RecoverTree99 solution = new RecoverTree99();
        TreeBFS.LevelTraversal(root);
        solution.recoverTree(root);
        TreeBFS.LevelTraversal(root);
    }
}
