package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/15 20:33
 * @description
 */
/*
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

        调用 next() 将返回二叉搜索树中的下一个最小的数。

        提示：

        next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
        你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
*/
class BSTIterator {

    private List<Integer> minNode;
    private int index;
    private TreeNode root;

    public BSTIterator(TreeNode root) {
        this.root = root;
        this.minNode = new ArrayList<>();
        this.index = 0;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                Integer num = cur.val;
                cur = cur.right;
                minNode.add(num);
            } else {
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }

                if (pre.right == cur) {
                    Integer num = cur.val;
                    pre.right = null;
                    cur = cur.right;
                    minNode.add(num);
                }
            }
        }


    }

    /**
     * @return the next smallest number
     */
    public int next() {
        index++;
        return minNode.get(index - 1);

    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return index < minNode.size();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(20);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        BSTIterator solution = new BSTIterator(root);
        System.out.println(solution.next());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());

    }
}

