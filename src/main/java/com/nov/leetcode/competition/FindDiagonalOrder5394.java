package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : longwenhe
 * @date : 2020/4/26 11:18
 * @description :
 */

/*    给你一个列表 nums ，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回 nums 中对角线上的整数。
            输入：nums = [[1,2,3],[4,5,6],[7,8,9]]
            输出：[1,4,2,7,5,3,8,6,9]
            输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
            输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
        */
public class FindDiagonalOrder5394 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0 || nums.get(0).size() == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(nums.get(0).get(0), 0, 0);
        queue.add(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.remove();
            int row = tmp.row;
            int col = tmp.col;
            if (nums.get(row).get(col) == Integer.MIN_VALUE) {
                continue;
            }
            result.add(tmp.val);
            nums.get(row).set(col, Integer.MIN_VALUE);
            if (row + 1 < nums.size() && col < nums.get(row + 1).size()) {
                Node left = new Node(nums.get(row + 1).get(col), row + 1, col);
                queue.add(left);
            }
            if (col + 1 < nums.get(row).size()) {
                Node right = new Node(nums.get(row).get(col + 1), row, col + 1);
                queue.add(right);
            }
        }
        int[] ans = new int[result.size()];
        int i = 0;
        for (Integer num : result) {
            ans[i] = num;
            i++;
        }
        return ans;
    }

    public int[] findDiagonalOrder02(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0 || nums.get(0).size() == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(nums.get(0).get(0), 0, 0);
        queue.add(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.remove();
            int row = tmp.row;
            int col = tmp.col;
            result.add(tmp.val);
            if (row + 1 < nums.size() && col < nums.get(row + 1).size()) {
                Node left = new Node(nums.get(row + 1).get(col), row + 1, col);
                queue.add(left);
            }
            if (col + 1 < nums.get(row).size() && (row == 0 || col + 1 >= nums.get(row - 1).size())) {
                Node right = new Node(nums.get(row).get(col + 1), row, col + 1);
                queue.add(right);
            }
        }
        int[] ans = new int[result.size()];
        int i = 0;
        for (Integer num : result) {
            ans[i] = num;
            i++;
        }
        return ans;
    }
    class Node {
        int val;
        int row;
        int col;

        public Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        int[][] tmp = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < tmp.length; i++) {
            List<Integer> tmpResult = new ArrayList<>();
            for (int j = 0; j < tmp[i].length; j++) {
                tmpResult.add(tmp[i][j]);
            }
            nums.add(tmpResult);
        }
        FindDiagonalOrder5394 solution = new FindDiagonalOrder5394();
        int[] result = solution.findDiagonalOrder02(nums);
        for (Integer num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
