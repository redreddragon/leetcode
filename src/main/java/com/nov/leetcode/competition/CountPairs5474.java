package com.nov.leetcode.competition;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/7/26 11:15
 * @description
 */
public class CountPairs5474 {
    public int countPairs(TreeNode root, int distance) {
        Node newRoot = getNode(root, null);
        List<Node> leafNodes = getLeafNode(newRoot, new ArrayList<>());
        int count = 0;
        for (int i = 0; i < leafNodes.size(); i++) {
            Node node1 = leafNodes.get(i);
            for (int j = i + 1; j < leafNodes.size(); j++) {
                Node node2 = leafNodes.get(j);
                if (isGoodPair(node1, node2, distance)) {
                    count++;
                }
            }
        }
        return count;
    }

    class Node {
        int val;
        Node left;
        Node right;
        Node parentNode;
        int height;

        public Node(int val, Node left, Node right, Node parentNode, int height) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parentNode = parentNode;
            this.height = height;
        }
    }

    public Node getNode(TreeNode root, Node parentNode) {
        if (root == null) {
            return null;
        }
        int tmpHeight;
        if (parentNode == null) {
            tmpHeight = 0;
        } else {
            tmpHeight = parentNode.height + 1;
        }
        Node newRoot = new Node(root.val, null, null, parentNode, tmpHeight);
        newRoot.left = getNode(root.left, newRoot);
        newRoot.right = getNode(root.right, newRoot);
        return newRoot;
    }

    public List<Node> getLeafNode(Node root, List<Node> result) {
        if (root.left == null && root.right == null) {
            result.add(root);
            return result;
        }
        if (root.left != null) {
            getLeafNode(root.left, result);
        }
        if (root.right != null) {
            getLeafNode(root.right, result);
        }
        return result;
    }

    public boolean isGoodPair(Node node1, Node node2, int distance) {
        Node parentNode = getComParent(node1, node2);
        int res = node1.height - parentNode.height + node2.height - parentNode.height;
        if (res <= distance) {
            return true;
        } else {
            return false;
        }
    }

    public Node getComParent(Node node1, Node node2) {
        Node tmp1 = new Node(node1.val, node1.left, node1.right, node1.parentNode, node1.height);
        Node tmp2 = new Node(node2.val, node2.left, node2.right, node2.parentNode, node2.height);
        if (tmp1.height > tmp2.height) {
            int level = tmp1.height - tmp2.height;
            while (level > 0) {
                tmp1 = tmp1.parentNode;
                level--;
            }
        } else {
            int level = tmp2.height - tmp1.height;
            while (level > 0) {
                tmp2 = tmp2.parentNode;
                level--;
            }
        }
        while (tmp1 != tmp2) {
            tmp1 = tmp1.parentNode;
            tmp2 = tmp2.parentNode;
        }
        return tmp1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        CountPairs5474 solution = new CountPairs5474();
        System.out.println(solution.countPairs(root, 3));
    }
}
