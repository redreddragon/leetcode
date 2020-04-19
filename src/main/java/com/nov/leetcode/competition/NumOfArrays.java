package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/4/19 11:55
 * @description
 */
public class NumOfArrays {
    public int numOfArrays(int n, int m, int k) {
        if (m < k || n < k || k < 1) {
            return 0;
        }
        long mod = 1000000007;
        long[][][] dp = new long[n + 1][m + 1][k + 1];
        for (int i = 1; i <= m; i++) {
            dp[1][i][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int t = 1; t <= m; t++) {
                for (int j = 1; j <= k; j++) {
                    dp[i][t][j] += dp[i - 1][t][j] * t;
                    dp[i][t][j] %= mod;
                    for (int p = 1; p < t; p++) {
                        dp[i][t][j] += dp[i - 1][p][j - 1];
                        dp[i][t][j] %= mod;
                    }
                }
            }
        }
        long total = 0;
        for (int i = 1; i <= m; i++) {
            total += dp[n][i][k];
            total %= mod;
        }
        return (int) total;
    }

    // TODO dfs + 动态备忘录 自底向上的解法
    public int numOfArrays02(int n, int m, int k) {
        return 0;
    }


    public static void main(String[] args) {
        int n = 50, m = 100, k = 25;
        NumOfArrays solution = new NumOfArrays();
        System.out.println(solution.numOfArrays(n, m, k));
    }
}
