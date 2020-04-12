package com.nov.leetcode.competition;

import java.math.BigInteger;

/**
 * @author longwenhe
 * @date 2020/4/12 11:56
 * @description
 */
public class NumOfWays {
    public int numOfWays(int n) {
        BigInteger[] a = new BigInteger[n];
        BigInteger[] b = new BigInteger[n];
        BigInteger LIMIT = BigInteger.valueOf(1000000007);
        a[0] = BigInteger.valueOf(6);
        b[0] = BigInteger.valueOf(6);
        for (int i = 1; i < n; i++) {
            BigInteger multiply = b[i - 1].multiply(BigInteger.valueOf(2));
            a[i] = a[i - 1].multiply(BigInteger.valueOf(3)).add(multiply);
            b[i] = a[i - 1].multiply(BigInteger.valueOf(2)).add(multiply);
        }
        BigInteger result = a[n - 1].add(b[n - 1]).remainder(LIMIT);
        return result.intValue();
    }

    public int numOfWays02(int n) {
        final int LIMIT = 1000000007;
        long a = 6;
        long b = 6;
        for (int i = 1; i < n; i++) {
            long tmp1 = (b << 1) % LIMIT;
            long tmp2 = ((a << 1) + a) % LIMIT;
            long tmp3 = (a << 1) % LIMIT;
            long tmpA = tmp1 + tmp2;
            long tmpB = tmp1 + tmp3;
            a = tmpA;
            b = tmpB;
        }
        return (int) ((a + b) % LIMIT);
    }

    public static void main(String[] args) {
        int n = 50;
        NumOfWays solution = new NumOfWays();
        System.out.println(solution.numOfWays(n));
        System.out.println(solution.numOfWays02(n));
    }
}
