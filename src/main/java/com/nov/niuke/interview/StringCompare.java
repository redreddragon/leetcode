package com.nov.niuke.interview;

/**
 * @author longwenhe
 * @date 2020/7/31 14:06
 * @description
 */
public class StringCompare {
    public static void main(String[] args) {
        String s1 = "asdf aaa";
        String s2 = "aSdf     aaa";
        if (isEqual(s1, s2)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static boolean isEqual(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null && s2 != null) {
            return false;
        }
        if (s1 != null && s2 == null) {
            return false;
        }
        return getSubString(s1).equals(getSubString(s2));
    }

    public static String getSubString(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] - 'A' + 'a');
            }
            count++;
        }
        char[] result = new char[count];
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                result[j] = chars[i];
                j++;
            }
        }
        return new String(result); }}
