package com.nov.leetcode.competition;

import java.util.HashMap;

/**
 * @author : longwenhe
 * @date : 2020/6/28 11:13
 * @description :
 */
public class CanArrange5449 {
    public boolean canArrange(int[] arr, int k) {
        int len = arr.length;
        int[] visited = new int[k];
        for (int num : arr) {
            int index = (num % k + k) % k;
            visited[index]++;
        }
        for (int i = 1; i < k; i++) {
            if (i > k - i) {
                break;
            }
            if (visited[i] != visited[k - i]) {
                return false;
            }
        }
        if (visited[0] % 2 == 1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CanArrange5449 solution = new CanArrange5449();
        int[] arr = {1, -1};
        int k = 7;
        System.out.println(solution.canArrange(arr, k));
    }
}
