package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/30 16:10
 * @description
 */
public class SearchMatrix74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int start = 0, end = matrix.length;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (matrix[mid][0] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (start == 0) {
            return false;
        }
        int first = 0, last = matrix[start - 1].length;
        while (first < last) {
            int mid = (first + last) >> 1;
            if (matrix[start - 1][mid] <= target) {
                first = mid + 1;
            } else {
                last = mid;
            }
        }
        if (matrix[start - 1][first - 1] != target) {
            return false;
        } else {
            return true;
        }
    }

    private int binarySearch(int[] nums, int target) {
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] == target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start - 1;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
/*        int[][] matrix = {
                {}
        };*/
        int target = 34;
        SearchMatrix74 solution = new SearchMatrix74();
        System.out.println(solution.searchMatrix(matrix, target));
    }
}
