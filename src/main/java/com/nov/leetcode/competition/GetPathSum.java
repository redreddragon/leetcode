package com.nov.leetcode.competition;

import java.util.Arrays;
import java.util.Date;

/**
 * @author longwenhe
 * @date 2020/4/30 19:53
 * @description
 */
public class GetPathSum {
    public int getPathSum(int N, int[] weights) {
        // 根据已知，N必然大于1
        long[] dp = new long[N];
        dp[0] = 1;
        long MOD = 991127;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                int path = getBitCount(weights[i] & weights[j]);
                dp[i] += path * dp[j];
                dp[i] %= MOD;
            }
        }
        return (int) dp[N - 1];
    }

    private int getBitCount(int num) {
        int count = 0;
        while (num > 0) {
            count += num & 1;
            num = num >> 1;
        }
        return count;
    }

    public int getPathSum02(int N, int[] weights) {
        long[] dp = new long[32];
        int MOD = 991127;
        int num = weights[0];
        int i = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                dp[i] = 1;
            }
            num = num >> 1;
            i++;
        }
        long sum = 0;
        for (int j = 1; j < N; j++) {
            sum = 0;
            num = weights[j];
            i = 0;
            while (num > 0) {
                if ((num & 1) == 1) {
                    sum += dp[i];
                    sum %= MOD;
                }
                num = num >> 1;
                i++;
            }
            num = weights[j];
            i = 0;
            while (num > 0) {
                if ((num & 1) == 1) {
                    dp[i] += sum;
                    dp[i] %= MOD;
                }
                num = num >> 1;
                i++;
            }
        }
        return (int) (sum % MOD);
    }

    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
        int N = 100_000;
        int[] weights = new int[N];
        Arrays.fill(weights, 0x7FFF_FFFF);
/*        int i = 0;
        while (i < N) {
            weights[i] = input.nextInt();
            i++;
        }*/
        GetPathSum solution = new GetPathSum();
        Date date1 = new Date();
        System.out.println(solution.getPathSum(N, weights));
        Date date2 = new Date();
        System.out.println((date2.getTime() - date1.getTime()) / 1000);
        System.out.println(solution.getPathSum02(N, weights));
    }
}
