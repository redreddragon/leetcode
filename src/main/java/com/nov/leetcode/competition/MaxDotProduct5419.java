package com.nov.leetcode.competition;

/**
 * @author : longwenhe
 * @date : 2020/5/24 11:43
 * @description :
 */
public class MaxDotProduct5419 {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = nums1[0] * nums2[0];
        for (int i = 1; i < len1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], nums1[i] * nums2[0]);
        }
        for (int i = 1; i < len2; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], nums1[0] * nums2[i]);
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                // 以i、j-1结尾或者以i-1、j结尾，特别的，这种情况还包含了以i-1、j-1结尾
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                // 以i、j结尾，特别的，如果以i-1、j-1结尾为负数，此时可以只以i、j两个元素组成点积
                dp[i][j] = Math.max(nums1[i] * nums2[j] + (dp[i - 1][j - 1] > 0 ? dp[i - 1][j - 1] : 0), dp[i][j]);
            }
        }
        return dp[len1 - 1][len2 - 1];
    }

    public static void main(String[] args) {
//        int[] nums1 = {-3, -8, 3, -10, 1, 3, 9};
//        int[] nums1 = {-3, -8, 3, -10};
        int[] nums1 = {-1, -1};
//        int[] nums2 = {9, 2, 3, 7, -9, 1, -8, 5, -1, -1};
//        int[] nums2 = {9, -9, 1, -8};
        int[] nums2 = {1, 1};
        MaxDotProduct5419 solution = new MaxDotProduct5419();
        int result = solution.maxDotProduct(nums1, nums2);

        System.out.println(result);
    }
}
