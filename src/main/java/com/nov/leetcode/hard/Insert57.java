package com.nov.leetcode.hard;

import java.util.LinkedList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/3/27 0:25
 * @description
 */
public class Insert57 {
    public class Number {
        private int first;
        private int last;

        public Number(int first, int last) {
            this.first = first;
            this.last = last;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Number> tmpResult = new LinkedList<>();
        int length = intervals.length;
        if (newInterval[1] < intervals[0][0]) {
            int[][] result = new int[length + 1][2];
            result[0] = newInterval;
            for (int i = 0; i < length; i++) {
                result[i + 1] = intervals[i];
            }
            return result;
        } else if (newInterval[0] > intervals[length - 1][0]) {
            int[][] result = new int[length + 1][2];
            for (int i = 0; i < length; i++) {
                result[i] = intervals[i];
            }
            result[length] = newInterval;
            return result;
        } else if (newInterval[0] < intervals[0][0]) {
            int i = 0;
            boolean flag = false;
            for (; i < length - 1; i++) {
                if (newInterval[1] <= intervals[i][1] && newInterval[1] >= intervals[i][0]) {
                    tmpResult.add(new Number(newInterval[0], intervals[i][1]));
                    flag = true;
                    break;
                } else if (newInterval[1] < intervals[i + 1][0] && newInterval[1] > intervals[i][1]) {
                    tmpResult.add(new Number(newInterval[0], newInterval[1]));
                    flag = true;
                    break;
                }
            }
            if (flag) {
                for (int j = i + 1; j < length; j++) {
                    tmpResult.add(new Number(intervals[j][0], intervals[j][1]));
                }
            } else {
                int[][] result = new int[1][2];
                result[0][0] = newInterval[0];
                result[0][1] = newInterval[1] > intervals[length - 1][1] ? newInterval[1] : intervals[length - 1][1];
                return result;
            }

        } else {
            int left = 0;
            int leftFlag = -1;
            for (; left < length - 1; left++) {
                if (newInterval[0] <= intervals[left][1] && newInterval[0] >= intervals[left][0]) {
                    leftFlag = 0;
                    break;
                } else if (newInterval[0] < intervals[left + 1][0] && newInterval[0] > intervals[left][1]) {
                    leftFlag = 1;
                    break;
                }
            }

            if (leftFlag == -1) {
                intervals[length - 1][1] = newInterval[1] > intervals[length - 1][1] ? newInterval[1] : intervals[length - 1][1];
                return intervals;
            } else if (leftFlag == 1) {
                for (int i = 0; i < left; i++) {
                    tmpResult.add(new Number(intervals[i][0], intervals[i][1]));
                }

                int index = 0;
                boolean flag = false;
                for (; index < length - 1; index++) {
                    if (newInterval[1] <= intervals[index][1] && newInterval[1] >= intervals[index][0]) {
                        tmpResult.add(new Number(newInterval[0], intervals[index][1]));
                        flag = true;
                        break;
                    } else if (newInterval[1] < intervals[index + 1][0] && newInterval[1] > intervals[index][1]) {
                        tmpResult.add(new Number(newInterval[0], newInterval[1]));
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    for (int j = index + 1; j < length; j++) {
                        tmpResult.add(new Number(intervals[j][0], intervals[j][1]));
                    }
                } else {
                    int[][] result = new int[1][2];
                    result[0][0] = newInterval[0];
                    result[0][1] = newInterval[1] > intervals[length - 1][1] ? newInterval[1] : intervals[length - 1][1];
                    return result;
                }

            }

            if (newInterval[1] > intervals[length - 1][1]) {
                boolean flag = false;
                for (int i = 0; i < length - 1; i++) {
                    if (newInterval[1] <= intervals[i][1] && newInterval[1] >= intervals[i][0]) {
                        tmpResult.add(new Number(newInterval[0], intervals[i][1]));
                        flag = true;
                    } else if (newInterval[1] < intervals[i + 1][0] && newInterval[1] > intervals[i][1]) {
                        tmpResult.add(new Number(newInterval[0], newInterval[1]));
                        flag = true;
                    } else if (flag) {
                        tmpResult.add(new Number(intervals[i][0], intervals[i][1]));

                    }
                }
            } else {

            }

        }

        for (int i = 0; i < length; i++) {
            int flag = merge(intervals[i], newInterval);
            if (flag == -1) {
                tmpResult.add(new Number(newInterval[0], 0));
            } else if (intervals[i][0] <= newInterval[0] && newInterval[0] <= intervals[i][1]) {
                tmpResult.add(new Number(intervals[i][0], 0));
            } else if (newInterval[0] > intervals[i][1]) {
                tmpResult.add(new Number(intervals[i][0], intervals[i][1]));
                i++;
            }


        }
        return null;
        // TODO 脑子做的一团浆糊
    }

    private int merge(int[] interval, int[] newInterval) {
        if (newInterval[1] < interval[0]) {
            return -1;
        } else if (newInterval[0] > interval[1]) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int binarySearch(int[] intervals, int target) {
        int len = intervals.length;
        int start = 0, end = len - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (intervals[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start - 1;

    }

    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            // init data
            int newStart = newInterval[0], newEnd = newInterval[1];
            int idx = 0, n = intervals.length;
            LinkedList<int[]> output = new LinkedList<int[]>();

            // add all intervals starting before newInterval
            while (idx < n && newStart > intervals[idx][0])
                output.add(intervals[idx++]);

            // add newInterval
            int[] interval = new int[2];
            // if there is no overlap, just add the interval
            if (output.isEmpty() || output.getLast()[1] < newStart)
                output.add(newInterval);
                // if there is an overlap, merge with the last interval
            else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], newEnd);
                output.add(interval);
            }
            // TODO 拿出来再放进去这一招是没想到！！！
            // add next intervals, merge with newInterval if needed
            while (idx < n) {
                interval = intervals[idx++];
                int start = interval[0], end = interval[1];
                // if there is no overlap, just add an interval
                if (output.getLast()[1] < start) output.add(interval);
                    // if there is an overlap, merge with the last interval
                else {
                    interval = output.removeLast();
                    interval[1] = Math.max(interval[1], end);
                    output.add(interval);
                }
            }
            return output.toArray(new int[output.size()][2]);
        }
    }


    public static void main(String[] args) {
        int[] intervals = {1, 2, 3, 4, 5, 6};
        int target = 3;
        System.out.println(binarySearch(intervals, target));


    }

}
