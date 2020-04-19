package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/4/18 16:56
 * @description
 */
public class MinJump {
    public int minJump(int[] jump) {
        if (jump == null || jump.length == 0) {
            return 0;
        }
        int len = jump.length;
        int[] min = new int[len];
        int minIndex = len - 1;
        int lastMinNum = jump[minIndex];
        for (int i = len - 1; i >= 0; i--) {
            if (jump[i] + i >= len) {
                min[i] = 1;
                if (min[i] <= min[minIndex]) {
                    minIndex = i;
                    lastMinNum = min[i];
                }
            } else {
                min[i] = min[jump[i] + i] + 1;
            }
        }
        for (int i = minIndex + 1; i < len; i++) {
            if (min[i] != lastMinNum) {
                min[i] = lastMinNum + 1;
            }
        }
        getIndex(jump, min, minIndex, lastMinNum);
        return min[0];
    }

    public void getIndex(int[] jump, int[] min, int end, int lastMinNum) {
        if (end < 1) {
            return;
        }
        int minIndex = end - 1;
        for (int i = minIndex; i >= 0; i--) {
            if (jump[i] + i >= end) {
                min[i] = 1 + min[jump[i] + i];
                if (min[i] <= min[minIndex]) {
                    minIndex = i;
                    lastMinNum = min[i];
                }
            } else {
                min[i] = min[jump[i] + i] + 1;
            }
        }
        for (int i = minIndex + 1; i < end; i++) {
            if (min[i] != lastMinNum) {
                min[i] = lastMinNum + 1;
            }
        }
        end = minIndex;
        getIndex(jump, min, end, lastMinNum);
    }

    public static void main(String[] args) {
        int[] jump = {2, 5, 1, 1, 1, 1};
        MinJump solution = new MinJump();
        System.out.println(solution.minJump(jump));
    }
}
