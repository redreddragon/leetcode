package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/5/17 10:31
 * @description
 */
public class BusyStudent5412 {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                count++;
            }
        }
        return count;
    }
}
