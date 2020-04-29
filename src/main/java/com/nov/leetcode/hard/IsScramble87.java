package com.nov.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : longwenhe
 * @date : 2020/4/27 16:06
 * @description :
 */
public class IsScramble87 {
    // TODO 动态规划的思路如何想呢？
    public boolean isScramble(String s1, String s2) {
        if (s1.length() == 1 && s1.equals(s2)) {
            return true;
        }
        int len = s1.length();
        for (int i = len - 1; i >= 0; i--) {
            if (compare(s1.substring(0, i), s2.substring(0, i)) && compare(s1.substring(i, len), s2.substring(i, len))) {
                return true;
            }
        }
        return false;
    }

    private boolean compare(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (Character character : s1.toCharArray()) {
            if (map1.containsKey(character)) {
                int val = map1.get(character);
                val++;
                map1.put(character, val);
            } else {
                map1.put(character, 1);
            }
        }
        for (Character character : s2.toCharArray()) {
            if (map2.containsKey(character)) {
                int val = map2.get(character);
                val++;
                map2.put(character, val);
            } else {
                map2.put(character, 1);
            }
        }
        for (Character character : map1.keySet()) {
            if (!map2.containsKey(character) || map2.get(character) != map1.get(character)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "caebd";
        IsScramble87 solution = new IsScramble87();
        System.out.println(solution.isScramble(s1, s2));
    }
}
