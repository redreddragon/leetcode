package com.nov.leetcode.hard;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/4 19:37
 * @description
 */
public class MaximalRectangle85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] height = new int[row][col];
        for (int i = 0; i < row; i++) {
            int count = 0;
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    count++;
                } else {
                    count = 0;
                }
                height[i][j] = count;
            }
        }
        int[] singleHeight = new int[row + 2];
        int max = 0;
        singleHeight[0] = -1;
        singleHeight[row + 1] = -1;
        for (int m = 0; m < col; m++) {
            for (int n = 0; n < row; n++) {
                singleHeight[n + 1] = height[n][m];
            }
            max = Math.max(max, maxRectangle(singleHeight));
        }
        return max;
    }

    private int maxRectangle(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                int curHeight = height[stack.pop()];
                while (curHeight == height[stack.peek()]) {
                    stack.pop();
                }
                int curWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, curHeight * curWidth);
            }
            stack.push(i);
        }
        return maxArea;
    }

    // 节约内存空间，压缩循环个数
    public int maximalRectangle02(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] height = new int[col];
        int max = 0;
        for (int m = 0; m < row; m++) {
            for (int n = 0; n < col; n++) {
                height[n] = matrix[m][n] == '1' ? height[n] + 1 : 0;
            }
            max = Math.max(max, largestRectangleArea(height, 0, col - 1));
        }
        return max;
    }

    private int largestRectangleArea(int[] height, int start, int end) {
        if (start > end) {
            return 0;
        }
        boolean sorted = true;
        int minHeight = height[start];
        int minIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (height[i - 1] > height[i]) {
                sorted = false;
            }
            if (minHeight > height[i]) {
                minHeight = height[i];
                minIndex = i;
            }
        }
        int result = 0;
        if (sorted) {
            for (int i = start; i <= end; i++) {
                result = Math.max(result, height[i] * (end - i + 1));
            }
        } else {
            int leftMax = largestRectangleArea(height, start, minIndex - 1);
            int rightMax = largestRectangleArea(height, minIndex + 1, end);
            result = Math.max(minHeight * (end - start + 1), Math.max(leftMax, rightMax));
        }
        return result;
    }

    // 动态规划(84题不可使用动态规划，因为没有动态的过程，而直接是一种状态)
    public int maximalRectangle03(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] height = new int[col];
        int[] left = new int[col];
        int[] right = new int[col];
        Arrays.fill(right, col);
        int maxSquare = 0;
        for (int i = 0; i < row; i++) {
            int curLeft = 0, curRight = col;
            for (int j = 0; j < col; j++) {
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = col;
                    curRight = j;
                }
            }

            for (int j = 0; j < col; j++) {
                maxSquare = Math.max(maxSquare, height[j] * (right[j] - left[j]));
            }
        }
        return maxSquare;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        MaximalRectangle85 solution = new MaximalRectangle85();
        System.out.println(solution.maximalRectangle(matrix));
        System.out.println(solution.maximalRectangle02(matrix));
        System.out.println(solution.maximalRectangle03(matrix));
    }

}
