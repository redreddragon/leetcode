package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/29 12:52
 * @description
 */
public class UniquePaths62 {
    // O(2^n),递归指数级别
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    // 自底向上方法，备忘录，O(nm)
    public int uniquePaths2(int m, int n) {
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            result[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            result[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }
        return result[m - 1][n - 1];
    }

    // 自底向上方法，备忘录，O(nm)，S(n)
    public int uniquePaths3(int m, int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[j] += result[j - 1];
            }
        }
        return result[n - 1];
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        UniquePaths62 solution = new UniquePaths62();
        System.out.println(solution.uniquePaths(m, n));
        System.out.println("----------------------");
        System.out.println(solution.uniquePaths2(m, n));
        System.out.println("----------------------");
        System.out.println(solution.uniquePaths3(m, n));
    }

}
