package com.nov.leetcode.competition;

/**
 * @author : longwenhe
 * @date : 2020/5/24 11:13
 * @description :
 */
public class MaxVowels5417 {
    public int maxVowels(String s, int k) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (isVowel(chars[i])) {
                count++;
            }
        }
        int tmp = count;
        for (int i = k; i < chars.length; i++) {
            if (isVowel(chars[i - k])) {
                tmp--;
            }
            if (isVowel(chars[i])) {
                tmp++;
            }
            count = Math.max(count, tmp);
        }
        return count;
    }

    private boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }
}
