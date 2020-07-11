package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/5/30 23:22
 * @description
 */
public class HasAllCodes5409 {
    public boolean hasAllCodes(String s, int k) {
        Integer Max = (1 << k) - 1;
        for (int i = 0; i < Max; i++) {
            String tmp = Integer.toBinaryString(i);
            int len = tmp.length();
            int count = k;
            StringBuffer sb = new StringBuffer(tmp);
            while (count - len > 0) {
                sb.insert(0, '0');
                count--;
            }
            if (!s.contains(sb.toString())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer a = 0;
        String s = Integer.toBinaryString(a);
        System.out.println(s);
    }
}
