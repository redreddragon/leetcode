package com.nov.leetcode.mid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/3/31 10:07
 * @description
 */
public class Subsets78 {

    // 利用bitMap，复杂度O(n*2^n)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int count = 1 << nums.length;
        for (int i = 0; i < count; i++) {
            List<Integer> tmpResult = new ArrayList<>();
            int hasNumber = 0;
            int j = i;
            while (j != 0) {
                if ((j & 1) == 1) {
                    tmpResult.add(nums[hasNumber]);
                }
                j = j >> 1;
                hasNumber++;
            }
            result.add(tmpResult);
        }
        return result;
    }

    // 利用递推公式f(n) = f(n-1) + f(n-1)，复杂度O(2^n)
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> initialSet = new ArrayList<>();
        result.add(initialSet);
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> tmpList = new ArrayList<>(result.get(j));
                tmpList.add(nums[i]);
                result.add(tmpList);
            }
        }
        return result;
    }

    // 回溯算法，复杂度O(2^n)，递归深度O(n)
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]); // 回溯的精髓步骤，先增加，再删除
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    private int containBits(int i) {
        int bitCount = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                bitCount++;
            }
            i = i >> 1;
        }
        return bitCount;
    }

    // 深搜+标记位，最后统一收集，注意与回溯算法的比较
    class Solution {

        List<List<Integer>> al = new ArrayList<>();
        boolean[] vis;

        public List<List<Integer>> subsets(int[] nums) {
            if (nums.length == 0) {
                return al;
            }
            vis = new boolean[nums.length];
            dfs(nums, 0);
            return al;

        }

        public void dfs(int[] a, int start) {
            if (start == a.length) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < a.length; i++) {
                    if (!vis[i]) {
                        list.add(a[i]);
                    }
                }
                al.add(list);
                return;
            }
            vis[start] = true;
            dfs(a, start + 1);
            vis[start] = false;
            dfs(a, start + 1);
        }

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Subsets78 solution = new Subsets78();
        List<List<Integer>> result = solution.subsets2(nums);
        System.out.println(result);
        boolean[][] visited = new boolean[2][2];
        for(int i = 0; i < visited.length; i++){
            for(int j = 0; j < visited[i].length; j++){
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }

    }

}
