package com.nov.leetcode.competition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/4/12 11:16
 * @description
 */
public class EntityParser {
    public String entityParser(String text) {
        StringBuffer sb = new StringBuffer();
        char[] textChars = text.toCharArray();
        Map<String, String> parserMap = new HashMap<>();
        parserMap.put("&quot;", "\"");
        parserMap.put("&apos;", "'");
        parserMap.put("&amp;", "&");
        parserMap.put("&gt;", ">");
        parserMap.put("&lt;", "<");
        parserMap.put("&frasl;", "/");
        for (int i = 0; i < textChars.length; ) {
            if (textChars[i] == '&') {
                int j = i;
                for (; j < textChars.length; j++) {
                    if (textChars[j] == ';') {
                        break;
                    }
                }
                String toParser = new String(textChars, i, j - i + 1);
                if (parserMap.containsKey(toParser)) {
                    sb.append(parserMap.get(toParser));
                } else {
                    sb.append(toParser);
                }
                i = j + 1;
            } else {
                sb.append(textChars[i]);
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String text = "&amp; is an HTML entity but &ambassador; is not.";
        EntityParser solution = new EntityParser();
        System.out.println(solution.entityParser(text));
    }
}
