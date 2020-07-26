package com.nov.niuke.interview;

/**
 * @author longwenhe
 * @date 2020/7/23 20:12
 * @description
 */
public class Solution {
    // 姓名：龙文赫 电话：15911009538
    public static void main(String[] args) {
        String s = "-123";
        Solution solution = new Solution();
        System.out.println(solution.getValue(s));

    }

    public int getValue(String s) {
        boolean isPositive = true;
        char[] chars = s.toCharArray();
        int value = 0;
        if (chars[0] == '-') {
            isPositive = false;
        } else if (chars[0] >= '0' && chars[0] <= '9') {
            value = chars[0] - '0';
        } else if (chars[0] != '+') {
            return 0;
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return 0;
            } else {
                int tmp = chars[i] - '0';
                value = value * 10 + tmp;
            }
        }
        if (!isPositive) {
            value = -value;
        }
        return value;
    }

}
