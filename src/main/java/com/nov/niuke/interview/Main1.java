package com.nov.niuke.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/7/20 0:41
 * @description
 */
public class Main1 {
    /**
     * @param input string字符串
     * @return int整型
     */
    public int helloWorldCount(String input) {
        // write code here
        Map<Character, Integer> map = new HashMap<>();
        String s = "heloWrd";
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), 0);
        }
        char[] chars = input.toCharArray();
        for (Character character : chars) {
            if (map.containsKey(character)) {
                int tmp = map.get(character);
                map.put(character, tmp + 1);
            }
        }
        int count = 0;
        while (hasOne(map)) {
            count++;
        }
        return count;
    }

    public boolean hasOne(Map<Character, Integer> map) {
        // helloWorld
        // h : 1 e: 1 l: 3 o: 2 W: 1 r: 1 d: 1
        String s = "heloWrd";
        for (int i = 0; i < s.length(); i++) {
            int tmp = map.get(s.charAt(i));
            if (i == 2) {
                if (tmp < 3) {
                    return false;
                } else {
                    tmp -= 3;
                    map.put(s.charAt(i), tmp);
                }
            } else if (i == 3) {
                if (tmp < 2) {
                    return false;
                } else {
                    tmp -= 2;
                    map.put(s.charAt(i), tmp);
                }
            } else {
                if (tmp < 1) {
                    return false;
                } else {
                    tmp -= 1;
                    map.put(s.charAt(i), tmp);
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        String s = "hheellllo12oWWwoorrllddaabc";
        Main1 solution = new Main1();
        System.out.println(solution.helloWorldCount(s));
    }
}
