package com.nov.leetcode.mid;

import com.nov.leetcode.common.ColorNode;
import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/6 13:40
 * @description
 */
public class InorderTraversal94 {
    List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            result.add(root.val);
            inorderTraversal(root.right);
        }
        return result;
    }

    // 栈方法
    public List<Integer> inorderTraversal02(TreeNode root) {
        List<Integer> result02 = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        getLeftSide(root, nodeStack);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            result02.add(node.val);
            getLeftSide(node.right, nodeStack);
        }
        return result02;
    }

    private void getLeftSide(TreeNode node, Stack<TreeNode> nodeStack) {
        while (node != null) {
            nodeStack.push(node);
            node = node.left;
        }
    }

    // 颜色标记法
    public List<Integer> inorderTraversal03(TreeNode root) {
        List<Integer> result03 = new ArrayList<>();
        Stack<ColorNode> nodeStack = new Stack<>();
        if (root == null) {
            return result03;
        } else {
            nodeStack.push(new ColorNode(root, false));
        }
        while (!nodeStack.isEmpty()) {
            ColorNode colorNode = nodeStack.pop();
            if (colorNode.visited) {
                result03.add(colorNode.node.val);
            } else {
                TreeNode leftChild = colorNode.node.left;
                TreeNode rightChild = colorNode.node.right;
                if (rightChild != null) {
                    nodeStack.push(new ColorNode(rightChild, false));
                }

                colorNode.visited = true;
                nodeStack.push(colorNode);
                if (leftChild != null) {
                    nodeStack.push(new ColorNode(leftChild, false));
                }
            }
        }
        return result03;
    }

    // 莫里斯方法（破坏树结构），使用前驱和后继的原理
    public List<Integer> inorderTraversal04(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre;
        while (cur != null) {
            if (cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur;
                TreeNode tmp = cur;
                cur = cur.left;
                tmp.left = null;
            }
        }
        return result;
    }

    // 莫里斯方法（不破坏树结构），使用前驱和后继的原理
    public List<Integer> inorderTraversal05(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre;
        while (cur != null) {
            if (cur.left == null) {
                // 没有前驱，则中序遍历可以直接进行
                result.add(cur.val);
                cur = cur.right;
            } else {
                // 有前驱，则判定直接前驱是否已经关联上
                pre = cur.left;
                // pre代表当前节点的直接前驱
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 构建前后继关系
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                }
                // 前驱已经关联上，证明此时当前节点的所有前驱都已经遍历，可以遍历当前节点
                // 并删除前后继关系，恢复树形状
                if (pre.right == cur) {
                    pre.right = null;
                    result.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}
