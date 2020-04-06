package com.nov.leetcode.mid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/4 21:36
 * @description
 */
public class SubsetsWithDup90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        backTrack(nums, 0, new Stack<>(), result);
        return result;
    }

    private void backTrack(int[] nums, int start, Stack<Integer> tmpResult, List<List<Integer>> result) {
        result.add(new ArrayList<>(tmpResult));
        for (int i = start; i < nums.length; i++) {
            tmpResult.push(nums[i]);
            int j = i + 1;
            while (j < nums.length && nums[j] == nums[j - 1]){
                tmpResult.push(nums[j]);
                j++;
            }
            backTrack(nums, j, tmpResult, result);
            while (j > i){
                tmpResult.pop();
                j--;
            }
        }
    }
    // 第二种剪枝方法
    private void backTrack02(int[] nums, int start, Stack<Integer> tmpResult, List<List<Integer>> result) {
        result.add(new ArrayList<>(tmpResult));
        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]){ // 精髓在于 i > start，保证不重不漏
                continue;
            }
            tmpResult.push(nums[i]);
            backTrack(nums, i + 1, tmpResult, result);
            tmpResult.pop();
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        SubsetsWithDup90 solution = new SubsetsWithDup90();
        List<List<Integer>> result = solution.subsetsWithDup(nums);
        System.out.println(result);
    }


}
