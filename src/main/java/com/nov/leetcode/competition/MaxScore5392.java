package com.nov.leetcode.competition;

/**
 * @author : longwenhe
 * @date : 2020/4/26 10:41
 * @description :
 */
public class MaxScore5392 {
    public int maxScore(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = chars[0] == '0' ? 1 : 0;
        int right = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '1') {
                right++;
            }
        }
        int max = left + right;
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] == '0') {
                left++;
            } else {
                right--;
            }
            max = Math.max(max, right + left);
        }
        return max;
    }
}
