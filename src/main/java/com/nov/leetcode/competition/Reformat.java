package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/19 10:33
 * @description
 */
public class Reformat {
    public String reformat(String s) {
        char[] chars = s.toCharArray();
        int numLength = 0;
        int charLength = 0;
        List<Character> numList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] - '0' >= 0 && chars[i] - '0' <= 9) {
                numLength++;
                numList.add(chars[i]);
            } else {
                charLength++;
                charList.add(chars[i]);
            }
        }
        if (Math.abs(numLength - charLength) > 1) {
            return "";
        }
        int len = Math.min(numLength, charLength);
        for (int i = 0, j = 0; j < len; j++) {
            if (numLength > charLength) {
                chars[i] = numList.get(j);
                chars[i + 1] = charList.get(j);
            } else {
                chars[i] = charList.get(j);
                chars[i + 1] = numList.get(j);
            }
            i++;
            i++;
        }
        if (numLength > len) {
            chars[chars.length - 1] = numList.get(len);
        }
        if (charLength > len) {
            chars[chars.length - 1] = charList.get(len);
        }
        return new String(chars);
    }
}
