package com.nov.leetcode.competition;

import java.util.Arrays;

/**
 * @author longwenhe
 * @date 2020/7/11 23:50
 * @description
 */
public class RangeSum5445 {
    public int rangeSum(int[] nums, int n, int left, int right) {
        int mod = 1000000007;
        int len = n * (n + 1) >> 1;
        int[] result = new int[len + 1];
        int k = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    result[k] = nums[i];
                } else {
                    result[k] = result[k - 1] + nums[j];
                    result[k] %= mod;
                }
                k++;
            }
        }
        Arrays.sort(result);
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += result[i];
            sum %= mod;
        }
        return sum;
    }
}
