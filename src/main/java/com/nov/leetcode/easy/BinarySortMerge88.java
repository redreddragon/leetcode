package com.nov.leetcode.easy;

/**
 * @author longwenhe
 * @date 2020/3/25 0:51
 * @description
 */
public class BinarySortMerge88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }

        int[] tmp = new int[m];
        for (int i = 0; i < m; i++) {
            tmp[i] = nums1[i];
        }
        for (int j = 0, k = 0; j < m || k < n; ) {
            if (k >= n || j < m && tmp[j] < nums2[k]) {
                nums1[j + k] = tmp[j];
                j++;
            } else {
                nums1[j + k] = nums2[k];
                k++;
            }
        }

    }

    public static void main(String[] args) {
        BinarySortMerge88 solution = new BinarySortMerge88();
        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;
        solution.merge(nums1, m, nums2, n);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }

    }
}
