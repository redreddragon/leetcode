package com.nov.leetcode.mid;

import com.nov.leetcode.common.ColorNode;
import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/6 22:12
 * @description
 */
public class PreorderTraversal144 {
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            result.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return result;
    }

    // 栈循环
    public List<Integer> preorderTraversal02(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        if (root == null) {
            return result;
        } else {
            nodeStack.push(root);
        }
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            result.add(node.val);
            if (node.right != null) {
                nodeStack.push(node.right);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
            }
        }
        return result;
    }

    // 颜色标记法
    public List<Integer> preorderTraversal03(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<ColorNode> colorStack = new Stack<>();
        if (root == null) {
            return result;
        } else {
            colorStack.push(new ColorNode(root, false));
        }
        while (!colorStack.isEmpty()) {
            ColorNode colorNode = colorStack.pop();
            if (colorNode.visited) {
                result.add(colorNode.node.val);
            } else {
                TreeNode left = colorNode.node.left;
                TreeNode right = colorNode.node.right;
                if (right != null) {
                    colorStack.push(new ColorNode(right, false));
                }
                if (left != null) {
                    colorStack.push(new ColorNode(left, false));
                }
                colorNode.visited = true;
                colorStack.push(colorNode);
            }
        }
        return result;
    }

    // Morris Traversal（不破坏树结构）
    public List<Integer> preorderTraversal04(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre;
        while (cur != null) {
            if (cur.left == null) {
                // 没有中序遍历意义上的前驱，则前序遍历可以直接进行
                result.add(cur.val);
                cur = cur.right;
            } else {
                // 有前驱，则需关联上中序遍历上的前驱，目的是为了回溯
                pre = cur.left;
                // pre代表当前节点的中序遍历意义直接前驱
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 构建前后继关系
                if (pre.right == null) {
                    pre.right = cur;
                    // 此时便收集结果，因为中序遍历意义上的前驱，恰好是前序遍历意义上的后继
                    result.add(cur.val);
                    // 转向左子树
                    cur = cur.left;
                }
                // 此时证明当前节点以及左子树已经完全遍历，需要回溯，一般是回溯到父节点，或者根节点
                // 并删除中序遍历意义上的前后继关系，恢复树形状
                if (pre.right == cur) {
                    pre.right = null;
                    // 左子树既然已经遍历完毕，则转向右子树
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}
