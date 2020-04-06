package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/31 15:43
 * @description
 */
public class Search81 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < nums[start]) {
                if (nums[mid] < target && target <= nums[end - 1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            } else if (nums[mid] > nums[start]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else {
                // 相等的情况退化成O(n)复杂度
                start++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 1};
        int target = 3;
        Search81 solution = new Search81();
        System.out.println(solution.search(nums, target));
    }
}
