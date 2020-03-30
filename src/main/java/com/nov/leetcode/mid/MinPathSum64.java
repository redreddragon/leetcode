package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/29 18:28
 * @description
 */
public class MinPathSum64 {
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] = Math.min(grid[i][j] + grid[i][j - 1], grid[i][j] + grid[i - 1][j]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        MinPathSum64 solution = new MinPathSum64();
        System.out.println(solution.minPathSum(grid));
    }
}
