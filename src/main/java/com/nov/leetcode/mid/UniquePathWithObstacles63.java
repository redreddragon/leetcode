package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/29 16:14
 * @description
 */
public class UniquePathWithObstacles63 {
    public int uniquePathWithObstacles(int[][] obstacleGrid) {
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    if (i > 0) {
                        obstacleGrid[i][j] = Math.min(1, obstacleGrid[i - 1][j]);
                    } else if (j > 0) {
                        obstacleGrid[i][j] = Math.min(1, obstacleGrid[i][j - 1]);
                    } else {
                        obstacleGrid[i][j] = 1;
                    }
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }
        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    public static void main(String[] agrs) {
        int[][] obstacleGrid = {
                {1, 0, 0}
        };
        UniquePathWithObstacles63 solution = new UniquePathWithObstacles63();
        System.out.println(solution.uniquePathWithObstacles(obstacleGrid));


    }
}
