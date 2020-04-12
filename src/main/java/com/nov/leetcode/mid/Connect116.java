package com.nov.leetcode.mid;

import com.nov.leetcode.common.Node;

/**
 * @author longwenhe
 * @date 2020/4/12 22:15
 * @description
 */
public class Connect116 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        connect(root.left, root.right);
        return root;
    }

    // 递归
    private void connect(Node leftNode, Node rightNode) {
        if (leftNode == null || rightNode == null) {
            return;
        }
        leftNode.next = rightNode;
        Node tmpRight = leftNode.right;
        Node tmpLeft = rightNode.left;
        while (tmpRight != null && tmpLeft != null) {
            tmpRight.next = tmpLeft;
            tmpRight = tmpRight.right;
            tmpLeft = tmpLeft.left;
        }
        connect(leftNode.left, leftNode.right);
        connect(rightNode.left, rightNode.right);
    }

    // 递归简化
    private void helper(Node root) {
        if (root == null || root.left == null || root.right == null) {
            return;
        }
        root.left.next = root.right;
        Node tmpRight = root.left.right;
        Node tmpLeft = root.right.left;
        while (tmpRight != null && tmpLeft != null) {
            tmpRight.next = tmpLeft;
            tmpRight = tmpRight.right;
            tmpLeft = tmpLeft.left;
        }
        helper(root.left);
        helper(root.right);
    }

    // 迭代
    public Node connect02(Node root) {
        if (root == null) {
            return null;
        }
        Node leftMost = root;
        while (leftMost.left != null) {
            Node head = leftMost;
            while (head != null) { // 必须由父亲寻找孩子之间的关联，而不是直接由孩子来连接
                if (head.next != null) {
                    head.right.next = head.next.left; // 跨接
                }
                head.left.next = head.right; // 内连接
                head = head.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }
}
