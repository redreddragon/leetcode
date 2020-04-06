package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/4/5 0:15
 * @description
 */
public class CheckOverlap {
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        if (x_center - radius > x2 || x_center + radius < x1 || y_center - radius > y2 || y_center + radius < y1) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
//        int radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1; // false
//        int radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3; // true
//        int radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1; // true
//        int radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1; // true
        int radius = 1415, x_center = 807, y_center = -784, x1 = -733, y1 = 623, x2 = -533, y2 = 1005; // false
        CheckOverlap solution = new CheckOverlap();
        System.out.println(solution.checkOverlap(radius, x_center, y_center, x1, y1, x2, y2));
    }
}
