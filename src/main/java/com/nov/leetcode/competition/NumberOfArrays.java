package com.nov.leetcode.competition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/4/18 23:13
 * @description
 */
public class NumberOfArrays {
    final static int LIMIT = (int) (1e9 + 7);

    public int numberOfArrays(String s, int k) {
        char[] chars = s.toCharArray();
        Map<Integer, Long> result = new HashMap<>();
        getNumArray(chars, result, k, 0);
        return (int) (result.get(0) % LIMIT);
    }

    // DFS+剪枝在大数据的时候出错，约能通过90%的案例，但不知道为什么不如动态规划。
    // 这种思维是自前向后，动态规划是自后向前
    private long getNumArray(char[] chars, Map<Integer, Long> result, int k, int index) {
        if (result.containsKey(index)) {
            return result.get(index);
        } else if (index == chars.length) {
            return 1;
        }
        Integer num = chars[index] - '0';
        long count = 0;
        int i = index;
        while (i < chars.length && num <= k) {
            i++;
            while (i < chars.length && (chars[i] - '0') == 0 && num <= k) {
                num = num * 10;
                i++;
            }
            if (num > k) {
                break;
            }
            count += getNumArray(chars, result, k, i) % LIMIT;
            if (i >= chars.length) {
                break;
            }
            num = num * 10 + (chars[i] - '0');
        }
        result.put(index, count % LIMIT);
        return count % LIMIT;
    }

    public int numberOfArrays02(String s, int k) {

        int n = s.length();
        long[] dp = new long[n + 1];
        int mod = 1000000007;

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, 9); j++) {
                if (s.charAt(i - j) == '0') {
                    continue;
                }
                long val = Long.valueOf(s.substring(i - j, i));
                if (val >= 1 && val <= k) {
                    dp[i] = (dp[i] + dp[i - j]) % mod;
                }
            }
        }

        return (int) dp[n];
    }

    public static void main(String[] args) {
//        String s = "407780786171321121429620765476840275495357129574174233567552131453038760763182952432348422252546559691171161181440370120987634895458140447952079749439961325982629462531738374032416182281868731817661954890417245087359968833257550123324827240537957646002494771036413572415";
        String s = "9";
        int k = 8;
        NumberOfArrays solution = new NumberOfArrays();
        System.out.println(solution.numberOfArrays02(s, k));
        System.out.println(LIMIT);
    }

}
