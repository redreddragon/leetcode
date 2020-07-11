package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/5/17 10:37
 * @description
 */
public class ArrangeWords5413 {
    public String arrangeWords(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        char[] chars = text.toCharArray();
        Map<Integer, List<String>> strMap = new HashMap<>();
        int begin = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ' || i == chars.length - 1) {
                String tmp = i == chars.length - 1 ? new String(chars, begin, i - begin + 1)
                        : new String(chars, begin, i - begin);
                int len = tmp.length();
                if (!strMap.containsKey(len)) {
                    strMap.put(len, new ArrayList<>());
                }
                if (begin == 0) {
                    strMap.get(len).add(tmp.toLowerCase());
                } else {
                    strMap.get(len).add(tmp);
                }
                begin = i + 1;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (Integer len : strMap.keySet()) {
            List<String> list = strMap.get(len);
            for (String str : list) {
                if (sb.length() == 0) {
                    sb.append(changeStr(str));
                } else {
                    sb.append(str);
                }
                sb.append(' ');
            }
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    private String changeStr(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 'A' - 'a';
        return new String(chars);
    }
}
