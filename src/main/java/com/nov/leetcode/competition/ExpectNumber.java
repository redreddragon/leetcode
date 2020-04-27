package com.nov.leetcode.competition;

import java.util.Arrays;

/**
 * @author longwenhe
 * @date 2020/4/25 15:43
 * @description
 */
public class ExpectNumber {
    public int expectNumber(int[] scores) {
        if (scores.length < 2) {
            return scores.length;
        }
        int count = 0;
        Arrays.sort(scores);
        for (int i = 0; i < scores.length; ) {
            while (i < scores.length - 1 && scores[i] == scores[i + 1]) {
                i++;
            }
            count++;
            i++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] scores = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int n = 48;
        ExpectNumber solution = new ExpectNumber();
        System.out.println(solution.expectNumber(scores));
    }
}
