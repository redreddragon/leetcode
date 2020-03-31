package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/30 20:00
 * @description
 */
public class SortColors75 {
    // 桶排序或者说计数排序，两次扫描
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                red++;
            } else if (nums[i] == 1) {
                white++;
            } else {
                blue++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (red != 0) {
                red--;
                nums[i] = 0;
            } else if (white != 0) {
                white--;
                nums[i] = 1;
            } else {
                blue--;
                nums[i] = 2;
            }
        }
    }

    // 快排思想，一次扫描即可
    public void sortColors2(int[] nums) {
        int first = 0, last = nums.length - 1;
        for (int i = 0; i < nums.length && i <= last; ) {
            if (nums[i] == 0) {
                swap(nums, i, first);
                first++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, last);
                last--;
            } else {
                i++;
            }
        }
    }

    // 冒泡排序
    public void sortColors3(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 0, 1, 1, 0};
        SortColors75 solution = new SortColors75();
        solution.sortColors3(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
