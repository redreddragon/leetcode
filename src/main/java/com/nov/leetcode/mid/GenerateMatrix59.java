package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/26 12:57
 * @description
 */
public class GenerateMatrix59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = -1, right = n, upper = -1, bottom = n;
        Direction direction = Direction.RIGHT;
        int count = 1;
        while (left < right - 1 && upper < bottom - 1) {
            switch (direction) {
                case RIGHT:
                    for (int i = left + 1; i < right; i++) {
                        matrix[upper + 1][i] = count;
                        count++;
                    }
                    direction = Direction.DOWN;
                    upper++;
                    break;
                case DOWN:
                    for (int i = upper + 1; i < bottom; i++) {
                        matrix[i][right - 1] = count;
                        count++;
                    }
                    direction = Direction.LEFT;
                    right--;
                    break;
                case LEFT:
                    for (int i = right - 1; i > left; i--) {
                        matrix[bottom - 1][i] = count;
                        count++;
                    }
                    direction = Direction.UP;
                    bottom--;
                    break;
                case UP:
                    for (int i = bottom - 1; i > upper; i--) {
                        matrix[i][left + 1] = count;
                        count++;
                    }
                    direction = Direction.RIGHT;
                    left++;
                    break;
                default:
                    break;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 4;
        GenerateMatrix59 solution = new GenerateMatrix59();
        int[][] matrix = solution.generateMatrix(n);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}
