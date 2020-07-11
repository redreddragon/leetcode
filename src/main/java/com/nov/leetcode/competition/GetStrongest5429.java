package com.nov.leetcode.competition;

import java.util.Arrays;

/**
 * @author longwenhe
 * @date 2020/6/7 10:40
 * @description
 */
public class GetStrongest5429 {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int midIndex = (arr.length - 1) >> 1;
        int mid = arr[midIndex];
        int i = 0, j = arr.length - 1;
        int[] result = new int[k];
        while (i <= j && k > 0) {
            if ((Math.abs(arr[j] - mid) > Math.abs(arr[i] - mid)) || (Math.abs(arr[j] - mid) == Math.abs(arr[i] - mid) && arr[j] > arr[i])) {
                result[k - 1] = arr[j];
                j--;
            } else {
                result[k - 1] = arr[i];
                i++;
            }
            k--;
        }
        return result;
    }
}
