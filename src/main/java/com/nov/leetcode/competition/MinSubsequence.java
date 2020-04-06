package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/5 10:37
 * @description
 */
public class MinSubsequence {
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int right = 0;
        int left = sum;
        for (int i = nums.length - 1; i >= 0; i--) {
            right += nums[i];
            left -= nums[i];
            result.add(nums[i]);
            if (right > left) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 10, 9, 8};
        MinSubsequence solution = new MinSubsequence();
        System.out.println(solution.minSubsequence(nums));
    }
}
