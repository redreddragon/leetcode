package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/31 14:34
 * @description
 */
public class RemoveDuplicates80 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int count = 1;
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[j] && count < 2) {
                j++;
                nums[j] = nums[i];
                count++;
            } else if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
                count = 1;
            }
        }
        return j + 1;
    }

    // 只剩唯一一个去重
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                if (i != j) {
                    // 不需要交换
                    swap(nums, i, j);
                }
            }
        }
        return j + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 与前两个位置的元素比较大小，省去了允许相同元素个数的计数
    public int removeDuplicates3(int[] nums) {
        int limit = 2;
        int i = 0;
        for (int n : nums)
            if (i < limit || n > nums[i - limit])
                nums[i++] = n;
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4};
        RemoveDuplicates80 solution = new RemoveDuplicates80();
        int len = solution.removeDuplicates3(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
