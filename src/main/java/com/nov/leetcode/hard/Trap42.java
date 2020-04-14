package com.nov.leetcode.hard;

/**
 * @author longwenhe
 * @date 2020/4/14 22:11
 * @description
 */
/*
42. 接雨水
        给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
*/
public class Trap42 {
    public int trap(int[] height) {
        int sum = 0;
        int begin = 0;
        int end = height.length - 1;
        while (begin < end && height[begin] <= height[begin + 1]) {
            begin++;
        }
        while (end > begin && height[end] <= height[end - 1]) {
            end--;
        }
        int maxIndex = begin;
        for (int i = begin; i <= end; i++) {
            if (height[i] > height[maxIndex]) {
                maxIndex = i;
            }
        }
        while (begin < maxIndex) {
            int j = begin + 1;
            for (; j < maxIndex; j++) {
                if (height[j] >= height[begin]) {
                    break;
                } else {
                    sum += height[begin] - height[j];
                }
            }
            begin = j;
        }

        while (end > maxIndex) {
            int k = end - 1;
            for (; k > maxIndex; k--) {
                if (height[k] >= height[end]) {
                    break;
                } else {
                    sum += height[end] - height[k];
                }
            }
            end = k;
        }
        return sum;
    }
}
