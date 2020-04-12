package com.nov.leetcode.mid;

import com.nov.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/12 18:52
 * @description
 */
public class PathSum113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<Integer> sumStack = new Stack<>();
        sumStack.push(sum);
        dfs(root, sumStack, new Stack<Integer>(), result);
        return result;
    }


    public void dfs(TreeNode root, Stack<Integer> sumStack, Stack<Integer> nodeStack, List<List<Integer>> result) {
        if (root != null) {
            int tmp = sumStack.peek() - root.val;
            if (root.left == null && root.right == null && tmp == 0) {
                List<Integer> tmpResult = new ArrayList<>(nodeStack);
                tmpResult.add(root.val);
                result.add(tmpResult);
            }
            nodeStack.push(root.val);
            sumStack.push(tmp);
            dfs(root.left, sumStack, nodeStack, result);
            dfs(root.right, sumStack, nodeStack, result);
            sumStack.pop();
            nodeStack.pop();
        }
    }
}
