package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/26 10:14
 * @description
 */
public class Rotate48 {
    public void rotate(int[][] matrix) {
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            for (int i = start; i < end; i++) {
                // 四元素交换，通过旋转规律得到，注意旋转变化的是相对位置
                int tmp = matrix[start][i];
                matrix[start][i] = matrix[end - (i - start)][start];
                matrix[end - (i - start)][start] = matrix[end][end - (i - start)];
                matrix[end][end - (i - start)] = matrix[i][end];
                matrix[i][end] = tmp;
            }
            // 每一次缩小一圈，且交点不重复交换，退出条件是start < end 而没有等号
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        /*int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };*/
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        Rotate48 solution = new Rotate48();
        solution.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

}
