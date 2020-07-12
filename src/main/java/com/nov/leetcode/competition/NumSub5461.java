package com.nov.leetcode.competition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/7/12 10:58
 * @description
 */
public class NumSub5461 {
    public int numSub(String s) {
        char[] chars = s.toCharArray();
        long left = 0;
        long right = 0;
        Map<Long, Long> helper = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                if (left != right) {
                    long len = right - left;
                    if (!helper.containsKey(len)) {
                        helper.put(len, 0l);
                    }
                    helper.put(len, helper.get(len) + 1);
                }
                left = i + 1;
                right = i + 1;
            } else {
                right++;
            }
        }
        if (left != right) {
            long len = right - left;
            if (!helper.containsKey(len)) {
                helper.put(len, 0l);
            }
            helper.put(len, helper.get(len) + 1);
        }
        long sum = 0;
        int mod = 1000000007;
        for (long l : helper.keySet()) {
            long tmp = (l + 1) * l >> 1;
            tmp %= mod;
            sum += tmp * helper.get(l);
            sum %= mod;
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        String s = "0110111";
        NumSub5461 solution = new NumSub5461();
        System.out.println(solution.numSub(s));
    }
}
