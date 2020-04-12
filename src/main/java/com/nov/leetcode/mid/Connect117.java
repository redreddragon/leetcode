package com.nov.leetcode.mid;

import com.nov.leetcode.common.Node;

/**
 * @author longwenhe
 * @date 2020/4/12 23:27
 * @description
 */
public class Connect117 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node leftMost = root;
        while (leftMost != null) {
            Node head = leftMost;
            leftMost = null;
            while (head != null) {
                Node preHead = head;
                while (preHead != null && !hasChild(preHead)) {
                    preHead = preHead.next;
                }
                if (preHead == null) {
                    break;
                }
                if (leftMost == null) {
                    leftMost = preHead.left != null ? preHead.left : preHead.right;
                }
                Node nextHead = preHead.next;
                while (nextHead != null && !hasChild(nextHead)) {
                    nextHead = nextHead.next;
                }
                connect(preHead, nextHead);
                head = nextHead;
            }
        }
        return root;
    }

    private boolean hasChild(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return false;
        } else {
            return true;
        }
    }

    private void connect(Node leftNode, Node rightNode) {
        if (rightNode == null) {
            if (leftNode.left != null) {
                leftNode.left.next = leftNode.right;
            }
        } else {
            if (leftNode.left == null) {
                leftNode.right.next = rightNode.left != null ? rightNode.left : rightNode.right;
            } else {
                if (leftNode.right == null) {
                    leftNode.left.next = rightNode.left != null ? rightNode.left : rightNode.right;
                } else {
                    leftNode.left.next = leftNode.right;
                    leftNode.right.next = rightNode.left != null ? rightNode.left : rightNode.right;
                }
            }
        }
    }

    // 哨兵节点的精妙使用
    public Node connect02(Node root) {
        Node dummy = new Node(0);
        Node cur = dummy;
        Node res = root;
        while (root != null) {
            if (root.left != null) {
                cur.next = root.left;
                cur = cur.next;
            }
            if (root.right != null) {
                cur.next = root.right;
                cur = cur.next;
            }
            root = root.next;
            if (root == null) {
                cur = dummy;
                root = dummy.next;
                dummy.next = null;
            }
        }
        return res;
    }

}
