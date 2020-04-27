package com.nov.leetcode.hard;

/**
 * @author longwenhe
 * @date 2020/4/23 22:03
 * @description
 */
public class IsMatch10 {
    public boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[0][0] = true;
        for (int j = 1; j <= lenP; j++) {
            if (p.charAt(j - 1) == '*') {
                if (j == 1) {
                    dp[0][j] = true;
                } else {
                    dp[0][j] = dp[0][j - 1] || dp[0][j - 2];
                }
            }
        }
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenP; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);
                if (pChar == '*') {
                    dp[i][j] = dp[i][j] || dp[i][j - 1]; // 匹配一个
                    if (j > 1 && compareChar(sChar, p.charAt(j - 2))) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; // 匹配多个
                    }
                    if (j > 2) {
                        dp[i][j] = dp[i][j] || dp[i][j - 2]; // 匹配0个
                    }
                } else {
                    dp[i][j] = compareChar(sChar, pChar) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[lenS][lenP];
    }

    private boolean compareChar(char s, char p) {
        if (p == '.') {
            return true;
        }
        return p == s;
    }

    public static void main(String[] args) {
        IsMatch10 solution = new IsMatch10();
//        String s = "aab";
//        String p = "c*a*b";
        String s = "aaa";
        String p = "ab*a*c*a";
        System.out.println(solution.isMatch(s, p));
    }
}
