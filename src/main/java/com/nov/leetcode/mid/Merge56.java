package com.nov.leetcode.mid;

/**
 * @author longwenhe
 * @date 2020/3/26 22:19
 * @description
 */
public class Merge56 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }
//        bubbleSort(intervals);
        binarySort(intervals, 0, intervals.length);
        int[][] result = new int[intervals.length][2];
        result[0][0] = intervals[0][0];
        result[0][1] = intervals[0][1];
        int i = 0, j = 1;
        while (i < intervals.length - 1 && j < intervals.length) {
            if (intervals[j][0] > result[i][1]) {
                i++;
                result[i][0] = intervals[j][0];
                result[i][1] = intervals[j][1];
                j++;
            } else if (intervals[j][1] > result[i][1]) {
                result[i][1] = intervals[j][1];
                j++;
            } else if (intervals[j][1] <= result[i][1]) {
                j++;
            }
        }
        int[][] finalResult = new int[i + 1][2];
        for (int k = 0; k < i + 1; k++) {
            finalResult[k][0] = result[k][0];
            finalResult[k][1] = result[k][1];
        }
        return finalResult;

    }

    private void binarySort(int[][] intervals, int start, int end) {
        if (start < end - 1) {
            int mid = (start + end) >> 1;
            binarySort(intervals, start, mid);
            binarySort(intervals, mid, end);
            binaryMerge(intervals, start, mid, end);
        }
    }

    private void binaryMerge(int[][] intervals, int start, int mid, int end) {
        int[][] tmp = new int[mid - start][2];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i][0] = intervals[start + i][0];
            tmp[i][1] = intervals[start + i][1];
        }
        for (int j = start, k = mid; j < mid || k < end; ) {
            if (j >= mid || k < end && intervals[k][0] < tmp[j - start][0]) {
                intervals[j + k - mid][0] = intervals[k][0];
                intervals[j + k - mid][1] = intervals[k][1];
                k++;
            } else {
                intervals[j + k - mid][0] = tmp[j - start][0];
                intervals[j + k - mid][1] = tmp[j - start][1];
                j++;
            }
        }
    }

    private void bubbleSort(int[][] intervals) {
        for (int i = intervals.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][0] > intervals[j + 1][0]) {
                    int[] tmp = intervals[j];
                    intervals[j] = intervals[j + 1];
                    intervals[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args1) {
        /*int[][] intervals = {
                {1, 4},
                {4, 5}
        };*/
        int[][] intervals = {
                {1, 4},
                {0, 4}
        };
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals[i].length; j++) {
                System.out.print(intervals[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
        Merge56 solution = new Merge56();
        int[][] result = solution.merge(intervals);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }


}
