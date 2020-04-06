package com.nov.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/6 12:44
 * @description
 */
public class GetRow119 {
    public List<Integer> getRow(int rowIndex) {
        int[] nums = new int[rowIndex + 1];
        Arrays.fill(nums, 1);
        for (int i = 2; i < rowIndex + 1; i++) {
            for (int j = i - 1; j > 0; j--) {
                nums[j] = nums[j] + nums[j - 1];
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            result.add(nums[i]);
        }
        return result;
    }

    public List<Integer> getRow02(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        int size = rowIndex + 1;
        for (int i = 0; i < size; i++) {
            result.add(1);
            for (int j = i - 1; j > 0; j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int rowIndex = 4;
        GetRow119 solution = new GetRow119();
        System.out.println(solution.getRow(rowIndex));
        System.out.println(solution.getRow02(rowIndex));
    }
}
