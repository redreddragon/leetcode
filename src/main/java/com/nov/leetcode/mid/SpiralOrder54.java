package com.nov.leetcode.mid;

import com.nov.leetcode.common.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/3/26 11:36
 * @description
 */
public class SpiralOrder54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int upBoundary = -1, downBoundary = matrix.length, leftBoundary = -1, rightBoundary = matrix[0].length;
        Direction direction = Direction.RIGHT;
        while (upBoundary < downBoundary - 1 && leftBoundary < rightBoundary - 1) {
            switch (direction) {
                case RIGHT:
                    for (int i = leftBoundary + 1; i < rightBoundary; i++) {
                        result.add(matrix[upBoundary + 1][i]);
                    }
                    direction = Direction.DOWN;
                    upBoundary++;
                    break;
                case DOWN:
                    for (int i = upBoundary + 1; i < downBoundary; i++) {
                        result.add(matrix[i][rightBoundary - 1]);
                    }
                    direction = Direction.LEFT;
                    rightBoundary--;
                    break;
                case LEFT:
                    for (int i = rightBoundary - 1; i > leftBoundary; i--) {
                        result.add(matrix[downBoundary - 1][i]);
                    }
                    direction = Direction.UP;
                    downBoundary--;
                    break;
                case UP:
                    for (int i = downBoundary - 1; i > upBoundary; i--) {
                        result.add(matrix[i][leftBoundary + 1]);
                    }
                    direction = Direction.RIGHT;
                    leftBoundary++;
                    break;
                default:
                    break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        SpiralOrder54 solution = new SpiralOrder54();
        List<Integer> result = solution.spiralOrder(matrix);
        System.out.println(result);
    }

}
