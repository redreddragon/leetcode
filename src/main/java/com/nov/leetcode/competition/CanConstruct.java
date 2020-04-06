package com.nov.leetcode.competition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/4/4 23:35
 * @description
 */
public class CanConstruct {
    public boolean canConstruct(String s, int k) {
        char[] chars = s.toCharArray();
        if (k > chars.length) {
            return false;
        } else if (k == chars.length) {
            return true;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (charMap.containsKey(chars[i])) {
                int count = charMap.get(chars[i]) + 1;
                charMap.put(chars[i], count);
            } else {
                charMap.put(chars[i], 1);
            }
        }
        int singleChar = 0;
        for (Character character : charMap.keySet()) {
            if (charMap.get(character) % 2 == 1) {
                singleChar++;
            }
        }
        return singleChar <= k;
    }

    public static void main(String[] args) {
        String s = "jwnudnpfwtswhlymalctgoazfwmlpxhcmyrhvkbzbjqvwhpxqqcdgtizrkidtqnndfyagnttunyvzvmjwhjtnyatwqrxgnopikysqizlvgximxbjpxvdytgqttqgtydvxpjbxmixgvlziqsykipongxrqwtayntjhwjmvzvynuttngayfdnnqtdikrzitgdcqqxphwvqjbzbkvhrymchxplmwfzaogtclamylhwstwfpndunwj";
        int k = 49;
        CanConstruct solution = new CanConstruct();
        System.out.println(solution.canConstruct(s, k));

    }

}
