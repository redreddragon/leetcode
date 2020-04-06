package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/4/4 22:53
 * @description
 */
public class CountLargestGroup {
    public int countLargestGroup(int n) {
        if (n < 1) {
            return 0;
        }
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[count(i)]++;
        }
        int num = 1;
        int val = result[1];
        for (int j = 2; j < n + 1; j++) {
            if (result[j] == val) {
                num++;
            } else if (result[j] > val) {
                num = 1;
                val = result[j];
            }
        }
        return num;

    }

    private int count(int i) {
        int count = 0;
        while (i / 10 != 0) {
            count += i % 10;
            i /= 10;
        }
        count += i;
        return count;
    }

    public static void main(String[] args) {
        int n = 2;
        CountLargestGroup solution  = new CountLargestGroup();
        System.out.println(solution.countLargestGroup(n));
    }
}
