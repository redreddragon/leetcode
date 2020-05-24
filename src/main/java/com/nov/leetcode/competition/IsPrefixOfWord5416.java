package com.nov.leetcode.competition;

/**
 * @author : longwenhe
 * @date : 2020/5/24 11:06
 * @description :
 */
public class IsPrefixOfWord5416 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 1; i <= words.length; i++) {
            if (words[i - 1].startsWith(searchWord)) {
                return i;
            }
        }
        return -1;
    }
}
