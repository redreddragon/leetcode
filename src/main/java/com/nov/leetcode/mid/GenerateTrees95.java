package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/7 10:15
 * @description
 */
public class GenerateTrees95 {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (n == 0) {
            return ans;
        }
        return getAns(1, n);
    }

    private List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        //此时没有数字，将 null 加入结果中
        if (start > end) {
            ans.add(null);
            return ans;
        }
        //只有一个数字，当前数字作为一棵树加入结果中
        if (start == end) {
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }
        //尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            List<TreeNode> leftTrees = getAns(start, i - 1);
            //得到所有可能的右子树
            List<TreeNode> rightTrees = getAns(i + 1, end);
            //左子树右子树两两组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    // DP数组
    public List<TreeNode> generateTrees02(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<List<TreeNode>> dp = new ArrayList<>();
        List<TreeNode> init = new ArrayList<>();
        init.add(null);
        dp.add(init);
        for (int i = 1; i <= n; i++) {
            List<TreeNode> treeNodes = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                List<TreeNode> leftTree = dp.get(j - 1);
                List<TreeNode> rightTree = dp.get(i - j);
                for (TreeNode leftNode : leftTree) {
                    for (TreeNode rightNode : rightTree) {
                        TreeNode node = new TreeNode(j);
                        node.left = leftNode;
                        node.right = clone(rightNode, j);
                        treeNodes.add(node);
                    }
                }
            }
            dp.add(treeNodes);
        }
        return dp.get(n);
    }

    private TreeNode clone(TreeNode node, int offset) {
        if (node == null) {
            return null;
        }
        TreeNode newNode = new TreeNode(node.val + offset);
        newNode.left = clone(node.left, offset);
        newNode.right = clone(node.right, offset);
        return newNode;
    }

    // DP单链表
    public List<TreeNode> generateTrees03(int n) {
        List<TreeNode> dp = new ArrayList<>();
        if (n == 0) {
            return dp;
        }
        dp.add(null);
        for (int i = 1; i <= n; i++) {
            List<TreeNode> pre = dp;
            dp = new ArrayList<>();
            for (TreeNode node : pre) {
                TreeNode cur = new TreeNode(i);
                cur.left = node;
                dp.add(cur);
                // 如果全部是右孩子构成的树，最长也不过是i-1，因为f(n)= n*f(n-1)
                for (int j = 0; j < i; j++) {
                    TreeNode copy = clone(node, 0);
                    TreeNode rightNode = copy;
                    // 每一次到最大右孩子深度，以示与上一次的区别
                    for (int k = 0; k < j; k++) {
                        if (rightNode == null) {
                            break;
                        }
                        rightNode = rightNode.right;
                    }
                    // 右孩子为0时不需要添加，证明此时已经出现重复
                    if (rightNode == null) {
                        break;
                    }
                    TreeNode newNode = new TreeNode(i);
                    // 交换子树
                    TreeNode next = rightNode.right;
                    rightNode.right = newNode;
                    newNode.left = next;
                    dp.add(copy);
                }

            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int n = 3;
        GenerateTrees95 solution = new GenerateTrees95();
        System.out.println(solution.generateTrees02(n));
    }
}
