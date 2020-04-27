package com.nov.leetcode.hard;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/24 0:45
 * @description
 */
public class LongestValidParentheses32 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < len + 1; i++) {
            if (s.charAt(i - 1) == '(') {
                dp[i] = 0;
            } else {
                if (s.charAt(i - 2) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else {
                    int index = i - dp[i - 1] - 1;
                    if (index > 0 && s.charAt(index - 1) == '(') {
                        dp[i] = dp[index - 1] + dp[i - 1] + 2;
                    } else {
                        dp[i] = 0;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 1; i < len + 1; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    // 栈结构，使用')'来作为边界，初始-1作为哨兵
    public int longestValidParentheses02(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int max = 0;
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(-1);
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                indexStack.push(i);
            } else {
                indexStack.pop();
                if (indexStack.isEmpty()) {
                    indexStack.push(i);
                } else {
                    max = Math.max(max, i - indexStack.peek());
                }
            }
        }
        return max;
    }

    //双指针法，主要注意左右两遍进行
    public int longestValidParentheses03(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                right = 0;
                left = 0;
            } else if (right == left) {
                max = Math.max(max, right + left);
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                right++;
            } else {
                left++;
                if (left == right) {
                    max = Math.max(max, right + left);
                } else if (left > right) {
                    left = 0;
                    right = 0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses32 solution = new LongestValidParentheses32();
        String s = ")()())()()(";
        System.out.println(solution.longestValidParentheses03(s));
    }
}
