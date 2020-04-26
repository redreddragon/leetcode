package com.nov.leetcode.competition;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : longwenhe
 * @date : 2020/4/26 13:37
 * @description :
 */

/*
给你一个整数数组 nums 和一个整数 k ，请你返回 非空 子序列元素和的最大值，子序列需要满足：子序列中每两个 相邻 的整数 nums[i] 和 nums[j] ，它们在原数组中的下标 i 和 j 满足 i < j 且 j - i <= k 。

        数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。
        输入：nums = [10,2,-10,5,20], k = 2
        输出：37
        解释：子序列为 [10, 2, 5, 20] 。
*/

public class ConstrainedSubsetSum5180 {
    /*
        int[] nums = {-999, 1, -999, -999, 1, -999, 1, 1, -999, 1, -999, -999, 1, -999};
        int k = 2;
        以下01方法用的是粗暴的左右中间剪枝的方法，虽然凑巧能通过测试用例，但上述自己构造的用例是无法通过的。
        目前根本还没有动态规划的思维，需要大量大量的联系！
    */
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = nums[0];
        Queue<Integer> indexQueue = new LinkedList<>();
        indexQueue.add(0);
        for (int i = 1; i < nums.length; i++) {
            int index = indexQueue.peek();
            dp[i] = Math.max(dp[index] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
            while (!indexQueue.isEmpty() && (i - indexQueue.peek() >= k || dp[indexQueue.peek()] < dp[i])) {
                indexQueue.poll();
            }
            indexQueue.add(i);
        }
        return result;
    }

    public int constrainedSubsetSum01(int[] nums, int k) {
        int begin = 0;
        int end = nums.length - 1;
        int max = Integer.MIN_VALUE;
        while (begin <= end && nums[begin] <= 0) {
            max = Math.max(max, nums[begin]);
            begin++;
        }
        while (begin <= end && nums[end] <= 0) {
            max = Math.max(max, nums[end]);
            end--;
        }
        if (begin > end) {
            return max;
        } else if (begin == end) {
            return nums[begin];
        }
        int storeMax = 0;
        for (int i = begin; i <= end; i++) {
            max = Math.max(storeMax, max);
            storeMax += nums[i];
            if (storeMax < 0) {
                storeMax = 0;
            }
        }
        max = Math.max(storeMax, max);
        storeMax = max;
        if (k == 1) {
            return max;
        } else {
            max = nums[begin] + nums[end];
            for (int i = begin + 1; i < end; ) {
                int first = i;
                int last = 0;
                if (nums[i] < 0) {
                    first = i - 1;
                }
                while (nums[i] < 0 && i < end) {
                    max += nums[i];
                    i++;
                }
                last = i;
                if (first == last) {
                    max += nums[i];
                    i++;
                } else {
                    max = trimNum(nums, first, last, max, k);
                }
            }
        }
        return Math.max(max, storeMax);
    }

    private int trimNum(int[] nums, int first, int last, int max, int k) {
        if (first >= last) {
            return max;
        }
        int count = 0;
        int sum = 0;
        int pre = first;
        int next = last;
        for (int i = first + 1; i < last; i++) {
            if (count < k - 1) {
                sum += nums[i];
                count++;
            } else {
                int tmpSum = sum;
                tmpSum += nums[i];
                tmpSum -= nums[i - k + 1];
                if (tmpSum < sum) {
                    sum = tmpSum;
                    pre = i - k + 1;
                    next = i + 1;
                }
            }
        }
        max -= sum;
        max = trimNum(nums, first, pre, max, k);
        max = trimNum(nums, next, last, max, k);
        return max;
    }

    public int constrainedSubsetSum02(int[] nums, int k) {
        //状态定义： dp[i] 选中i元素的最大值
        //状态转移方程：dp[i]=  Math.max(dp[i-1],dp[i-2],...,dp[i-k])+nums[i]
        //定义一个双端队列，队列存的是索引。如果当前值比最左侧的大，把左侧元素弹出，始终保持最
        //左侧的是最大值
        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            int idx = deque.getFirst();
            dp[i] = Math.max(nums[i], dp[idx] + nums[i]);
            res = Math.max(res, dp[i]);
            while (!deque.isEmpty() && (i - deque.getFirst() >= k || dp[deque.getFirst()] < dp[i])) {
                deque.pollFirst();
            }
            deque.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-999, 1, -999, -999, 1, -999, 1, 1, -999, 1, -999, -999, 1, -999};
        int k = 2;
        ConstrainedSubsetSum5180 solution = new ConstrainedSubsetSum5180();
        System.out.println(solution.constrainedSubsetSum(nums, k));
    }
}
