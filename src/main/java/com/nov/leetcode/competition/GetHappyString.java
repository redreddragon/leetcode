package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/18 22:53
 * @description
 */
public class GetHappyString {
    public String getHappyString(int n, int k) {
        List<String> result = new ArrayList<>();
        char[] happyChar = {'a', 'b', 'c'};
        char[] chars = new char[n];
        getHappyString(result, chars, happyChar, n, 0);
        return result.size() >= k ? result.get(k - 1) : "";
    }

    private void getHappyString(List<String> result, char[] chars, char[] happyChar, int n, int index) {
        if (index == n) {
            result.add(new String(chars));
            return;
        }
        if (index == 0) {
            for (int i = 0; i < happyChar.length; i++) {
                chars[index] = happyChar[i];
                getHappyString(result, chars, happyChar, n, index + 1);
            }
        } else {
            for (int i = 0; i < happyChar.length; i++) {
                if (chars[index - 1] != happyChar[i]) {
                    chars[index] = happyChar[i];
                    getHappyString(result, chars, happyChar, n, index + 1);
                }
            }
        }
    }

}
