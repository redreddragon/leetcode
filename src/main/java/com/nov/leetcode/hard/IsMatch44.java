package com.nov.leetcode.hard;

/**
 * @author longwenhe
 * @date 2020/4/26 23:25
 * @description
 */
public class IsMatch44 {
    // 动态规划，其实相比10题还要容易些
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < p.length() + 1; i++) {
            dp[0][i] = dp[0][i - 1] && (p.charAt(i - 1) == '*');
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    // 贪心算法
    public boolean isMatch02(String s, String p) {
        int n = s.length();
        int m = p.length();
        int iStar = -1;
        int jStar = -1;
        int i = 0;
        int j = 0;
        while (i < n) {
            if (j < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < m && p.charAt(j) == '*') {
                iStar = i;
                j++;
                jStar = j;
            } else if (iStar >= 0) {
                iStar++;
                i = iStar;
                j = jStar;
            } else {
                return false;
            }
        }
        while (j < m && p.charAt(j) == '*') {
            j++;
        }
        return j == m;
    }

    public static void main(String[] args) {
        IsMatch44 solution = new IsMatch44();
        String s = "aa";
        String p = "*";
        System.out.println(solution.isMatch02(s, p));
        ;
    }
}
