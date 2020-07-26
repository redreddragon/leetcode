package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/7/25 22:44
 * @description
 */
public class NumOfSubarrays5457 {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        long count = 0;
        // 用高电平表示在该数字之前的子数组和为基数，低电平表示子数组和为偶数
        long highLevel = 0;
        long lowLevel = 0;
        // 每当遇到奇数时，电平才会发生翻转，而偶数保持不变
        boolean[] reverse = new boolean[arr.length];
        boolean sumFlag = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                reverse[i] = true;
                sumFlag = sumFlag == true ? false : true;
            }
            if (sumFlag) {
                highLevel++;
            } else {
                lowLevel++;
            }
        }
        // 从前往后，开始去掉数字，去掉偶数时，后续的波形不变，去掉奇数时，后续的波形完全翻转，因此用swap来实现
        // 统计时只统计高电平，即为和奇数的子数组总个数
        for (int i = 0; i < reverse.length; i++) {
            count += highLevel;
            if (reverse[i]) {
                // 翻转前，高电平个数减一，表示起始位置往前走1，然后再交换高低电平个数，表示电平翻转
                highLevel--;
                long tmp = highLevel;
                highLevel = lowLevel;
                lowLevel = tmp;
            } else {
                lowLevel--;
            }
        }
        count %= mod;
        return (int) count;
    }

    public static void main(String[] args) {
        NumOfSubarrays5457 solution = new NumOfSubarrays5457();
        int[] arr = {97, 10, 8, 74, 51, 1, 14, 84, 2, 63, 33, 29, 28};
        System.out.println(solution.numOfSubarrays(arr));
    }
}
