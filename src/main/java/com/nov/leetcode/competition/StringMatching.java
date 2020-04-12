package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/12 10:33
 * @description
 */
public class StringMatching {
    public List<String> stringMatching(String[] words) {
        int len = words.length;
        List<String> result = new ArrayList<>();
        boolean[] added = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (added[i]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (added[j]) {
                    continue;
                }
                String tmpResult = include(words, i, j, added);
                if (tmpResult != null) {
                    result.add(tmpResult);
                    if (added[i]) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private String include(String[] words, int i, int j, boolean[] added) {
        String s1 = words[i];
        String s2 = words[j];
        if (s1.contains(s2)) {
            added[j] = true;
            return s2;
        } else if (s2.contains(s1)) {
            added[i] = true;
            return s1;
        }
        return null;
    }

}
