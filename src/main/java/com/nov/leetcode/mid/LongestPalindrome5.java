package com.nov.leetcode.mid;

import java.util.Scanner;

/**
 * @author longwenhe
 * @date 2020/4/21 21:43
 * @description
 */
public class LongestPalindrome5 {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int len = s.length();
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        char[] chars = s.toCharArray();
        for (int i = 1; i < len; i++) {
            int tmp = dp[i - 1][1];
            for (int j = i - tmp - 1; j <= i - tmp + 1; j++) {
                if (j >= 0 && isPalindrome(chars, j, i)) {
                    dp[i][0] = j;
                    dp[i][1] = i + 1 - j;
                    break;
                } else {
                    dp[i][0] = dp[i - 1][0];
                    dp[i][1] = dp[i - 1][1];
                }
            }
        }
        return new String(chars, dp[len - 1][0], dp[len - 1][1]);
    }

    private boolean isPalindrome(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start] == chars[end]) {
                start++;
                end--;
            } else {
                break;
            }
        }
        return start >= end;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] arrayA = new double[1001];
        double[] arrayB = new double[1001];
        int nonZeroA = input.nextInt();
        while (nonZeroA > 0) {
            int index = input.nextInt();
            double val = input.nextDouble();
            arrayA[index] = val;
            nonZeroA--;
        }
        int nonZeroB = input.nextInt();
        while (nonZeroB > 0) {
            int index = input.nextInt();
            double val = input.nextDouble();
            arrayB[index] = val;
            nonZeroB--;
        }
        int count = 0;
        for (int i = 0; i < 1001; i++) {
            arrayA[i] += arrayB[i];
            if (arrayA[i] != 0) {
                count++;
            }
        }
        System.out.print(count);
        for (int i = 1000; i >= 0; i--) {
            if (arrayA[i] != 0) {
                String str = String.format("%.1f", arrayA[i]);
                System.out.print(" " + i + " " + str);
            }
        }
    }
}
