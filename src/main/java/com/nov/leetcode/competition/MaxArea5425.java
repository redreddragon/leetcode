package com.nov.leetcode.competition;

import java.util.Arrays;

/**
 * @author longwenhe
 * @date 2020/5/31 10:43
 * @description
 */
public class MaxArea5425 {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long[] hor = new long[horizontalCuts.length + 2];
        long[] ver = new long[verticalCuts.length + 2];
        hor[0] = 0;
        hor[hor.length - 1] = h;
        ver[0] = 0;
        ver[ver.length - 1] = w;
        for (int i = 0; i < horizontalCuts.length; i++) {
            hor[i + 1] = horizontalCuts[i];
        }
        for (int i = 0; i < verticalCuts.length; i++) {
            ver[i + 1] = verticalCuts[i];
        }
        Arrays.sort(hor);
        Arrays.sort(ver);
        long max = 0;
        int mod = 1000000007;
        long height = 0;
        long width = 0;
        for (int i = 1; i < hor.length; i++) {
            long tmpHeight = hor[i] - hor[i - 1];
            height = Math.max(height, tmpHeight) % mod;
        }
        for (int j = 1; j < ver.length; j++) {
            long tmpWidth = ver[j] - ver[j - 1];
            width = Math.max(width, tmpWidth) % mod;
        }
        max = height * width % mod;
        return (int) max;
    }

    public static void main(String[] args) {
        MaxArea5425 s = new MaxArea5425();
        int h = 5;
        int w = 4;
        int[] hor = {1, 2, 4};
        int[] ver = {1, 3};
        System.out.println(s.maxArea(h, w, hor, ver));
    }
}
