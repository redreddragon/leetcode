package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/4/11 0:21
 * @description
 */
public class NumTrees96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= i; j++) {
                int left = dp[j - 1];
                int right = dp[i - j];
                sum += left * right;
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    // 卡特兰数，C(2n,n) - C(2n,n-1)，与栈混洗联系起来
    // 确定中序遍历的前（后）序遍历个数，就是一次栈混洗
    public int numTrees02(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    public static void main(String[] args) {
        int n = 4;
        NumTrees96 solution = new NumTrees96();
        System.out.println(solution.numTrees(n));
    }
}
