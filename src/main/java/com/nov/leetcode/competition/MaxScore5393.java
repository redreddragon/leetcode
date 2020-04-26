package com.nov.leetcode.competition;

import java.util.LinkedList;

/**
 * @author : longwenhe
 * @date : 2020/4/26 10:51
 * @description :
 */
public class MaxScore5393 {
    public int maxScore(int[] cardPoints, int k) {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        int len = cardPoints.length;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < k; i++) {
            left.add(cardPoints[i]);
            leftSum += cardPoints[i];
            right.add(cardPoints[len - 1 - i]);
            rightSum += cardPoints[len - 1 - i];
        }
        int max = 0;
        int count = k;
        while (count > 0) {
            if (leftSum > rightSum || (leftSum == rightSum && left.getFirst() > right.getFirst())) {
                int tmpLeft = left.removeFirst();
                max += tmpLeft;
                leftSum -= tmpLeft;
                int tmpRight = right.removeLast();
                rightSum -= tmpRight;
            } else {
                int tmpLeft = left.removeLast();
                leftSum -= tmpLeft;
                int tmpRight = right.removeFirst();
                rightSum -= tmpRight;
                max += tmpRight;
            }
            count--;
        }
        return max;
    }
}
